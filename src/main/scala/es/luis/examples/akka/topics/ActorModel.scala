package es.luis.examples.akka.topics

import akka.actor.{Actor, ActorSystem, Props}
import es.luis.examples.akka.AkkaTutorial
import es.luis.examples.akka.topics.actors.{Introduction, IntroductionB, WordCountActor}

import java.util.logging.Logger
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.util.{Failure, Success}

class ActorModel(name: String) extends AkkaTutorial(name) {

  val log: Logger = Logger.getLogger(name)

  override def executeTuto(): Unit = {
    log.info(s"Start tuto ${name}")
    // ActorSystem
    Introduction.start()
//    IntroductionB.start()


  }
}


