package es.luis.examples.scala.tutoB.programacionFuncional


class Practica {
  //  def ejemplo(function: () => Unit){
  //    function
  //  }

  def ejemploB(cifraA: Int, function: (Int) => Int): Int = {
    function(cifraA)
  }

  def funcionEjemploA(): Unit = {
    println("asdasd")
  }
}


object Main extends App {

  // Funciones de orden superior

  // Function literals o anónimas
  val pares: List[Int] = List.range(1, 100).filter(_ % 2 == 0)
  pares.foreach(println(_))
  pares.map(math.pow(_, 2)).foreach(println(_))

  var prA = new Practica
  prA.funcionEjemploA()
  //    prA.ejemplo( prA.funcionEjemploA() )

  def multi(n: Int): Int = n * 4

  val resultEjemploB = prA.ejemploB(3, multi)
  println(resultEjemploB)
  val resultEjemploC = prA.ejemploB(6, (x: Int) => x * 10)
  println(resultEjemploC)
  val resultEjemploD = prA.ejemploB(6, _ * 10)
  println(resultEjemploD)


  // Funciones lambdas
  val cuadrado = (numero: Int) => math.pow(numero, 2)
  val otroCuadrado = math.pow(_, 2)
  println(cuadrado(100))
  println(otroCuadrado(100))

  val esPar = (n: Int) => n % 2 == 0
  println(List.range(1, 100).filter(esPar(_)))

  val multip = (nA: Int, nB: Int) => nA * nB
  println(List.range(1, 100).map(n => multip(n, n - 1)))

  // Devolver una función
  def metodoA(): Int => Int = {
    (n: Int) => n * 2
  }

  val vA = metodoA()
  List.range(1, 11).map(vA(_))

  // Partially Applied Functions: funciones que se aplican de manera parcial
  def calcular(n1: Int, n2: Int, n3: Int): Int = n1 + n2 + n3

  val x = calcular(10, 20, _: Int)

  println(x(40))

  println(x(10))

  // Currying Fucntions
  // Currying transforma una funcion que tiene multiples argumentos en una funcion que tome un argumento
  // (Int, Int) => Int

  def sumar(a: Int, b: Int): Int = a + b

  def sumarA(x: Int) = (y: Int) => x + y

  def sumarB(x: Int)(y: Int)(operation: String): Any = if (operation.equals("+")) x + y else "no sé que operación es"

  println(sumarA(1)(2))

  val xA = sumarA(50)

  println(xA(50))
  println(xA(10))

  val yA = sumarB(2)(2)_

  println(yA("+"))
  println(yA("*"))


  var numero = 90
  val sumarC = (x: Int) => x+numero

  println(sumarC(2))
  numero = 900
  println(sumarC(2))
}
