package de.htwg.de.dominion.model.playerComponent.basePlayerComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Card
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.playerInterface
import org.scalatest._

import scala.collection.immutable.List

class PlayerSpec extends WordSpec with Matchers {
  val pCount = 2
  val names: List[String] = List("Luca","Luis")
  val hand: List[Card] = List(Card.copper,Card.copper,Card.copper,Card.copper,Card.copper)
  val silver: List[Card] = List(Card.silver,Card.silver,Card.silver,Card.silver,Card.silver)
  var Luca = Player("Luca",0,Card.startDeck,Card.stacker,Card.hand)
  var Luis = Player("Luis",0,Card.startDeck,Card.stacker,hand)
  var list: List[playerInterface] = List(Luca,Luis)
  var stacker: List[Card] = Card.startDeck
  var deck: List[Card] = Nil
  var deck1: List[Card] = List(Card.copper)
  var deck2: List[Card] = List(Card.copper,Card.copper)
  var deck3: List[Card] = List(Card.copper,Card.copper,Card.copper)
  var deck4: List[Card] = List(Card.copper,Card.copper,Card.copper,Card.copper)
  var Luca0 = Player("Luca",0,deck,stacker,Card.hand)
  var Luca2 = Player("Luca",0,deck2,stacker,Card.hand)
  var Luca3 = Player("Luca",0,deck3,stacker,Card.hand)
  var Luca4 = Player("Luca",0,deck4,stacker,Card.hand)
  var Luca1 = Player("Luca",0,deck1,stacker,Card.hand)

  "A Player" when {
    "new" should {
      val player = Player("Your Name",0,Card.startDeck,Card.stacker,Card.hand)
      "have a name" in {
        player.getGetName should be("Your Name")
      }
      "have a nice String representation" in {
        player.toString should be("Your Name")
      }
    }
  }
  "A player" should {
    "have a createPlayer method" in {
      Player().createPlayer(2,names) should not be (list.head == Luis)
    }
    "have a getHand method" in {
      Player().getGetHand(Luca) should not be (Card.copper,Card.copper,Card.copper,Card.copper)
      Player().getGetHand(Luca0) should not be (Card.copper,Card.copper,Card.copper,Card.copper)
      Player().getGetHand(Luca1) should not be (Card.copper,Card.copper,Card.copper,Card.copper)
      Player().getGetHand(Luca2) should not be (Card.copper,Card.copper,Card.copper,Card.copper)
      Player().getGetHand(Luca3) should not be (Card.copper,Card.copper,Card.copper,Card.copper)
      Player().getGetHand(Luca4) should not be (Card.copper,Card.copper,Card.copper,Card.copper)

    }
    "have a getMoney method" in {
      Player().getGetMoney(Luis) should not be be (5)
    }
    "have a updatePlayer method " in {
      Player().updatePlayer(list, Player().draw(Luca, 2)) should not be (Luca.getHand.length == 4)
    }
    "have a draw method" in {
      Player().draw(Luca, 5) should not be (list.head.getHand.length == 5)
      Player().draw(Luca2,5) should not be (list.head.getHand.length == 5)
    }
    "have a upgrading method" in {
      Player().upgrading(Luis, 0, silver) should not be (Luis.getHand.head == Card.copper)
    }
    "have a isEmpty method " in {
      Player().isEmpty(Luca) should not be (Luca.getStacker.length == 1)
    }
    "have a updateMoney method" in {
      Player().updateMoney(Luca, 5) should not be (Luca.getMoney == 1)
    }
    "have a updateAction method" in {
      Player().updateAction(Luca, 1) should not be (Luca.getActions == 4)
    }
  }
}
