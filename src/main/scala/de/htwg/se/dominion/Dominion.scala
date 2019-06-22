package de.htwg.se.dominion

import de.htwg.se.dominion.aview.{Tui, TuiRe}
import de.htwg.se.dominion.controller.maincontroller.{Controller, ControllerRe, RoundManager}

object Dominion {

  val controller = new ControllerRe(new RoundManager())
  val tui = new TuiRe(controller)
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