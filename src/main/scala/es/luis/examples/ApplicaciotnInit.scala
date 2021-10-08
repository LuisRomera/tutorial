package es.luis.examples

import es.luis.examples.config.Configuration


object ApplicaciotnInit {

  def main(args: Array[String]): Unit = {
    new Configuration(args(0))
  }

}
