package de.htwg.se.dominion.model

import scala.collection.immutable.List
import scala.util.Random

object Deck {

  val random = new Random
  val basicDeck: List[BasicCards] = List(BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.copper,
    BasicCards.copper, BasicCards.copper, BasicCards.mansion, BasicCards.mansion, BasicCards.mansion)

  def shuffle(l: List[BasicCards]): List[BasicCards] ={
    val shuffleList: List[BasicCards] = random.shuffle(l)
    shuffleList
  }

  def getHand(l: List[BasicCards]): List[BasicCards] = {
    val hand: List[BasicCards] = List(l.head, l(1), l(2), l(3), l(4))
    hand
  }

  def hand(l: List[BasicCards]) : List[BasicCards] = {
    val p: List[BasicCards] = shuffle(l)
    val hand: List[BasicCards] = getHand(p)
    hand
  }
}
