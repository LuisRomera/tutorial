package es.luis.examples.scala.tutoB

import scala.util.control.Breaks.break

object Variables extends App {

  val double: Double = 1.1
  val float: Float = 1
  val long: Long = 2L
  val int: Int = 3
  val short: Short = 3
  val byte: Byte = 1
  val unit: Unit = AnyRef //
  val boolean: Boolean = true
  val nothing: Nothing = nothing
  val char: Char = 'e'
  val anyVal: AnyVal = anyVal
  val nulo: Null = null
  val anyRef: AnyRef with Specializable = AnyRef

  // Variable lazy

  try {
    val x = 1 / 0
  } catch {
    case e: ArithmeticException => println("Error ")
  }
  lazy val x = 1 / 0
  println("Varible lazy")
  try {
    println(x)
  } catch {
    case e: ArithmeticException => println("Ya se ha evaluado la variable lazy")
  }

  val s = if (2 == 3) 5 else "hola"

  case class Persona(name: String)

  val persona = Persona(if (2 == 3) "Antonio" else if (2 != 2) "Pepe" else "Luis")

  do {
    println("While")
  } while (false)

  for (i <- 1 to 10)
    println(s"For: $i")
  for (i <- 1 until 10)
    println(s"For until: $i")
  for (i <- 1 until 10)
    if (i % 2 == 0) println(s"For until: $i")

  // Rangos
  val rango = 2 to 5
  val rangoList = (2 to 5).toList
  rangoList.foreach(println(_))

  val rangoB = 0 to 100 by 10
  for (i <- rangoB)
    println(s"rango de 10 en 10: $i")

  ('a' to 'z').foreach(println(_))


}
