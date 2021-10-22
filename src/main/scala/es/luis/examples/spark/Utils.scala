package es.luis.examples.spark

import org.apache.spark.sql.SparkSession

object Utils {
  val PATCH_EXAMPLES = "src/main/resources/examples/"

  def getSparkSession(): SparkSession = SparkSession
    .builder()
    .appName("Spark")
    .master("local[*]")
    .getOrCreate()

}
