package es.luis.examples.scala.tutoB.otros

object Ficheros extends App {

  val path = "C:\\Users\\la.romera\\proyectos\\otros\\tutorial\\src\\main\\resources\\quijote.txt"

  import java.io._
  val fichero = new PrintWriter(new File(path))
  fichero.write("\nEstoy escribiendo text")
  fichero.close()


  import scala.io.Source
  val ficheroPath = "resources/quijote.txt"
  val soruce = Source.fromFile(path)
  val text = soruce.toList.mkString("")
  soruce.close()
  println(text)


}
