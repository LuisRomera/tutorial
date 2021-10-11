package es.luis.examples.scala

import es.luis.examples.scala.topics.{Clases, Collections, Starting}

import java.util.logging.Logger

object TutorialScala {

  val log: Logger = Logger.getLogger(getClass.getName)

  def start(): Unit = {
    log.info("Starting scala")
    new Starting().start()
    new Collections().start()
    new Clases().start()
  }

}
