package es.luis.examples.akka

abstract class AkkaTutorial (name: String) {

  def getName(): String = name

  def executeTuto(): Unit

  def start(): Unit = {

  }

}
