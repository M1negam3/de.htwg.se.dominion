package de.htwg.de.dominion.model.gameComponent.gameEndComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent._
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Card
import de.htwg.se.dominion.model.gameComponent.gameEndComponent.GameEnd
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player
import org.scalatest.{Matchers, WordSpec}
class GameEndSpec extends WordSpec with Matchers{
  var deck: List[Card] =List(Card.copper,Card.copper,Card.copper,Card.copper,Card.copper,Card.copper,Card.copper,Card.estate,Card.estate,Card.gardens)
  var deck2: List[Card] =List(Card.copper,Card.copper,Card.copper,Card.copper,Card.copper,Card.copper,Card.copper,Card.estate,Card.estate,Card.estate)
  var stacker: List[Card] = List(Card.copper,Card.copper)
  var Luca = new Player("Luca",0,deck,stacker,Card.hand)
  var Luis = new Player("Luis",0,deck2,stacker,Card.hand)
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