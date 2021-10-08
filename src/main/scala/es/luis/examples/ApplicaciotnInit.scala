package es.luis.examples

import es.luis.examples.config.Configuration


object ApplicaciotnInit {

  def main(args: Array[String]): Unit = {
    val configuration = new Configuration(args(0))
    print(configuration.appName)
  }

}
