package de.htwg.se.dominion.model

import scala.collection.immutable.List
import scala.util.Random

case class GameStart(a : BasicCards, b : BasicCards, c : BasicCards, d : BasicCards, e : BasicCards, f : BasicCards, g : BasicCards, h : BasicCards, i : BasicCards, j : BasicCards) {
}

object GameStart {
  def createDeck(pAmount:Int): Unit ={
    val random = new Random

    val player1Deck = new GameStart(BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.copper,
      BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.mansion, BasicCards.mansion,
      BasicCards.mansion)
    val player2Deck = new GameStart(BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.copper,
      BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.mansion, BasicCards.mansion,
      BasicCards.mansion)
    val player3Deck = new GameStart(BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.copper,
      BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.mansion, BasicCards.mansion,
      BasicCards.mansion)
    val player4Deck = new GameStart(BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.copper,
      BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.mansion, BasicCards.mansion,
      BasicCards.mansion)
    val player5Deck = new GameStart(BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.copper,
      BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.mansion, BasicCards.mansion,
      BasicCards.mansion)

    /*
    val emptyhand : List[BasicCards] = List()
    val emptyhand2 : List[BasicCards] = List()
    val deckhand: List[BasicCards] = BasicCards.copper :: BasicCards.copper :: BasicCards.copper :: BasicCards.copper :: BasicCards.copper :: BasicCards.copper :: BasicCards.copper :: BasicCards.mansion :: BasicCards.mansion :: BasicCards.mansion :: emptyhand

    for (x <- 0 until pAmount) {
      val y : List[BasicCards] = random.shuffle(deckhand)
      val x : List[BasicCards] = y.head :: y(1) :: y(2) :: y(3) :: y(4) :: emptyhand2
      println(x)
    }*/
  }
}