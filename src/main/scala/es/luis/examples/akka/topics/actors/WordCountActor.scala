package es.luis.examples.akka.topics.actors

import akka.actor.Actor

class WordCountActor extends Actor {

  // Internal data
  var totalWords = 0

  def receive: PartialFunction[Any, Unit] = {
    case message: String =>
      totalWords += message.split(" ").length
      println(s"[Mensaje recibido] $message [$totalWords]")
    case msg => println(s"[word counter] No entiendo el mensaje: ${msg.toString}")
  }
}
