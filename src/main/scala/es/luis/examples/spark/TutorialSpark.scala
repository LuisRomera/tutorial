package es.luis.examples.spark

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}


object TutorialSpark extends App{



  val spark = SparkSession
    .builder()
    .appName("Spark")
    .master("local[*]")
    .getOrCreate()

  val df = spark.read.json("src/main/resources/examples/people.json")
  import spark.implicits._
  df.printSchema()
  df.select("age").show()
  df.select($"name", $"age" + 1).show()
  df.filter($"age" > 25).show()
  df.groupBy("age").count().show()


  // SQL QUERIES
  //  Global Temporary View en Spark SQL tienen un alcance de sesión y desaparecerán si la sesión que las crea termina.
  //  Si desea tener una vista temporal que se comparta entre todas las sesiones y mantenerse activa hasta que finalice
  //  la aplicación Spark, puede crear una vista temporal global. La vista temporal global está vinculada a una base de
  //  datos preservada por el sistema global_temp, y debemos usar el nombre calificado para referirla,
  //  por ejemplo SELECT * FROM global_temp.view1.
  df.createOrReplaceTempView("people")
  val sqlDf = spark.sql("SELECT * FROM people")
  sqlDf.show()

  // Register the DataFrame as a global temporary view
  df.createGlobalTempView("people")
  spark.sql("SELECT * FROM global_temp.people").show()
  //  Global temporary view is cross-session
  spark.newSession().sql("SELECT * FROM global_temp.people").show()

  case class Person(name: String, age: Long)
  val dfC = Seq(Person("Luis", 22), Person("Antonio", 66)).toDS()
  dfC.show()

  val people = spark.read.json("src/main/resources/examples/people.json").as[Person]

  val peopleRDD = spark.sparkContext.textFile("src/main/resources/examples/people.txt")

  val schema = StructType(List("name", "age").map(fieldName => StructField(fieldName, StringType, nullable = true)))

  val rowRdd = peopleRDD.map(l => l.split(",")).map(attributes => Row(attributes(0), attributes(1).trim))

  val peopleDf = spark.createDataFrame(rowRdd, schema)
  peopleDf.show()

}
