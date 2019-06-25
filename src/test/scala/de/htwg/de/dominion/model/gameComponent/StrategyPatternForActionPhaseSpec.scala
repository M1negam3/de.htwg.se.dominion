package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent._
import de.htwg.se.dominion.model.gameComponent._
import de.htwg.se.dominion.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class StrategyPatternForActionPhaseSpec extends WordSpec with Matchers{


  var Luca = new Player("Luca",0,Cards.startDeck,Cards.stacker,Cards.hand)
  var Luis = new Player("Luis",0,Cards.startDeck,Cards.stacker,Cards.hand)
  var l: List[Player] = List(Luca,Luis)
  var i = 0
  var playingCards: List[Cards] = List(Cards.cellar,Cards.mine,Cards.remodel,Cards.workshop,Cards.merchant)
  "A StrategyPatternForAction" should {
    "have a strategy" ignore {
      var strategy = playingCards.head.CardName match {
        //case "Cellar" => StrategyPatternForActionPhase.cellar(l, i) should be (l)
        //case "Mine" => StrategyPatternForActionPhase.mine(l, i) should be (l)
        //case "Remodel" => StrategyPatternForActionPhase.remodel(l, i) should be (l)
        //case "Workshop" => StrategyPatternForActionPhase.workshop(l, i) should be (l)
        //case "Merchant" =>
        case _ => GameTurn.l should be (GameTurn.l)
      }
    }

  }
}
