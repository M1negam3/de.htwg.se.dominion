package de.htwg.se.dominion.aview

import java.io.BufferedReader

import de.htwg.se.dominion.util.Observer
import de.htwg.se.dominion.controller.Controller


class Tui(controller: Controller) extends Observer {

  controller.add(this)
  var stopProcessingInput = false

  def processInput(input: BufferedReader): Unit = {
    while(!stopProcessingInput) {
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
      case "n" => controller.newGame()
      case "t" => controller.turn()
      case "e" => controller.endGame()
      case "h" => controller.help()
      case _ => println(Console.RED + "Input invalid, try again!")
    }
  }

  override def update(): Boolean = {print(controller.phaseString); true}
}