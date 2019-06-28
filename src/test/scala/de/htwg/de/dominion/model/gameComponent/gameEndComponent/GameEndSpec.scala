package de.htwg.de.dominion.model.gameComponent.gameEndComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent._
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.gameComponent.gameEndComponent.GameEnd
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player
import org.scalatest.{Matchers, WordSpec}
class GameEndSpec extends WordSpec with Matchers{
  var deck: List[Cards] =List(Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.estate,Cards.estate,Cards.gardens)
  var deck2: List[Cards] =List(Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.estate,Cards.estate,Cards.estate)
  var stacker: List[Cards] = List(Cards.copper,Cards.copper)
  var Luca = new Player("Luca",0,deck,stacker,Cards.hand)
  var Luis = new Player("Luis",0,deck2,stacker,Cards.hand)
  var list: List[Player] = List(Luca,Luis)
  var finished: List[(Int,String)] = List((3,"Luca"),(3,"Luis"))
  "A GameEnd" should {
    "have a end method" in {
      GameEnd().end(list).head.stacker.length should  be (0)
    }
    "have a score method" in {
      GameEnd().score(list) should  be (finished)
    }
  }
}