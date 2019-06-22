package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.deckComponent.Cards
import de.htwg.se.dominion.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class StrategyPatternForActionPhaseSpec extends WordSpec with Matchers{


  var Luca = new Player("Luca",0,Cards.startDeck,Cards.stacker,Cards.hand)
  var Luis = new Player("Luis",0,Cards.startDeck,Cards.stacker,Cards.hand)
  var list: List[Player] = List(Luca,Luis)
  "A StrategyPatternForAction" should {
    "have a case Cellar" in {

    }
    "have a case Mine" in {

    }
    "have a case  Remodel" in {

    }
    "have a case Workshop " in {

    }
    "have a case Merchant" in {

    }
  }
}
