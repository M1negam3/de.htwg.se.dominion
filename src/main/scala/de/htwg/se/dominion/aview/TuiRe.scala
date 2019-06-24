package de.htwg.se.dominion.aview

import java.io.BufferedReader

import de.htwg.se.dominion.controller.maincontroller.{ControllerRe, GameStatus}
import de.htwg.se.dominion.util.Observer

class TuiRe(controller: ControllerRe) extends Observer {

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
      case "r" => controller.redo()
      case _ => controller.eval(input)
    }
  }

    override def update(): Boolean = {
      println(GameStatus.message(controller.gameStatus))
      println(controller.getCurrentStateAsString)

      controller.gameStatus = GameStatus.IDLE
      true
    }
}
