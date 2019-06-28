package de.htwg.de.dominion.model.gameComponent.gameTurnComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent._
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Card
import de.htwg.se.dominion.model.gameComponent.gameTurnComponent.StrategyPatternForActionPhase
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class StrategyPatternForActionPhaseSpec extends WordSpec with Matchers{

  var hand: List[Card] = List(Card.copper,Card.merchant,Card.silver,Card.copper,Card.copper)
  var Luca = new Player("Luca",0,Card.startDeck,Card.stacker,Card.hand)
  var Luca1 = new Player("Luca",0,Card.startDeck,Card.stacker,Card.hand,Nil,1,0,22)
  var Luca2 = new Player("Luca",0,Card.startDeck,Card.stacker,Card.hand,Nil,1,0,20,2)
  var Luca3 = new Player("Luca",0,Card.startDeck,Card.stacker,Card.hand,Nil,1,0,21)
  var Luca4 = new Player("Luca",0,Card.startDeck,Card.stacker,Card.hand,Nil,1,0,40)


  var Luca5 = new Player("Luca",0,Card.startDeck,Card.stacker,hand,Nil,1,0)

  var Luis = new Player("Luis",0,Card.startDeck,Card.stacker,Card.hand)
  var l: List[Player] = List(Luca,Luis)
  var l1: List[Player] = List(Luca1,Luis)
  var l2: List[Player] = List(Luca2,Luis)
  var l3: List[Player] = List(Luca3,Luis)
  var l4: List[Player] = List(Luca4,Luis)
  var l5: List[Player] = List(Luca5,Luis)
  var l6: List[Player] = List(Luca2,Luis)

  var i = 0
  var playingCards: List[Card] = List(Card.cellar,Card.mine,Card.remodel,Card.workshop,Card.merchant)
  "A StrategyPatternForAction" should {
    "have a strategy" in {
      //var strategy = playingCards.head.CardName match {
        //case "Cellar" => StrategyPatternForActionPhase.cellar(l, i) should be (l)
        //case "Mine" => StrategyPatternForActionPhase.mine(l, i) should be (l)
        //case "Remodel" => StrategyPatternForActionPhase.remodel(l, i) should be (l)
        //case "Workshop" => StrategyPatternForActionPhase.workshop(l, i) should be (l)
        //case "Merchant" =>
        //case _ => GameTurn.l should be (GameTurn.l)
      }
      "have a cellar method" in {
        StrategyPatternForActionPhase.cellar2(l,0,"0 1") should be (l)
      }
      "have a cellar2 method" in {
        StrategyPatternForActionPhase.cellar(l,0,0) should be (l)
      }
      "have a mine method" in {
        StrategyPatternForActionPhase.mine(l,0,0) should be (l)
      }
      "have a remodel method" in {
        StrategyPatternForActionPhase.remodel(l,0,0) should be (l)
      }
      "have a remodel2 method" in {
        StrategyPatternForActionPhase.remodel2(l,0,0) should be (l)
      }
      "have a workshop method " in {
        StrategyPatternForActionPhase.workshop(l,0,0) should be (l)
      }
      "have a merchant method" in {
        StrategyPatternForActionPhase.merchant(l,0) should be (l)
        //StrategyPatternForActionPhase.merchant(l5,0) should be (l6)
      }
    }

}
