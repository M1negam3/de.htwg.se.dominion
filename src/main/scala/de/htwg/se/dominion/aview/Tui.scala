package de.htwg.se.dominion.aview

import java.io.BufferedReader

import de.htwg.se.dominion.controller.maincontroller.Controller
import de.htwg.se.dominion.util.Observer


class Tui(controller: Controller) extends Observer {

  controller.add(this)
  var stopProcessingInput = false

  def processInput(input: BufferedReader): Unit = {
    while (!stopProcessingInput) {
      if (input.ready()) {
        val line = input.readLine()
        processInputLine(line)
      } else {
        Thread.sleep(200)
      }
    }
  }

  def processInputLine(input: String): Unit = {
    input match {
      case "q" =>
      case "u" => controller.undo()
      case "r" => if (controller.memory.length > 1) {controller.redo()}
      case "n" => controller.newGame()
      case "t" => if (controller.state.equals("turn")) { controller.turn() }
      case "e" => if (controller.state.equals("end")) { controller.endGame() }
      case "h" => controller.help()
      case _ => println(Console.RED + "Input invalid, try again!")
    }
  }

  override def update(): Boolean = {
    print(
      controller.gameInfoString +
      controller.phaseString); true
  }
}