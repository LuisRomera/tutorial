package es.luis.examples.scala.tutoB.orientacionObjetos

class AlumnoC {
  var nombre: String = _
}

object AlumnoC {
  def apply(nombre: String): AlumnoC = {
    var alumno = new AlumnoC()
    alumno.nombre = nombre
    alumno
  }
}
