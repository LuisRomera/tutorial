package es.luis.examples.scala.tutoB.orientacionObjetos

class Estudiante (var nombre: String, var apellidos: String, var tipo: String){
  override def toString: String = s"{nombre: $nombre, apellidos: $apellidos, tipo: $tipo}"
}
object Estudiante{
  val TIPO_VIP = "VIP"
  val TIPO_NORMAL = "NORMAL"
  def metodoEstatico(): Unit = println("Método estático")
}

