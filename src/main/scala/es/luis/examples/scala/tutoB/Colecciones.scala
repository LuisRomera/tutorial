package es.luis.examples.scala.tutoB


object Colecciones extends App {

  // Colecciones principales: Seq, Set, Map
  // Seq: Colecciones secuencial (indexedSeq o LinearSeq) List, Arrays, ListBuffer,...
  // Set: Colecciones con valores unicos
  // Map: HashMap, WeakHashMap, sortedMap TreeMap, listMap


  // Listas
  val listaA: List[String] = List("Luis", "Maria", "David", "Javier")
  val listaB: List[Any] = List("Luis", "Maria", 'a', 10)

  println(listaA)
  println(listaB)
  println(listaA.head)
  println(listaA.last)
  println(listaA(2))
  println(listaA.slice(0, 3))
  for (m <- listaA.slice(1, 3)) {
    print(s"$m ")
  }
  var listaC = List()
  if (listaC.isEmpty) {
    var listaC = List.fill(22)("Pepe")
    println(listaC)
  }
  var listaD = List(1, "Luis", 99, 'C')
  println(listaD.reverse)
  var listaE = List(9, 22, 45, 56, 1, 324, 32, -5)
  println(s"Lista ordenada ${listaE.sorted}")

  // Añadir elementos
  listaE = listaE :+ 55
  listaE = 99 +: listaE
  println(listaE)
  var listaF = List()

  println(listaE :: listaC)
  println(listaE ::: listaC)

  // ListBuffer
  // Lista que permite modificar una lista

  import scala.collection.mutable.ListBuffer

  var listaBuffer = ListBuffer(2, 3, 65, 5, 10, -4, 53)
  listaBuffer += 90
  listaBuffer.append(-69)
  listaBuffer(2) = 22
  listaBuffer.remove(0, 2)
  listaBuffer -= 90

  // Array
  var arrayA: Array[Int] = Array(1, 2, 3, 4)
  var arrayB: Array[Int] = new Array[Int](5)
  arrayB(4)
  Array.copy(arrayA, 0, arrayB, 1, 2)
  arrayB.foreach(println)
  var arrayC = Array.concat(Range(1, 10).toArray,Range(10, 20).toArray)

  //ArrayBuffer

  import scala.collection.mutable.ArrayBuffer
  var arrayBuffer = ArrayBuffer(1, 4, -2)
  arrayBuffer += 2
  arrayBuffer ++= List(7, -10, 3)
  arrayBuffer -= 7
  arrayBuffer.remove(0)
  arrayBuffer(0) = -99
  arrayBuffer = arrayBuffer.sorted.reverse
  val arrayBufferVal = ArrayBuffer(1, 3, 4)
  arrayBufferVal += 2
  arrayBufferVal(0) = -99
  arrayBufferVal

  // SET

  val setList = Set(2, 9, 3)
  setList(9) // Exite el valor
  var setNombre = Set("Luis", "Pedro", 3)
  setNombre += "Luis"
  setNombre += "Antonio"
  setNombre("Luis")
  val setListA = List(1 ,2 ,3 ,4 ,4)
  println(if(setListA.toSet.size != setListA.size) "Elementos repetidos" else "No elementos repetidos")

  println(setNombre - "Antonio")

  // Maps
  var mapaA = Map((1, "as"), (2, "asdad"))
  var mapaB = Map(1 -> "Antonia", "Luis" -> 3, "ES" -> "Español")
  mapaB("ES")

  try {
    mapaB("IT")
  }catch {
    case ex: NoSuchElementException =>
      println("No se ha encontrado key, Añadiendo key...")
      mapaB + ("IT" -> "Italia")
  }

  // Tuple
  var tupla = List((1, "asdasd"), (2, "adjk"), ("3", "asdda", "asras"))
  val d = List((1, "asdasd"), (2, "adjk"), ("3", "asdda", "asras"))(2).asInstanceOf[(String, String, String)]




}
