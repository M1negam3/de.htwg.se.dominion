package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.deckComponent.Cards
import de.htwg.se.dominion.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class StrategyPatternForActionPhaseSpec extends WordSpec with Matchers{


  var Luca = new Player("Luca",0,Cards.startDeck,Cards.stacker,Cards.hand)
  var Luis = new Player("Luis",0,Cards.startDeck,Cards.stacker,Cards.hand)
  var l: List[Player] = List(Luca,Luis)
  var i = 0
  var playingCards: List[Cards] = List(Cards.cellar,Cards.mine,Cards.remodel,Cards.workshop,Cards.merchant)
  "A StrategyPatternForAction" should {
    "have a strategy" in {
      var strategy = playingCards.head.CardName match {
        case "Cellar" => GameTurn.cellar(l, i) should be (l)
        case "Mine" => GameTurn.mine(l, i) should be (l)
        case "Remodel" => GameTurn.remodel(l, i) should be (l)
        case "Workshop" => GameTurn.workshop(l, i) should be (l)
        case "Merchant" =>
          GameTurn.money = GameTurn.money + GameTurn.merchant(l, i)
          l should be (GameTurn.l)
        case _ => GameTurn.l should be (GameTurn.l)
      }
    }

  }
}
