package de.htwg.se.dominion.model

import de.htwg.se.dominion.aview.Tui
import de.htwg.se.dominion.controller.maincontroller.Controller
import org.scalatest._

class GameInitSpec extends WordSpec with Matchers {
  val pCount = 2
  val l: List[Cards] = List(Cards.copper, Cards.copper, Cards.copper, Cards.copper,
    Cards.copper)

  "A GameInit" should {

    "have a getPlayerCount method" in {
    }

    "have a getPlayername method" in {
    }

  }
}
