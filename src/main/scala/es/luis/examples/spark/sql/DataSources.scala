package es.luis.examples.spark.sql

import org.apache.spark.sql.{SaveMode, SparkSession}

object DataSources extends App {


  val spark = SparkSession
    .builder()
    .appName("Spark")
    .master("local[*]")
    .getOrCreate()

  import spark.implicits._

  val pathExamples = "src/main/resources/examples/"

  // Load json
  val df = spark.read.json(s"${pathExamples}people.json")

  // Parquet
  df.write.mode(SaveMode.Overwrite).parquet("people.parquet")
  val parquetFileDF = spark.read.parquet("people.parquet")

  parquetFileDF.show()

  // Load csv
  val peopleDFCsv = spark.read.format("csv")
    .option("sep", ";")
    .option("inferSchema", "true")
    .option("header", "true")
    .load(s"${pathExamples}/people.csv")

  peopleDFCsv.createOrReplaceTempView("people")

  val s = spark.sql("SELECT name from people where name = 'Bob'")
  s.show()

  // Run SQL on files directly
  spark.read.parquet(s"${pathExamples}users.parquet").show()
  val sqlDf = spark.sql(s"SELECT * FROM parquet.`${pathExamples}users.parquet`")
  sqlDf.show()




}
