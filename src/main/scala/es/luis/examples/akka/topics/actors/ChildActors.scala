package es.luis.examples.akka.topics.actors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import es.luis.examples.akka.topics.actors.ChildActors.CreditCard.{AttachToAccount, CheckStatus}

object ChildActors extends App {

  // Los actores pueden crear otros actores

  object Parent {
    case class CreateChild(name: String)

    case class TellChild(message: String)
  }

  class Parent extends Actor {

    import Parent._

    var child: ActorRef = null

    override def receive: Receive = {
      case CreateChild(name) =>
        println(s"${self.path} creating child")
        // create new actor
        val childRef = context.actorOf(Props[Child], name)
        context.become(withChild(childRef))
    }

    def withChild(ref: ActorRef): Receive = {
      case TellChild(message) =>
        ref forward message
    }
  }

  class Child extends Actor {
    override def receive: Receive = {
      case message => println(s"${self.path} I got: $message")
    }
  }

  import Parent._

  val system = ActorSystem("ParentChildDemo")
  val parent = system.actorOf(Props[Parent], "parent")

  // Create child actor
  parent ! CreateChild("child")
  // Tell child actor
  parent ! TellChild("Actor Child")

  /**
   * Actor selection
   *
   */
  val childSelection = system.actorSelection("/user/parent/child")

  childSelection ! "actorSelection"

  /**
   * Danger No pasar el estado de un actor mutable
   */

  object NativeBankAccount {
    case class Deposit(amount: Int)

    case class WithDraw(amount: Int)

    case object InitializeAccount
  }

  class NativeBankAccount extends Actor {

    import NativeBankAccount._
    import CreateChild._

    var amount = 0

    override def receive: Receive = {
      case InitializeAccount =>
        val creditCardRef = context.actorOf(Props[CreditCard], "card")
        creditCardRef ! AttachToAccount(this) // !!
      case Deposit(funds) => deposit(funds)
      case WithDraw(funds) => withDraw(funds)
    }

    def deposit(funds: Int): Unit = amount += funds

    def withDraw(funds: Int): Unit = amount -= funds
  }

  object CreditCard {
    case class AttachToAccount(bankAccount: NativeBankAccount) // !!

    case object CheckStatus
  }

  class CreditCard extends Actor {
    override def receive: Receive = {
      case AttachToAccount(account) => context.become(attachedTo(account))
    }
    def attachedTo(account: NativeBankAccount): Receive ={
      case CheckStatus => println(s"${self.path} your message has been processed.")
        account.withDraw(1)
    }
  }

  import NativeBankAccount._
  import CreateChild._

  val bankAccount = system.actorOf(Props[NativeBankAccount])
  bankAccount ! InitializeAccount

  Thread.sleep(500)
  val ccSelection = system.actorSelection("/user/account/card")
  ccSelection ! CheckStatus



}
