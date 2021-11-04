package es.luis.examples.scala.tutoB.orientacionObjetos


class Alumno(nombre: String, apellidos: String, edad: Int = 0) {
  var sexo: Char = _

  def getNombre(): String = this.nombre

  def getApellidos(): String = this.apellidos
}

class Vehiculo {
  var cv: Int = _
  var bastidor: String = _

  def visualizarDatos(): Unit = {
    println(s"$cv , $bastidor")
  }
}


class Coche extends Vehiculo {
  var ruedas: Int = _

  def verDatos(): Unit = {
    println(s"$ruedas")
  }
}

class Curso(private var parametro: Int = 0) {

  var valorPublico: String = _

  private var valorPrivado: String = _

  protected var valorProtected: String = "protected"

  def setValorPrivado(valor: String): Unit = valorPrivado = valor

  def setValorProtected(valor: String): Unit = valorProtected = valor

  def setParametro(int: Int) = {
    this.parametro = int
  }

}

class Asignatura extends Curso {
  def imprimir(): Unit = {
    valorProtected = "asignatura" // Tengo acceso
    println(valorProtected)
  }
}

class Producto(codProducto: Int, nombre: String){
  println("Estoy en el constructor del producto")
  imprimir()

  private var pvd: Int = 0

  def this(codProducto: Int, nombre: String = null, pvd: Int){
    this(codProducto, nombre)
    this.pvd = pvd
  }

  def imprimir():Unit = {
    println(s"$nombre -> $codProducto")
  }

}


object main extends App {
  var coche = new Coche()
  coche.ruedas = 4
  coche.cv = 500
  coche.bastidor = "FDPE-6392340"
  coche.verDatos()
  coche.visualizarDatos()

  var alumnoA = new Alumno("Luis", "Rodriguez")
  val alumnoB = new Alumno("Maria", "Lopez", 14)
  alumnoA.sexo = 'V'

  val persona = new Persona()
  persona.nombre = " Luis"
  persona.imprimir()
  persona.imprimirEnTraid()
  persona.edad = 30
  persona.imprimirB()
  persona.imprimirBObject()

  var asignatura = new Asignatura
  asignatura.valorPublico = "Esto es un valor publico"
  // asignatura.valorProtected = "asignatura" No tengo acceso
  asignatura.setValorPrivado("Modificamos el valor privado")
  asignatura.setValorProtected("Modificamos el valor protected")
  asignatura.valorPublico
  val producto = new Producto(2332, "tornillos")
  producto.imprimir()

  val productoA = new Producto(234234, "Tuercas", 3)
  println(productoA)

  new Empleado("Luis").imprimir
  new Empleado("Luis", 8).imprimir
  new Empleado("Luis","Calle Tablas", 55).imprimir

  val estudiante = new Estudiante("Luis", "FF", Estudiante.TIPO_VIP)
  Estudiante.metodoEstatico()

  AlumnoC.apply("luis")

  var estudienateCase = EstudianteCase("Luis", 89, "Calle case class")
  var estudianteHash = estudienateCase.hashCode()
  var estudianteB = estudienateCase.copy(nombre = "Raul")

  var estudianteC = new EstudianteAbstracto("Luis", "Rodrigues")
  println(estudianteC.nombreCompleto)

  var personaA = new PersonaAbs("Raul", "asd") {
    override def nombreCompleto: String = "as"
  }
  println(productoA.imprimir())
}
