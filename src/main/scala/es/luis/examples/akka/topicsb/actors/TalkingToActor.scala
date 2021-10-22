package es.luis.examples.akka.topicsb.actors

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import es.luis.examples.akka.topicsb.actors.Checker.{BlackUser, CheckerUser, WhiteUser}
import es.luis.examples.akka.topicsb.actors.Recorder.NewUser
import es.luis.examples.akka.topicsb.actors.Storage.AddUser

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt


case class User(userName: String, email: String)

object Recorder {
  sealed trait RecorderMgs
  case class NewUser(user: User) extends RecorderMgs

  def props(checker: ActorRef, storage: ActorRef) = Props(new Recorder(checker, storage))
}

object Checker {
  sealed trait CheckerMsg
  case class CheckerUser(user: User) extends CheckerMsg

  sealed trait CheckerResposive
  case class BlackUser(user: User) extends CheckerMsg
  case class WhiteUser(user: User) extends CheckerMsg
}

object Storage {
  sealed trait StorageMsg
  case class AddUser(user: User) extends StorageMsg
}

class Storage extends Actor with ActorLogging {
  var users = List.empty[User]
  override def receive: Receive = {
    case AddUser(user) => log.info(s"Add new user ${user}")
      users = user :: users
  }
}

class Recorder(checker: ActorRef, storage: ActorRef) extends Actor with ActorLogging {
  implicit val timeout: Timeout = Timeout(5 seconds)
  override def receive: Receive = {
    case NewUser(user) =>
      log.info("New user")
      checker ? CheckerUser(user) map {
        case WhiteUser(user) =>
          storage ! AddUser(user)
        case BlackUser(user) =>
          log.info("Black user")
      }
  }
}

class Checker extends Actor with ActorLogging {

  val blackList = List(User("luis", "luis@gmail.com"))

  override def receive: Receive = {
    case CheckerUser(user) if (blackList.contains(user)) =>
        log.info(s"Error el usuario ${user.userName} está en la blacklist")
      sender() ! BlackUser(user)
    case CheckerUser(user) =>
      log.info("Usuario permitido")
      sender() ! WhiteUser(user)



  }
}


object TalkingToActor extends App {

  val system = ActorSystem("talk-to-actor")

  val checker = system.actorOf(Props[Checker], "checker")
  val storage = system.actorOf(Props[Storage], "storage")
  val recorder = system.actorOf(Recorder.props(checker, storage), "recorder")

  recorder ! NewUser(User("Pepe", "pepe@gmai.com"))
  recorder ! NewUser(User("luis", "luis@gmail.com"))

  system.terminate()



}
