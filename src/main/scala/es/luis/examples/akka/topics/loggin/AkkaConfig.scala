package es.luis.examples.akka.topics.loggin

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object AkkaConfig extends App {

  val config = ConfigFactory.parseResources("resources/config/config-dev.conf")
  class SimpleActor extends Actor with ActorLogging{
    override def receive: Receive = {
      case message => log.info(message.toString)
    }
  }

  val system = ActorSystem("ActorLoggin", config)
  val actor = system.actorOf(Props[SimpleActor], "SimpleActor")

  actor ! "hello logging"


}
