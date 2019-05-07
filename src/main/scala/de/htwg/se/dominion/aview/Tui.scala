package de.htwg.se.dominion.aview

import java.io.BufferedReader

import de.htwg.se.dominion.util.Observer
import de.htwg.se.dominion.controller.Controller


class Tui(controller: Controller) extends Observer {

  controller.add(this)
  var stopProcessingInput = false
  print(printHeader())

  def processInput(input: BufferedReader) = {
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
      case "s" => println(controller.suggestions)
      case _ => println(s"Wanna try one of these? ${controller.suggestions}")
    }
  }

  def printHeader(): String = {
    """
    ╔═══════════════════════════════════════════ Dominion ════════════════════════════════════════════════╗

                                        Press "n" to START a NEW Game!
                                        Press "q" to QUIT the Game!
                                        Press "t" for next Player
                                        Press "h" for Rules

    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝
    """.stripMargin
  }
  override def update(): Boolean = {println(); true}
}