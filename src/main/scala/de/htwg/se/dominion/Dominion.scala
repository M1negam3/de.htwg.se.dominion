package de.htwg.se.dominion

import de.htwg.se.dominion.aview.InputOutput
import de.htwg.se.dominion.model._
import de.htwg.se.dominion.aview.Tui
import de.htwg.se.dominion.controller.Controller
import de.htwg.se.dominion.model.{Player, Deck}


object Dominion {
  var name = "Dominion"
  val players: Vector[Player] = Vector(new Player(1))
  val controller = new Controller(new Deck, players)
  val tui = new Tui(controller)
  controller.notifyObservers()



  def main(args: Array[String]): Unit = {
    var input: String = ""
    do {
      input = readLine
      tui.processInputLine(input)
    } while (input != "q")
  }
}
