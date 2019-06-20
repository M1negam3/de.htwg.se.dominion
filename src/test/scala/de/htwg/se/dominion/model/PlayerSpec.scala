package de.htwg.se.dominion.model

import org.scalatest._

import scala.collection.immutable.List

class PlayerSpec extends WordSpec with Matchers {
  val pCount = 2
  val names: List[String] = List("Luca","Luis")
  val hand: List[Cards] = List(Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper)
  val silver: List[Cards] = List(Cards.silver,Cards.silver,Cards.silver,Cards.silver,Cards.silver)
  var Luca = new Player("Luca",0,Cards.startDeck,Cards.stacker,Cards.hand)
  var Luis = new Player("Luis",0,Cards.startDeck,Cards.stacker,hand)
  var list: List[Player] = List(Luca,Luis)
  "A player" when {
    "new" should {
      "have a toString method" in {

      }
      "have a value" in {

      }
      "have a deck" in {

      }
      "have a hand" in {

      }
    }
  }
      "A player" should {
        "have a createPlayer method" in {
          Player.createPlayer(2,names) should not be (list.head == Luis)
        }
        "have a getHand method" in {
          Player.getHand(Luca) should not be (Cards.copper,Cards.copper,Cards.copper,Cards.copper)
        }
        "have a getMoney method" in {
          Player.getMoney(Luis) should not be be (5)
        }
        "have a updatePlayer method " in {
          Player.updatePlayer(list, Player.draw(Luca, 2)) should not be (Luca.hand.length == 4)
        }
        "have a draw method" in {
          Player.draw(Luca, 5) should not be (list.head.hand.length == 5)
        }
        "have a upgrading method" in {
          Player.upgrading(Luis, 0, silver) should not be (Luis.hand.head == Cards.copper)
        }
        "have a isEmpty method " in {
          Player.isEmpty(Luca) should not be (Luca.stacker.length == 1)
        }
      }
}
