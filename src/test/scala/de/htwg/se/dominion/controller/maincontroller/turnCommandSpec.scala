package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.model.deckComponent.Cards
import de.htwg.se.dominion.model.playerComponent.Player
import de.htwg.se.dominion.controller.maincontroller._
import org.scalatest._

class turnCommandSpec extends WordSpec with Matchers {
  var Luca = new Player("Luca",0,Cards.startDeck,Cards.stacker,Cards.hand)
  var Luis = new Player("Luis",0,Cards.startDeck,Cards.stacker,Cards.hand)
  var list: List[Player] = List(Luca,Luis)
  var names: List[String] = List("Luca","Luis")
  var score: Map[String, Int] = Map()
  var r = RoundManager(list,0,2,names,score,0)


  "A turnCommand" should {
    "have a undo method" in{

    }
    "have a redo method" in {

    }
    "have a do method " in {

    }
  }
}
