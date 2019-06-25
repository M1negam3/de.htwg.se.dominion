package de.htwg.de.dominion.model.gameComponent

import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.dominion.model.gameComponent.GameTurn

class GameTurnSpec extends WordSpec with Matchers{
  var x: List[Int]= List(0,1,2)
  var y: List[Int]= List(0,1,2)
  "a GameTurn" should {
    "have a actionPhase method " in {

    }
    "have an actionPhase2 method" in {

    }
    "have a buyPhase method" in {

    }
    "have an updateStacker method" in {

    }
    "have an addCardToHand method" in {

    }
    "have a removeHandCard method " in {

    }
    "have a copyList method " in {

    }
    "have an updateDeck method" in{

    }
    "have an updatePlayingDecks method" in {

    }
    "have a round method" in {

    }
    "have an endCheck method" in {

    }
    "have a getMoney method" in {

    }
    "have a clearHand method" in {

    }
    "have a getCardsWCost4" in {
      GameTurn.getCardsWCost4() should be (x)
    }
    "have a getCardsWC method " in {
      GameTurn.getCardsWC() should be(y)
    }
  }
}
