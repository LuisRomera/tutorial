package es.luis.examples.akka.topics.actors

import akka.actor.Actor

class ActorsClass {
}

class SimpleActor extends Actor {
  override def receive: Receive = {
    case message: String => println(s"String recibido ${message}")
    case number: Int => println(s"Mesage recibido ${number}")

  }
}
