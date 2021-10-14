package es.luis.examples.akka

import es.luis.examples.akka.topics.Essentials

object Pipeline {
  def start(): Unit = {
    new Essentials("01").executeTuto()
  }

}
