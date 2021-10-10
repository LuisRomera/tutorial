package es.luis.examples

import es.luis.examples.config.Configuration
import es.luis.examples.config.Constants.{SCALA, SPARK}
import es.luis.examples.scala.TutorialScala
import es.luis.examples.spark.TutorialSpark

import java.util.logging.Logger


object ApplicaciotnInit {
  val log: Logger = Logger.getLogger(this.getClass.getName)

  def main(args: Array[String]): Unit = {
    val configuration = new Configuration(args(0))
    log.info(s"Init application ${configuration.appName}")


    configuration.tutorial match {
      case SCALA => TutorialScala.start()
      case SPARK => TutorialSpark.start()
    }

  }

}
