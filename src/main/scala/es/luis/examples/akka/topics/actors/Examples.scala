package es.luis.examples.akka.topics.actors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import es.luis.examples.akka.topics.actors.Examples.BankAccount.{Deposit, Statement, TransactionFailure, TransactionSuccess, Withdraw}
import es.luis.examples.akka.topics.actors.Examples.Person.LiveTheLife

object Examples extends App {

  /**
   * Examples
   */

  println("||| EXAMPLE COUNTER |||")


  case class Aumenta(int: Int)

  case class Disminuye(int: Int)

  case class Print()

  // 1 Contador
  class Counter extends Actor {

    var counter = 0

    override def receive: Receive = {
      case Aumenta(value) => counter += value
      case Disminuye(value) => counter -= value
      case Print() => println(s"Valor actual ${counter}")
    }
  }
  val system = ActorSystem("ActorExamples")

  val counterActor: ActorRef = system.actorOf(Props[Counter], "counterActor")
  (1 to 100).toList.foreach(r => {
    math.random() match {
      case x if x < 1.0/3.0 => counterActor ! Aumenta(r)
      case x if x > 2.0/3.0 => counterActor ! Disminuye(r)
      case _ => counterActor ! Print()
    }
  })
  println(s"Valor final: ")
  counterActor ! Print()

  println("************************************************************************")


  println("||| EXAMPLE BANK |||")

  object BankAccount {
    case class Deposit(amount: Int)
    case class Withdraw(amount: Int)
    case object Statement
    case class TransactionSuccess(message: String)
    case class TransactionFailure(message: String)
  }

  // Actor bank account
  class BankAccount extends Actor {
    var funds = 0
    override def receive: Receive = {
      case Deposit(amount) =>
        if (amount < 0) sender() ! TransactionFailure("Invalid deposit amount")
        else {
          funds += amount
          sender() ! TransactionSuccess(s"Successfull deposited ${amount}")
        }
      case Withdraw(amount) =>
        if (amount < 0) sender() ! TransactionFailure("Invalid withdraw amount")
        else if(amount > funds) sender() ! TransactionFailure("Insufficient funds")
        else {
          funds -= amount
          sender() ! TransactionSuccess(s"Successfull withdraw ${amount}")
        }
      case Statement => sender() ! s"Your balance is $funds"
    }
  }

  object Person{
    case class LiveTheLife(account: ActorRef)
  }

  // Person actor
  class Person extends Actor{

    import Person._
    import BankAccount._

    override def receive: Receive = {
      case  LiveTheLife(account) =>
        account ! Deposit(10000)
        account ! Withdraw(90000)
        account ! Withdraw(500)
        account ! Statement
      case message => println(message.toString)
    }
  }

  val account = system.actorOf(Props[BankAccount], "bankAccount")
  val person = system.actorOf(Props[Person], "billionaire")

  person ! LiveTheLife(account)





}
