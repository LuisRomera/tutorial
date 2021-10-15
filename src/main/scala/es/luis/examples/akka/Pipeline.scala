package es.luis.examples.akka

import es.luis.examples.akka.topics.{ActorModel, Essentials}

object Pipeline {
  def start(): Unit = {
    new Essentials("01").executeTuto()
    new ActorModel("02").executeTuto()
  }

}
