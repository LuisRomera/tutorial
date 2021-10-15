name := "tutorial"

version := "0.1"

scalaVersion := "2.12.9"

val typesafeVersion = "1.4.1"

val sparkVersion = "3.1.2"

val AkkaVersion = "2.6.16"


libraryDependencies ++= Seq(
  "com.typesafe" % "config" % typesafeVersion,
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test
)