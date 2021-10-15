package es.luis.examples.akka.topics

import akka.actor.{Actor, ActorSystem, Props}
import es.luis.examples.akka.AkkaTutorial
import es.luis.examples.akka.topics.actors.WordCountActor

import java.util.logging.Logger
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.util.{Failure, Success}

class ActorModel(name: String) extends AkkaTutorial(name) {

  val log: Logger = Logger.getLogger(name)


  override def executeTuto(): Unit = {
    log.info(s"Start tuto ${name}")
    // ActorSystem
    val actorSystem = ActorSystem("firstActorSystem")
    println(actorSystem.name)

    // Instanciamos los actores
    val wordCounterA = actorSystem.actorOf(Props[WordCountActor], "WordCountActorA")
    val wordCounterB = actorSystem.actorOf(Props[WordCountActor], "WordCountActorB")

    //Asincrono
    wordCounterA ! "Hola, esto es un toturial de Akka"
    wordCounterA ! 888
    wordCounterB ! "Llamo al actor B"



    //    Mandar argumentos a un actor
    class Person(name: String) extends Actor{
      override def receive: Receive = {
        case "Hola" => println(s"Hola mi nombre es $name")
        case _ =>
      }
    }

    val personActor = actorSystem.actorOf(Props(new Person("Luis")))
    personActor ! "Hola"


  }
}


