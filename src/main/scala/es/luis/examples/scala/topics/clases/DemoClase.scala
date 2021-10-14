package es.luis.examples.scala.topics.clases

import java.util.logging.Logger

class DemoClase(r: Vector[Int]) {

  require(r != null && getModuloCuadrado(r) > 0, "El radio debe ser mayor que 0") // precondition

  final val log: Logger = Logger.getLogger(this.getClass.getName)

  val radio: Vector[Int] = r

  val moduloCuadrado: Double = getModuloCuadrado(r)

  val modulo: Double = Math.sqrt(moduloCuadrado)

  val flattenVector: Vector[Int] = getFlattenVector()

  val flatMap: List[Char] = getFlatMap()

  def getFlatMap(): List[Char] = r.map(x => List(x, x * x, x * x * x)).toList.flatMap(s => s + "2")

  def getFlattenVector(): Vector[Int] = r.flatMap(x => List(x, x * x, x * x * x))

  private def getModuloCuadrado(r: Vector[Int]): Double = r.toList.map(scala.math.pow(_, 2)).sum

  override def toString: String = super.toString
}