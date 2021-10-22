package es.luis.examples.akka.topicsb

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

case class Hello (message: String)
class HelloActor extends Actor with ActorLogging{
  override def receive: Receive = {
    case Hello(message) => log.info(message)
  }
}

object AAHelloWord extends App {
  val system = ActorSystem("AAHelloWord")
  val actorHello = system.actorOf(Props[HelloActor], "HelloActor")
  actorHello ! Hello("Hello Actor")
}
