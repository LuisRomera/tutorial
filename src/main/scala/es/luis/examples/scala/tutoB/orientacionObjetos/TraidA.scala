package es.luis.examples.scala.tutoB.orientacionObjetos

trait TraidA {

  var edad: Int

  def imprimir(): Unit

  def imprimirEnTraid(): Unit = {
    println("Imprimir dentro del traid")
  }

}
