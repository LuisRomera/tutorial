package es.luis.examples.scala.topics

import java.util.logging.Logger
import scala.::
import scala.annotation.tailrec

/**
 * Tema compiezo.
 */
class Starting extends Topics {
  var value = 0

  final val log: Logger = Logger.getLogger(this.getClass.getName)

  def tryCatch(): Unit = {
    try {
      val s: Int = "32sa".toInt
    } catch {
      case ex: NumberFormatException =>
        log.severe("Error transformado string a int")
    }

    val arrayA: Array[String] = Array()
    val firstElement: String =
      try {
        arrayA(0)
      } catch {
        case _: IndexOutOfBoundsException => "first"
      }
      finally {
        "finally"
      }
    print(firstElement)
  }

  def queVariableEs(variable: Any): String = {
    variable match {
      case "Hello" => "Well, hello back"
      case 1 :: rest => s"A list beginning with 1, rest is $rest"
      case Nil => "The empty list"
      case 5 => "The number 5"
      case _: List[_] => "Some kind of list, not empty and not starting with 1"
    }
  }

  def matching(): Unit = {
    val x = 1
    var resultado = x match {
      case 1 => "Uno"
      case 0 => "Cero"
      case _ => ""
    }
    resultado = resultado + (x match {
      case y if y > 0 => ". Es postivo"
      case y if y < 0 => ". Es negativo"
      case _ => ""
    })
    println(resultado)

    println(queVariableEs(5))
    println("\t" + queVariableEs("Hello"))
    println(queVariableEs(List(2)))
    1 -> "hello"
  }

  /**
   * Comienza el tutorial
   */
  override def start(): Unit = {
    varibles()
    metodos()
    condicionales()
    tryCatch()
    bucles()
    matching()
  }

  private def getConnection: Boolean = value > 100


  /**
   * Notación @tailrec para llamadas redundantes
   *
   * @param n
   * @return
   */
  def factorial(n: Int): Int = {
    @tailrec def factorialAcc(acc: Int, n: Int): Int = {
      if (n <= 1) acc
      else factorialAcc(n * acc, n - 1)
    }

    factorialAcc(1, n)
  }

  def forYield() = {
    val a = Array(1, 2, 3, 4, 5)
    val b = for (e <- a) yield e * 2
    (for (e <- a) yield e * 2).equals(a.map(_ * 2))

    if ((for (e <- a) yield e * 2).equals(a.map(_ * 2)))
      print("Es igual")
    else
      print("No es igual")
  }

  def bucles(): Unit = {
    var i = 0
    while (i < 10) {
      log.info(s"print $i")
      i += 1
    }
    factorial(100)

    do {
      log.info(s"print $i")
      i -= 2
    } while (i > 0)

    for (w <- 1 to 10) {
      i += 2
    }
    // For Yield
    forYield()

    val resultado = Range.inclusive(1, 10, 3).foreach(print(_))

  }

  def condicionales() = {
    val a = 4
    var b = 6
    b = if (b < a) a else b
    if (b < a) log.info(s"$a es mayor que $b") else log.info(s"$a es menor que $b")
  }


  private def devuelve(numeroA: Int, numeroB: Int): Int = numeroA + numeroB

  def noDevuelve(str: String) = log.info(str)

  /**
   * Métodos
   */
  def metodos() = {
    val suma = devuelve(2, 5)
    noDevuelve("Método que no devuelve nada")
  }

  /**
   * Tipos de variables
   */
  private def varibles(): Unit = {
    val byte: Byte = 0
    val short: Short = 32
    val int: Int = 322
    val long: Long = 2L
    val float: Float = 255522
    val double: Double = 3333333.21
    val char: Char = 't'
    val string: String = "hello"
    val boolean: Boolean = false
    // Ningún valor
    //val unit: Unit
    val null: Null = null
    //val nothing: Nothing
    val any: Any = 55
    val anyRef: AnyRef = "asda"
  }
}
