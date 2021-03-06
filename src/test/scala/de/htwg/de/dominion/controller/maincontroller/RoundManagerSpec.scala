package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.controller.maincontroller._
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player
import org.scalatest._

class RoundManagerSpec extends WordSpec with Matchers {
  var players: List[Player] = Nil
  var numberOfPlayers: Int = 0
  var names: List[String] = Nil
  var score: List[(Int, String)] = Nil
  var playerturn: Int = 0
  var Luca = new Player("Luca",0,Cards.startDeck,Cards.stacker,Cards.hand,Nil,3,1,0,0)
  var Luis = new Player("Luis",0,Cards.startDeck,Cards.stacker,Cards.hand,Nil,1,1,0,0)
  var Luis2 = new Player("Luis",0,Cards.startDeck,Cards.stacker,Cards.hand,Nil,1,1,0,3)
  var Luis3 = new Player("Luis",0,Cards.startDeck,Cards.stacker,Cards.hand,Nil,0,1,0,3)
  var Luis4 = new Player("Luis",0,Cards.startDeck,Cards.stacker,Cards.hand,Nil,1,1,20,0)
  var Luca2 = new Player("Luis",0,Cards.startDeck,Cards.stacker,Cards.hand,Nil,3,1,20,0)
  var list: List[Player] = List(Luca,Luis)
  var list2: List[Player] = List(Luca,Luis2)
  var list3: List[Player] = List(Luca,Luis3)
  var list4: List[Player] = List(Luca2,Luis4)
  var r = RoundManager(players,names,numberOfPlayers,playerturn)
  var r2 = RoundManager(list,names,2,0)
  var r3 = RoundManager(list2,names,2,1)
  var r4 = RoundManager(list2,names,2,1)
  var r5 = RoundManager(list)
  var test2: List[Player] = List()



  "A Round Manager" should {
      "have this values" in {
        r.players should be(Nil)
        r.numberOfPlayer should be(0)
        r.names should be(Nil)
        r.score should be(score)
        r.playerturn should be(0)
      }
      "have a getNUmberOfPlayers method" ignore {

      }
      "have a getNameSetupStrings method" in {
        r.getNameSetupStrings() should be ("     Player " + (playerturn + 1) + ", please enter your name:")
      }
      "have a createPlayer method" in {
        r.createPlayer(r) should be (players)
      }
      "have a editStringValue method" in {
        r.editStringValue(r2, 20) should not be (list4)
      }
      "have a updateA1 method" in {
        r.updateActions(r3) should be (list3)
      }
      "have a score method " in {
        r.score(r) should be (score)
      }
      "have a update Money method" in {
        r.updateMoney(r, 3) should be (test2)
      }
      "have a updateActions method" in {
        r.updateActions(r3, 2) should not be (list)
      }
      "have a getHand method" in {
        r.getHand(r5) should  be (r5.players)
      }
      "have a end method" in {
        r.end(r) should be (r.players)
      }
    }
}
