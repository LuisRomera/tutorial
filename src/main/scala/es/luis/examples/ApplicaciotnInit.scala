package es.luis.examples

import es.luis.examples.akka.Pipeline
import es.luis.examples.config.Configuration
import es.luis.examples.config.Constants.{AKKA, SCALA, SPARK}
import es.luis.examples.scala.TutorialScala
import es.luis.examples.spark.TutorialSpark

import java.util.logging.Logger


object ApplicaciotnInit {
  val log: Logger = Logger.getLogger(this.getClass.getName)

//  https://www.baeldung.com/scala/

  def main(args: Array[String]): Unit = {
    val configuration = new Configuration(args(0))
    log.info(s"Init application ${configuration.appName}")
    configuration.tutorial match {
      case SCALA => new TutorialScala().start()
      case AKKA => Pipeline.start()
      case SPARK => TutorialSpark.start()
    }

  }

}
