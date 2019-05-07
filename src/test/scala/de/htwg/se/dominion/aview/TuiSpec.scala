package de.htwg.se.dominion.aview

import de.htwg.se.dominion.controller.Controller
import org.scalatest._

class TuiSpec extends WordSpec with Matchers {

  "A Dominion Tui" should {
    val controller = new Controller()
    val tui = new Tui(controller)
    "create a new Game on input 'n'" in {
    }
    "start the next turn on input 't'" in {
    }
    "Suggest Options on bad input like '9999'" in {
    }
    "terminate the game on input 'q'" in {
    }
  }
}