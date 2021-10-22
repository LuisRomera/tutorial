package es.luis.examples.spark.sql

import es.luis.examples.spark.Utils.{PATCH_EXAMPLES, getSparkSession}
import org.apache.spark.sql.catalyst.ScalaReflection
import org.apache.spark.sql.{DataFrame, Encoders, Row, functions}
import org.apache.spark.sql.functions.{array_contains, col, lit, when}
import org.apache.spark.sql.types.{IntegerType, StringType, StructType}

object Funtions extends App {
  val spark = getSparkSession()

  val carsDF = spark.read
    .option("sep", ",")
    .option("inferSchema", "true")
    .option("header", "true")
    .csv(s"${PATCH_EXAMPLES}cars.csv")


  val employeesDF = createDFEmployees()
  println("*********************************    SELECT    ****************************************")
  carsDF.select(col("Marca"), col("Año"))

  import spark.implicits._

  carsDF.select(col("Marca"), col("Año"))
  employeesDF.select(col("name.firstname"))


  carsDF.select(carsDF.columns.slice(0, 2).map(m => col(m)): _*)
  carsDF.select(carsDF.colRegex("`^.*Marca*`"))

  println("*********************************  WITHCOLUMN  ****************************************")
  carsDF
    .withColumn("Oferta", $"Precio" / 2)
    .withColumn("Pais", lit("USA"))


  carsDF.createOrReplaceTempView("CARS")
  spark.sql("SELECT Precio*100 as salary, Precio*-1 as CopiedColumn, 'USA' as country FROM CARS")
    .withColumnRenamed("salary", "precio")
    .show()
  carsDF
    .withColumnRenamed("Precio", "NuevoPrecio")
    .drop("Precio")


  println("*********************************   DROP   ****************************************")
  employeesDF.drop("name.firstname", "name.lastname", "salary")
  println("*********************************  SCHEMA  ****************************************")
  val structureSchema = new StructType()
    .add("name", new StructType()
      .add("firstname", StringType)
      .add("middlename", StringType)
      .add("lastname", StringType))
    .add("id", StringType)
    .add("gender", StringType)
    .add("salary", IntegerType)

  structureSchema.prettyJson

  case class Name(first: String, last: String, middle: String)
  case class Employee(fullName: Name, age: Integer, gender: String)

  val scalaSchema = ScalaReflection.schemaFor[Employee].dataType.asInstanceOf[StructType]

  val encoderSchema = Encoders.product[Employee].schema
  encoderSchema.printTreeString()



  def createDFEmployees(): DataFrame = {
    val data = Seq(Row(Row("James ", "", "Smith"), "36636", "M", 3000),
      Row(Row("Michael ", "Rose", ""), "40288", "M", 4000),
      Row(Row("Robert ", "", "Williams"), "42114", "M", 4000),
      Row(Row("Maria ", "Anne", "Jones"), "39192", "F", 4000),
      Row(Row("Jen", "Mary", "Brown"), "", "F", -1)
    )
    val schema = new StructType()
      .add("name", new StructType()
        .add("firstname", StringType)
        .add("middlename", StringType)
        .add("lastname", StringType))
      .add("dob", StringType)
      .add("gender", StringType)
      .add("salary", IntegerType)

    spark.createDataFrame(spark.sparkContext.parallelize(data), schema)
  }
  println("*********************************  FILTER  ****************************************")

  employeesDF.filter($"salary" <= 3000)
  employeesDF.filter("gender == 'M'")
  employeesDF.filter(employeesDF("gender") === "F")
  employeesDF.filter(employeesDF("gender") === "F" && (employeesDF("salary") === 4000))
  carsDF.withColumn("DescripciónArray", functions.split(col("Descripción"), ", "))
    .filter(array_contains(col("DescripciónArray"), "Extended Edition"))

  println("****************************** WHEN OTHREWISE **************************************")
  carsDF.withColumn("Oferta", when(col("Año") < 1999, true).otherwise(false))
  carsDF.withColumn("Oferta", when(col("Año") < 1999, true).otherwise(false))
    .withColumn("OfertaNueva",
      functions.expr("case when Modelo = 'E350' then 'YES' " +
        "when Modelo = 'Venture' then 'NO' " +
        "else 'Unknown' end"))

  println("****************************** COLLECT **************************************")

  val collection = carsDF.collect()
  val collectionAsList = carsDF.collectAsList()
  val listPrice: Array[Double] = carsDF.select("precio").filter(col("precio").isNotNull &&
    col("precio").isInstanceOf[Int] != 0)
    .collect().map(row => row.get(0).asInstanceOf[Double])

  val maps = collection.map(row => {
    val map = scala.collection.mutable.Map[String,Any]()
    row.schema.fields.foreach(k => map += (k.name -> row.getAs(k.name)))
    println(map)
    map
  })

  println(maps)

  println("****************************** COLLECT **************************************")



}
