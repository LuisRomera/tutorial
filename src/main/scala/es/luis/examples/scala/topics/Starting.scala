package es.luis.examples.scala.topics

import java.util.logging.Logger

/**
 * Tema compiezo.
 */
class Starting extends Topics {

  final val log: Logger = Logger.getLogger(this.getClass.getName)

  def condicionales() = {
    val a = 4
    var b = 6
    b = if (b < a) a else b
    if (b < a) log.info(s"$a es mayor que $b") else log.info(s"$a es menor que $b")
  }

  /**
   * Comienza el tutorial
   */
  override def start(): Unit = {
    varibles()
    metodos()
    condicionales()
    listas()
  }


  private def listas(): Unit = {

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
