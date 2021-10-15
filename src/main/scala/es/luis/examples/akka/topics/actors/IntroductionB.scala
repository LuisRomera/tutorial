package es.luis.examples.akka.topics.actors

import akka.actor.{Actor, ActorSystem, Props}

object IntroductionB extends App {

  val system = ActorSystem("actorCapabilityDemo")
  val simpleActor = system.actorOf(Props[SimpleActor], "simpleActor")

  simpleActor ! "hola, actor"
  simpleActor ! 666

  case class SpecialMessage(contents: String)

  simpleActor ! SpecialMessage("Special Message")



}
