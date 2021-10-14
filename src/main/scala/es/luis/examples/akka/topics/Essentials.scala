package es.luis.examples.akka.topics

import es.luis.examples.akka.AkkaTutorial

import java.util.logging.Logger

class Essentials(name: String) extends AkkaTutorial(name) {

  val log: Logger = Logger.getLogger(name)

  override def executeTuto(): Unit = {

    log.info(s"Start tuto ${name}")
    // partial functions
    val sqrt = new PartialFunction[Int, Any] {
      override def isDefinedAt(x: Int): Boolean = x >= 0

      override def apply(x: Int): Any = x match {
        case value if value >= 0 => math.sqrt(value.toDouble)
        case _ => "No existe"
      }
    }
    sqrt.isDefinedAt(-2)
    sqrt.isDefinedAt(2)
    sqrt.apply(2)
    sqrt(-2)


    val partialFunction: PartialFunction[Int, Int] = {
      case 1 => 42
      case 2 => 65
      case 5 => 999
    }

    val pf = (x: Int) => x match {
      case 1 => 42
      case 2 => 65
      case 5 => 999
    }

    val function: (Int => Int) = partialFunction

    val modifiedList = (1 to 10).toList.map({
      case 1 => 42
      case _ => 0
    })

  }

}
