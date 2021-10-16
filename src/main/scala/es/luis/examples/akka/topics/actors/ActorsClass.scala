package es.luis.examples.akka.topics.actors

import akka.actor.Actor
import es.luis.examples.akka.topics.actors.IntroductionB.SpecialMessage

class ActorsClass {
}

class SimpleActor extends Actor {
  override def receive: Receive = {
    case message: String => println(s" ${context.self.path} String recibido ${message}")
    case number: Int => println(s"Int recibido ${number}")
    case SpecialMessage(contents) => println(s"Mesage especial ${contents}")
    //    case SendMessage(message) => "hola" ! simpleActor
  }
}
