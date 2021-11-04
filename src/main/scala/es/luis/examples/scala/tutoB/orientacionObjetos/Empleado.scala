package es.luis.examples.scala.tutoB.orientacionObjetos

class Empleado {

  println("Constructor primario")

  private var nombre: String = _

  private var direccion: String = _

  private var edad: Int = _

  def imprimir: Unit = {
    println(s"nombre -> $nombre, direccion -> $direccion, edad -> $edad")
  }

  def this(nombre: String) {
    this()
    this.nombre = nombre
  }

  def this(nombre: String, edad: Int) {
    this(nombre)
    this.edad = edad
  }

  def this(nombre: String, direccion: String, edad: Int) {
    this(nombre, edad)
    this.direccion = direccion
  }
}