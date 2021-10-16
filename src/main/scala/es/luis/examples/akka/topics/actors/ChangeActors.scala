package es.luis.examples.akka.topics.actors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import es.luis.examples.akka.topics.actors.ChangeActors.Mom.{Food, MomAsk, MomStart}

// Cambio de actores a lo largo del tiempo
object ChangeActors extends App {

  println("||| CAMBIO DE UN ACTOR |||")
  object FussyKid {
    case object KidAccept
    case object KidReject
    val HAPPY = "happy"
    val SAD = "sad"
  }

  class FussyKid extends Actor {
    import FussyKid._
    import Mom._
    // Internal state kid
    var state: String = HAPPY
    override def receive: Receive = {
      case Food(VEGETABLE) => state = SAD
      case Food(CHOCCOLATE) => state = HAPPY
      case Ask(_) =>
        if (state == HAPPY) sender() ! KidAccept
        else sender() ! KidReject
    }
  }



  object Mom {
    case class MomStart(kidRef: ActorRef)
    case class MomAsk(kidRef: ActorRef)
    case class Food(food: String)
    case class Ask(message: String) // quieres jugar?

    val VEGETABLE = "veggies"
    val CHOCCOLATE = "chocalate"
  }

  class Mom extends Actor {
    import Mom._
    import FussyKid._
    override def receive: Receive = {
      case MomStart(kidRef) =>
        kidRef ! Food(VEGETABLE)
        kidRef ! Ask("do you want play?")

      case KidAccept => println("Yes, my kid is happy!")
      case KidReject => println("My kid is idiot!!")
      case MomAsk(kidRef) =>
        kidRef ! Ask("do you want play?")

    }
  }

val system = ActorSystem("ChangeActor")
  val fussyKid = system.actorOf(Props[FussyKid])
  val mon = system.actorOf(Props[Mom])

  mon ! MomAsk(fussyKid)
  mon ! MomStart(fussyKid)
  mon ! MomAsk(fussyKid)
}
