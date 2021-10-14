package es.luis.examples.akka.topics

import es.luis.examples.akka.AkkaTutorial
import org.threeten.extra.scale.UtcRules.system

import java.util.logging.Logger
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Success}

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

    val aThread = new Thread(() => println("Thread A"))
    aThread.start()
    aThread.join()

    val bThread = new Thread(() => (1 to 1000).foreach(_ => println("Thread B")))
    val cThread = new Thread(() => (1 to 1000).foreach(_ => println("Thread C")))
    bThread.start()
    cThread.start()

    executeFuture()

  }


  // https://alvaromonsalve.com/2019/09/29/scala-future-con-ejemplos/
    // Future como aquel objeto que contiene un valor el cual estará disponible en algún instante.
    def executeFuture(): Unit = {
      futureA()
      futureB()
      futureC()
    }



  def futureA(): Unit ={
    val futureFail = Future {
      throw new Exception("Error!")
    }
    futureFail.onComplete {
      case Success(value) => println("Success:" + value)
      case Failure(e) => println("Respuesta Failure:" + e)
    }
  }

  def futureB(): Unit = {
    def getEvent(parametro: String): Future[String] = {
      val resultadoGetEvent = Future{
        val resultado = "getEvent: " + parametro
        resultado
      }
      resultadoGetEvent
    }
    def getTraffic(parametro: String): Future[String] = {
      val resultadoGetTraffic = Future {
        val resultado = "getTraffic: '" + parametro + "'"
        resultado
      }
      resultadoGetTraffic
    }
    val futureStep1: Future[String] = getEvent("PruebaEvent")
    val futureStep2: Future[String] = {
      futureStep1.flatMap { response =>
        getTraffic(response)
      }
    }
    futureStep2.onComplete {
      case Success(value) => println("futureStep2 Success:" + value)
      case Failure(e) => println("futureStep2 Failure:" + e)
    }
  }
}


