package de.htwg.se.dominion

import de.htwg.se.dominion.aview.Tui
import de.htwg.se.dominion.controller.Controller

object Dominion {

  val controller = new Controller()
  val tui = new Tui(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    var input: String = ""
    if (args.length > 0) input = args(0)
    if (!input.isEmpty) tui.processInputLine(input)
    else do {
      input = scala.io.StdIn.readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}