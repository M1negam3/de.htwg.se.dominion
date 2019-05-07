package de.htwg.se.dominion.model

import org.scalatest._

import scala.collection.immutable.List

class DeckSpec extends WordSpec with Matchers {
  val basicDeck: List[BasicCards] = List(BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.copper,
    BasicCards.copper, BasicCards.copper, BasicCards.mansion, BasicCards.mansion, BasicCards.mansion)
  val hand: List[BasicCards] = List(BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.copper,
    BasicCards.copper)
  "A deck" should {
    "have a getHand method" in {
        Deck.getHand(basicDeck) should be (hand)
      }
    "have a shuflle method " in {
      Deck.shuffle(basicDeck) should not be (basicDeck)
    }
    "have a hand method" in {
      Deck.hand(basicDeck) should not be (basicDeck)
    }

  }
}