package es.luis.examples.akka.topics.actors

import akka.actor.{Actor, ActorSystem, Props}

object Introduction {
  def start():Unit = {
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
