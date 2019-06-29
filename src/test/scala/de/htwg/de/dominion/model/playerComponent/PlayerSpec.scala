package de.htwg.se.dominion.model.playerComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent._
import org.scalatest._

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.List

class PlayerSpec extends WordSpec with Matchers {
  val pCount = 2
  val names: List[String] = List("Luca","Luis")
  val hand: List[Cards] = List(Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper)
  val silver: List[Cards] = List(Cards.silver,Cards.silver,Cards.silver,Cards.silver,Cards.silver)
  var Luca = new Player("Luca",0,Cards.startDeck,Cards.stacker,Cards.hand)
  var Luis = new Player("Luis",0,Cards.startDeck,Cards.stacker,hand)
  var list: List[Player] = List(Luca,Luis)
  var stacker: List[Cards] = Cards.startDeck
  var deck: List[Cards] = Nil
  var deck1: List[Cards] = List(Cards.copper)
  var deck2: List[Cards] = List(Cards.copper,Cards.copper)
  var deck3: List[Cards] = List(Cards.copper,Cards.copper,Cards.copper)
  var deck4: List[Cards] = List(Cards.copper,Cards.copper,Cards.copper,Cards.copper)
  var Luca0 = new Player("Luca",0,deck,stacker,Cards.hand)
  var Luca2 = new Player("Luca",0,deck2,stacker,Cards.hand)
  var Luca3 = new Player("Luca",0,deck3,stacker,Cards.hand)
  var Luca4 = new Player("Luca",0,deck4,stacker,Cards.hand)
  var Luca1 = new Player("Luca",0,deck1,stacker,Cards.hand)

  "A Player" when {
    "new" should {
      val player = Player("Your Name",0,Cards.startDeck,Cards.stacker,Cards.hand)
      "have a name" in {
        player.name should be("Your Name")
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
      Player().getHand(Luca) should not be (Cards.copper,Cards.copper,Cards.copper,Cards.copper)
      Player().getHand(Luca0) should not be (Cards.copper,Cards.copper,Cards.copper,Cards.copper)
      Player().getHand(Luca1) should not be (Cards.copper,Cards.copper,Cards.copper,Cards.copper)
      Player().getHand(Luca2) should not be (Cards.copper,Cards.copper,Cards.copper,Cards.copper)
      Player().getHand(Luca3) should not be (Cards.copper,Cards.copper,Cards.copper,Cards.copper)
      Player().getHand(Luca4) should not be (Cards.copper,Cards.copper,Cards.copper,Cards.copper)

    }
    "have a getMoney method" in {
      Player().getMoney(Luis) should not be be (5)
    }
    "have a updatePlayer method " in {
      Player().updatePlayer(list, Player().draw(Luca, 2)) should not be (Luca.hand.length == 4)
    }
    "have a draw method" in {
      Player().draw(Luca, 5) should not be (list.head.hand.length == 5)
      Player().draw(Luca2,5) should not be (list.head.hand.length == 5)
    }
    "have a upgrading method" in {
      Player().upgrading(Luis, 0, silver) should not be (Luis.hand.head == Cards.copper)
    }
    "have a isEmpty method " in {
      Player().isEmpty(Luca) should not be (Luca.stacker.length == 1)
    }
    "have a updateMoney method" in {
      Player().updateMoney(Luca, 5) should not be (Luca.money == 1)
    }
    "have a updateAction method" in {
      Player().updateAction(Luca, 1) should not be (Luca.actions == 4)
    }
  }
}