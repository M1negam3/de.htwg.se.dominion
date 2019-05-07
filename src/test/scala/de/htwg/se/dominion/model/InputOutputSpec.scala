package de.htwg.se.dominion.model

import org.scalatest._

class InputOutputSpec extends WordSpec with Matchers {
  val pCount = 1

  "A InputOutput" should {
    /*"have a getPlayerCount method" in {
      InputOutput.getPlayerCount() should be ()
    }*/
    "have a deckcreation method" in {
      InputOutput.deckCreation(pCount) should not be ("==> Deck " + pCount + " is created!")
    }
    /*"have a getPlayername method" in {
      InputOutput.getPlayerName(pCount) should be (1)
    }*/
  }
}
