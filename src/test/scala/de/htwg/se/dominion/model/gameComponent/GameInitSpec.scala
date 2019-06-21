package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.deckComponent.Cards
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
