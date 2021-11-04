package es.luis.examples.scala.tutoB.orientacionObjetos

abstract class PersonaAbs (nombre: String, appellido: String){
  def mayusculas:Unit = {
    println(nombre.toUpperCase)
  }

  def nombreCompleto:String

}

class EstudianteAbstracto (nombre: String, appellido: String) extends PersonaAbs(nombre: String, appellido: String) {
  override def nombreCompleto: String = s"$nombre $appellido"
}
