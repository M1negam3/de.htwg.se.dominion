package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.deckComponent.Cards
import org.scalatest._

class GameInitSpec extends WordSpec with Matchers {
  val pCount = 2
  val l: List[Cards] = List(Cards.copper, Cards.copper, Cards.copper, Cards.copper,
    Cards.copper)
  val names: List[String] = List("Luca","Luis")

  "A GameInit" should {

    "have a getPlayerCount method" in {
      //GameInit.getPlayerCount() should be ()
    }

    "have a getPlayername method" in {
      //GameInit.getPlayerName(pCount) should be (names)
    }

  }
}
