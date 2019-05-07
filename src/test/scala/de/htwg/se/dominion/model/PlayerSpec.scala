package de.htwg.se.dominion.model

import org.scalatest._

import scala.collection.immutable.List

class PlayerSpec extends WordSpec with Matchers {

  val emptylist: List[BasicCards] = List()
  val player = Player(0,"test",emptylist,emptylist)
  "A player" should {
    "have a toString method" in {
      player.toString should be ("test")
    }
    "have a value" in {
      player.value should be (0)
    }
    "have a deck" in {
      player.deck should be (emptylist)
    }
    "have a hand" in {
      player.hand should be (emptylist)
    }
  }
}