package es.luis.examples.akka.topicsb.actors

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, OneForOneStrategy, Props}

import scala.concurrent.duration.DurationInt

object Luis {
  case object ResumeException extends Exception
  case object StopException extends Exception
  case object RestartException extends Exception
}

class Luis extends Actor with ActorLogging {
  import Luis._

  override def preStart(): Unit = {
    log.info("Luis preStart")
  }
  override def preRestart(reason: Throwable, message: Option[Any]):Unit = {
    log.info("Luis preRestart")
    super.preRestart(reason, message)
  }

  override def postRestart(reason: Throwable) = {
    println("Luis postRestart hook...")
    super.postRestart(reason)
  }

  override def postStop() = {
    println("Luis postStop...")
  }

  override def receive: Receive = {
    case "Resume" =>
      log.info("Resume")
      throw ResumeException
    case "Stop" =>
      log.info("Stop")
      throw StopException
    case "Restart" =>
      log.info("Restart")
      throw RestartException
    case _ =>
      throw new Exception
  }
}

class Hera extends Actor {
  import Luis._

  var childRef: ActorRef = _

  override val supervisorStrategy =
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 second) {
      case ResumeException      => Resume
      case RestartException     => Restart
      case StopException        => Stop
      case _: Exception         => Escalate
    }

  override def preStart() = {
    // Create Aphrodite Actor
    childRef = context.actorOf(Props[Luis], "Luis")
    Thread.sleep(100)
  }

  def receive: Receive = {
    case msg =>
      println(s"Hera received ${msg}")
      childRef ! msg
      Thread.sleep(100)
  }
}

object Supervision extends App {
  // Create the 'supervision' actor system
  val system = ActorSystem("supervision")

  // Create Hera Actor
  val hera = system.actorOf(Props[Hera], "hera")

   hera ! "Resume"
   Thread.sleep(1000)
   println()

//   hera ! "Restart"
//   Thread.sleep(1000)
//   println()

//  hera ! "Stop"
//  Thread.sleep(1000)
//  println()

  system.terminate()
}

