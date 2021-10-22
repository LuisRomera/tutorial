package es.luis.examples.akka.topics.loggin

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import akka.event.{Logging, LoggingAdapter}

object ActorLogginObject extends App {


  class SimpleActorWithExplicitLogger extends Actor {
    val logger: LoggingAdapter = Logging(context.system, this)

    override def receive: Receive = {
      /**
       * 1 - DEBUG
       * 2 - INFO
       * 3 - WARNING
       * 4 - ERROR
       */
      case message =>
        logger.info(message.toString) // LOG it
    }
  }

  val system: ActorSystem = ActorSystem("LoggingDemo")
  val actor: ActorRef = system.actorOf(Props[SimpleActorWithExplicitLogger], "log")
  actor ! "Logging a simple message"

  class ActorWithLogging extends Actor with ActorLogging {
    override def receive: Receive = {
      case (a, b) => log.info("a = {}, b = {}", a, b)
      case message => log.info(message.toString)
    }
  }

  val actorWithLogging = system.actorOf(Props[ActorWithLogging], "ActorWithLogging")

  actorWithLogging ! "write logging"
  actorWithLogging ! (23, 22)

}
