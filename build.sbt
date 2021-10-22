name := "tutorial"

version := "0.1"

scalaVersion := "2.12.9"

val typesafeVersion = "1.4.1"

val sparkVersion = "3.1.2"

val AkkaVersion = "2.6.16"

val AkkaHttpVersion = "10.2.6"

val scalatestVersion = "3.2.10"

val sprayJsonVersion = "1.3.6"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % typesafeVersion,
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-testkit" % AkkaVersion,
  "org.scalatest" %% "scalatest" % scalatestVersion,
  "io.spray" %% "spray-json" % sprayJsonVersion
)