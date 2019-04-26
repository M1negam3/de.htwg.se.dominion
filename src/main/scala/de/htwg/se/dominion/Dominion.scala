package de.htwg.se.dominion

import de.htwg.se.dominion.aview.InputOutput
import de.htwg.se.dominion.model._
import de.htwg.se.dominion.aview.Tui
import de.htwg.se.dominion.controller.Controller
import de.htwg.se.dominion.model.{Player, Deck}


object Dominion {

  val players: Vector[Player] = Vector(new Player(1))
  val controller = new Controller(new Deck, players)
  val tui = new Tui(controller)
  controller.notifyObservers()

  def main(args: Array[String]): Unit = {
    println("Commands:")
    println("q = quit")
    println("n = new Game")
    println("t = next Trun")
    while(true) {
      val input = scala.io.StdIn.readLine()
      tui.processInputLine(input)
    }
  }
}