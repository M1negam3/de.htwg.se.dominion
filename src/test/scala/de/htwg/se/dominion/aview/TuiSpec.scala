package de.htwg.se.dominion.aview

import de.htwg.se.dominion.controller.maincontroller.{Controller, RoundManager}
import org.scalatest._

class TuiSpec extends WordSpec with Matchers {

  "A Dominion Tui" should {
    val controller = new Controller(new RoundManager())
    val tui = new Tui(controller)
    "do nothing on input 'q'" in {
      tui.processInputLine("q")
    }
    "create a new Game on input 'n'" in {

    }
    "start the next turn on input 't'" in {
      tui.processInputLine("t")
    }
    "end the game on input 'e'" in {
      tui.processInputLine("e")
    }
    "undo on input 'u'" in {
      tui.processInputLine("u")
    }
    "redo on input 'r'" in {

    }
    "print the rules on input 'h'" in {
      tui.processInputLine("h")
    }
    "Suggest Options on bad input like '9999'" in {
      tui.processInputLine("9999")
    }
  }
}