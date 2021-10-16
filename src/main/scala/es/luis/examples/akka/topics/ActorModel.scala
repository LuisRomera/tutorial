package es.luis.examples.akka.topics

import es.luis.examples.akka.AkkaTutorial
import es.luis.examples.akka.topics.actors.Introduction

import java.util.logging.Logger

class ActorModel(name: String) extends AkkaTutorial(name) {

  val log: Logger = Logger.getLogger(name)

  override def executeTuto(): Unit = {
    log.info(s"Start tuto ${name}")
    // ActorSystem
    Introduction.start()
    //    IntroductionB.start()




  }
}


