package es.luis.examples.scala.topics

import es.luis.examples.scala.topics.clases.DemoClase

import java.util.logging.Logger

class Clases extends Topics {

  final val log: Logger = Logger.getLogger(this.getClass.getName)

  override def start(): Unit = {
    try {
      new DemoClase(Vector(0, 0))
    }catch {
      case exception: IllegalArgumentException => log.severe(exception.getMessage)
    }
    val demoClase = new DemoClase(Vector(1, 2, 3, 4))
    demoClase.flattenVector.foreach(r => log.info(r.toString))
    log.info(demoClase.toString)
  }
}

