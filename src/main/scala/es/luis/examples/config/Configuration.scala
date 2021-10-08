package es.luis.examples.config

import com.typesafe.config.{Config, ConfigFactory}

class Configuration(val path: String) {

  private val config: Config = ConfigFactory.parseResources(path)

  val appName: String = config.getString("app.name")

  case class sparkConfig(name: String, config: (String, String))

}
