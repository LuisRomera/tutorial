package es.luis.examples.scala.tutoB

object Funciones extends App {

  def funcionHola: Unit = println("Hola")

  def funcionPrinta(texto: String): Unit = {
    println(texto)
  }

  funcionHola
  funcionPrinta("hola....")

  def operaciones(numA: Int = 0, numB: Int = 0, operacion: Char): Any = {
    operacion match {
      case '+' => numA + numB
      case '-' => numA - numB
      case '*' => numA * numB
      case '/' => if (numB != 0) numA.toDouble / numB.toDouble else "No se puede dividir entre 0"
      case _ => "No existe operación"
    }
  }

  operaciones(1, 0, '/')
  operaciones(operacion = '/')
  operaciones(numB = 100, operacion = '+')


  def serie(numeros: Int*): Int = {
    numeros.sum
  }

  println(serie(1, 2, 3, 3, 4, 4))


}
