package de.htwg.se.dominion.aview

import java.io.BufferedReader

import de.htwg.se.dominion.util.Observer
import de.htwg.se.dominion.controller.Controller


class Tui(controller: Controller) extends Observer {

  controller.add(this)
  var stopProcessingInput = false
  print(printHeader())

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
      case _ =>
    }
  }

  def printHeader(): String = {
    """
    ╔═══════════════════════════════════════════ Dominion ════════════════════════════════════════════════╗

                                        Press "n" to START a NEW Game!
                                        Press "q" to QUIT the Game!

    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝
    """.stripMargin
  }
  override def update(): Boolean = {println(); true}
}