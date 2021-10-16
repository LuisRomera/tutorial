package es.luis.examples.akka.topics.actors

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object IntroductionB extends App {

  val system = ActorSystem("actorCapabilityDemo")
  val simpleActor: ActorRef = system.actorOf(Props[SimpleActor], "simpleActor")

  val alice = system.actorOf(Props[SimpleActor], "alice")
  val bob = system.actorOf(Props[SimpleActor], "bob")

  simpleActor ! "hola, actor"
  simpleActor ! 666

  case class SpecialMessage(contents: String)
  case class SendMessage(simpleActor: ActorRef)
  case class SayHiTo(ref: ActorRef)
  alice ! SayHiTo(bob)

  simpleActor ! SpecialMessage("Special Message")

  // Mensajes inmutable
  // Mensajes serializables

  case class  WirelessPhoneMessage(content: String, ref: ActorRef)



}
