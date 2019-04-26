package de.htwg.se.dominion.aview

import de.htwg.se.dominion.util.Observer
import de.htwg.se.dominion.controller.Controller


class Tui(controller: Controller) extends Observer {

  controller.add(this)

  def processInputLine(input: String): Unit = {
    input match {
      case "q" => controller.finish()
      case "n" => controller.newGame()
      case "t" => controller.turn()
      case _ => println(s"Wanna try one of these? ${controller.suggestions}")
    }
  }
  override def update(): Unit = println()
}