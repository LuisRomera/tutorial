package es.luis.examples.scala.topics

import java.util.logging.Logger
import scala.collection._

class Collections extends Topics {

  final val log: Logger = Logger.getLogger(this.getClass.getName)

  def tuplas(): Unit = {
    val tupleA = (1, 3)
    log.info(s"${tupleA._1}, ${tupleA._2}")
    val tupleB = ("Luis", "Pepe", "Maria")
    log.info(tupleB._1)
  }

  def arrays(): Unit = {
    val arrayA: Array[String] = Array("assad", "sdsadd")
    log.info(arrayA(0))
  }

  def listas(): Unit = {
    val listA: List[Int] = Range.inclusive(0, 100, 10).toList
    val listB = 4 :: 5 :: 6 :: Nil
    val listC = listB ::: listA
    print(listC)
  }

  def vectores(): Unit = {
    val u = Vector(1, 2, 3)
    print(u)
  }

  def sets(): Unit = {
    val set = Set(1, 1, 2, 3) //Set(1, 2, 3)
    log.info(set.toString())
  }

  def mutablidad(): Unit = {
    val setA = mutable.Set(1, 1, 2, 3) //Set(1, 2, 3)
    val setB = immutable.Set(4, 5, 6)
    immutable.Set(4, 5, 6).foreach(println(_))

    setA += 6
    // No se puede setB += 2
    setB.foreach(println(_))
    // Mapas y set son immutable mutables
  }

  def mapas(): Unit = {
    val m1 = mutable.Map('a' -> 1, 'b' -> 2, 'c' -> 3)
    val m2 = immutable.Map('a' -> "lala", 'b' -> 4)
    m1 += 'g' -> 2
    //    m2 += 'g' -> 2 No se puede hacer
    m2.foreach(println(_))
    m1.keySet.toList
    Map('a' -> 1, 'b' -> 2, 'c' -> 3).foreach(k => print(s"key: ${k._1}, value: ${k._2}"))

  }

  override def start(): Unit = {
    // Tuplas
    tuplas()

    // Arrays
    arrays()

    // Listas
    listas()

    // Vectores
    vectores()

    // Sets
    sets()

    // Maps
    mapas()

    // Mutables e inmutables
    mutablidad()


  }
}
