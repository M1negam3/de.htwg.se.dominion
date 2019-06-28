package de.htwg.de.dominion.model.gameComponent.gameTurnComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent._
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.{Cards, cellarHeadDeck, copperHeadDeck, duchyHeadDeck, estateHeadDeck, festivalHeadDeck, gardensHeadDeck, goldHeadDeck, marketHeadDeck, merchantHeadDeck, mineHeadDeck, provinceHeadDeck, remodelHeadDeck, silverHeadDeck, smithyHeadDeck, villageHeadDeck, workshopHeadDeck}
import de.htwg.se.dominion.model.gameComponent.gameTurnComponent.GameTurn
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player
import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.List

class GameTurnSpec extends WordSpec with Matchers{
  var x: List[Int]= List(0,1,3,6,7,8,9,10,11,12)
  var y: List[Int]= List(0)
  val hand: List[Cards] = List(Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper)
  var hand1: List[Cards] = List(Cards.copper,Cards.copper,Cards.copper,Cards.copper)
  val hand2: List[Cards] = List(Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper)
  var deck: List[Cards] = List(Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper)
  var Luca = new Player("Luca",0,Cards.startDeck,Nil,hand,hand)
  var Luis = new Player("Luis",0,Cards.startDeck,Cards.stacker,hand)
  var Luca1 = new Player("Luca",0,Cards.startDeck,Cards.stacker,hand1,hand)
  var Luca2 = new Player("Luca",0,deck,Cards.stacker,hand,hand)
  var Luca3 = new Player("Luca",0,deck,Cards.stacker,hand2,hand)
  var Luca4 = new Player("Luca",0,Cards.startDeck,List(Cards.copper),hand,hand)
  var list: List[Player] = List(Luca,Luis)
  var end = true
  var copiedCards1: List[Cards] = List(Cards.copper)
  var copiedCards2: List[Cards] = List(Cards.copper,Cards.copper)
  var copiedCards3: List[Cards] = List(Cards.silver,Cards.silver)
  var listlist: List[List[Cards]] = List(copiedCards1,copiedCards2)
  var listlist2: List[List[Cards]] = List(copiedCards1,copiedCards3)

  "a GameTurn" should {
    "have a actionPhase method " in {

    }
    "have an actionPhase2 method" in {

    }
    "have a buyPhase method" in {

    }
    "have an updateStacker method" in {
      GameTurn.updateStacker(Luca, Cards.copper) should be (Luca4)
    }
    "have an addCardToHand method" in {
      GameTurn.addCardToHand(Luca2, 0) should be
    }
    "have a removeHandCard method " in {
      GameTurn.removeHandcard(0, Luca) should be (Luca1)
    }
    "have a copyList method " in {
      GameTurn.copyList(copiedCards2) should be (copiedCards1)
    }
    "have an updateDeck method" in{
      GameTurn.updateDeck(listlist,List(Cards.silver,Cards.silver), 1) should be (listlist2)
    }
    "have an updatePlayingDecks method" ignore {
      var test: List[List[Cards]] =List(copperHeadDeck.copperDeck, silverHeadDeck.silverDeck, goldHeadDeck.goldDeck,
        estateHeadDeck.estateDeck, provinceHeadDeck.provinceDeck, duchyHeadDeck.duchyDeck, villageHeadDeck.villageDeck, festivalHeadDeck.festivalDeck,
        cellarHeadDeck.cellarDeck, mineHeadDeck.mineDeck, smithyHeadDeck.createDeck, remodelHeadDeck.remodelDeck, merchantHeadDeck.merchantDeck,
        workshopHeadDeck.workshopDeck, gardensHeadDeck.gardensDeck, marketHeadDeck.marketDeck)
      GameTurn.updatePlayingDecks(GameTurn.playingDecks, 19) should be (test)
    }
    "have a round method" in {
      GameTurn.round(2,3) should be (0)
    }
    "have an endCheck method" in {
      GameTurn.endCheck(end) should be (
        Console.BLACK +
          """
    ╔═══════════════════════════════════════════ Game End ════════════════════════════════════════════════╗

                                          Press q to QUIT the Game!

    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝
    """.stripMargin)
    }
    "have a getMoney method" in {
      GameTurn.getMoney(list(0)) should be (5)
    }
    "have a clearHand method" in {
      GameTurn.clearHand(list,0) should not be (list(0).hand.length == 1)
    }
    "have a getCardsWCost4" in {
      GameTurn.getCardsWCost4() should be (x)
    }
    "have a getCardsWC method " in {
      GameTurn.getCardsWC() should be(y)
    }
  }
}
