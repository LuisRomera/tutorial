package es.luis.examples.scala.topics


class Functions extends Topics {

  private def metodoPrivado(): Unit = print("Método privado")

  def metodoVarArgs(name: String*): String = {
    println(s"hello $name")
    s"hello $name"
  }
  def metodoVarArgsCollections(names: List[String]*): String = {
    println(s"hello ${names.mkString(", ")}")
    s"hello ${names.mkString(", ")}"
  }


  override def start(): Unit = {

    // private methods
    metodoPrivado()

    def multiplyMethod(a: Int, b: Int): Int = a * b

    val multiplyFunction: (Int, Int) => Int = (a, b) => a * b

    if (multiplyMethod(2, 3).equals(multiplyFunction(2, 3))) println("Funciones literales")

    val fn1: (Int, Int) => Int = (a, b) => a + b
    val fn2 = new ((Int, Int) => Int) {
      override def apply(a: Int, b: Int): Int = a + b

      override def tupled: ((Int, Int)) => Int = super.tupled
    }
    fn1(2, 3) // = 5
    fn1.apply(2, 3) // = 5
    fn2(2, 3) // = 5
    val fn1curried = fn1.curried
    fn1curried(2)(3) // = 5
    val fn1tupled = fn1.tupled
    fn1tupled(2, 3) // = 5


    val lista: List[Int] = (1 to 10).toList
    lista.map(n => n * n)
    val listaPar = lista.filter(_ % 2 == 0)
    val listaParImpar = lista.partition(_ % 2 == 0)
    val spanF:(List[Int], List[Int]) = lista.span(_ % 6 != 0) // tupla de listas. La primera hasta donde cumple
    // la condicion

    metodoVarArgs()
    metodoVarArgs()

  }
}
