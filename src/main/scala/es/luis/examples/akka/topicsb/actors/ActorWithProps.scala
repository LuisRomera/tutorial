package es.luis.examples.akka.topicsb.actors

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import es.luis.examples.akka.topicsb.actors.MusicController.{Play, Stop}
import es.luis.examples.akka.topicsb.actors.MusicPlayer.{PlayMusic, StopMusic}

/**
 * Mesages del controlador de musica
 */
object MusicController {
  sealed trait ControllerMsg // Es un enums

  case object Play extends ControllerMsg

  case object Stop extends ControllerMsg

}

/**
 * Controlador de música
 */
class MusicController extends Actor with ActorLogging {
  override def receive: Receive = {
    case Play =>
      log.info("Playing....")

    case Stop =>
      log.info("Stopping....")

  }
}

/**
 *
 * Mensajes del reproductor de música
 */
object MusicPlayer {
  sealed trait Music

  case object PlayMusic extends Music

  case object StopMusic extends Music
}

/**
 * Reproductor de música
 */
class MusicPlayer extends Actor with ActorLogging {
  val controller: ActorRef = context.actorOf(Props[MusicController], "MusicController")
  override def receive: Receive = {
    case PlayMusic => log.info("Play")
      controller ! Play

    case StopMusic =>
      log.info("Stop")
      controller ! Stop
  }
}


object ActorWithProps extends App {

  val system = ActorSystem("Music")
  val musicPlayer = system.actorOf(Props[MusicPlayer], "MusicPlayer")
  musicPlayer ! PlayMusic

  Thread.sleep(5000)
  musicPlayer ! StopMusic


  system.terminate()

}
