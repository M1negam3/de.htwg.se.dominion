package de.htwg.se.dominion.model

import org.scalatest._

class InputOutputSpec extends WordSpec with Matchers {
  val pCount = 2
  val l: List[BasicCards] = List(BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.copper,
  BasicCards.copper)

  "A InputOutput" should {

    "have a getPlayerCount method" in {
    }

    "have a getPlayername method" in {
    }

    "have a deckcreation method" in {
      InputOutput.deckCreation(pCount) should startWith ("==> Deck ")
    }

    "have a HandCardCreation method" in {
      InputOutput.HandCardCreation(l, pCount) should be("Player " + pCount + "`s Hand Cards are: Copper, Copper, " +
        "Copper," + " Copper, Copper, ")
    }
  }
}