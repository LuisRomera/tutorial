package es.luis.examples.scala.tutoB.otros

object Main extends App {

  // Option
  val lista = List.range(1, 5)
  val algunoElemento3: Option[Int] = lista.find(_ > 3)
  val algunoElemento8: Option[Int] = lista.find(_ > 8)
  val algunoElemento2: Int = lista.find(_ > 2).get
  println(algunoElemento8.getOrElse(0))
  try {
    println(algunoElemento8.get)
  } catch {
    case noSuchElementException: NoSuchElementException => "Error no hay ningún elemento que cumple la condición lista.find(_>8)"
    case arrayIndexOutOfBoundsException: ArrayIndexOutOfBoundsException => "Error el array no tiene ese indice"
  } finally {
    println("Finally: este trozo de código siempre se ejecuta")
  }

  // Creación de un objeto option
  val optionA: Option[Int] = None
  println(optionA.getOrElse(0))
  val optionB: Option[Int] = Some(10)
  println(optionB.getOrElse(0))

  try{
    exceptionPersonalizada(11)
  }catch {
    case throwable: Throwable => println(s"Se ha producido una excepcion ${throwable.getMessage}")
  }

  def exceptionPersonalizada(n: Int): Unit = {
    if (n > 10)
      throw new Exception("El número es mayor que 10")
  }


  val estudianteSobrecarga = new EstudianteSobrecarga
  estudianteSobrecarga.hola()
  estudianteSobrecarga.hola("Luis")
  estudianteSobrecarga.hola("Luis", 33)

}

class EstudianteSobrecarga{

  def hola(): Unit = println("Primer método")

  def hola(nombre: String):Unit = println(s"Segundo método ${nombre}")

  def hola(nombre: String, edad: Int): Unit = println(s"Tercer método ${nombre}/${edad}")
}
