package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent._
import de.htwg.se.dominion.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}
class GameEndSpec extends WordSpec with Matchers{
  var deck: List[Cards] =List(Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.estate,Cards.estate,Cards.gardens)
  var Luca = new Player("Luca",0,deck,Cards.stacker,Cards.hand)
  var Luis = new Player("Luis",0,Cards.startDeck,Cards.stacker,Cards.hand)
  var list: List[Player] = List(Luca,Luis)
  var finished: List[(Int,String)] = List((4,"Luca"),(3,"Luis"))
  "A GameEnd" should {
    "have a end method" in {
      GameEnd.end(list).head.stacker.length should  be (0)
    }
    "have a score method" ignore {
      GameEnd.score(list) should  be (finished)
    }
  }
}
