package es.luis.examples.scala.tutoB.orientacionObjetos

class Persona extends TraidA with TraidB{

  var nombre: String = _

  var apellidos: String = _

  override def imprimir(): Unit = {
    println(nombre)
  }

  override def imprimirEnTraid(): Unit = {
    println("imprimo desde la clase")
  }

  override var edad: Int = _

  override def imprimirBObject(): Unit = println(edad)
}
