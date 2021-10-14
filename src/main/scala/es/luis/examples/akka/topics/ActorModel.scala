package es.luis.examples.akka.topics

import es.luis.examples.akka.AkkaTutorial

import java.util.logging.Logger
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.util.{Failure, Success}

class ActorModel(name: String) extends AkkaTutorial(name) {

  val log: Logger = Logger.getLogger(name)


  override def executeTuto(): Unit = {
    log.info(s"Start tuto ${name}")

  }
}


