package de.htwg.se.dominion.model

import scala.collection.immutable.List
import scala.util.Random

object GameStart {
  def createDeck(pAmount:Int): Unit ={
    val random = new Random

    val basicDeck : List[BasicCards] = List(BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.copper,
      BasicCards.copper, BasicCards.copper, BasicCards.mansion, BasicCards.mansion, BasicCards.mansion)

    val player1Deck : List[BasicCards] = basicDeck
    val player2Deck : List[BasicCards] = basicDeck
    val player3Deck : List[BasicCards] = basicDeck
    val player4Deck : List[BasicCards] = basicDeck
    val player5Deck : List[BasicCards] = basicDeck

    if (pAmount == 2) {
      InputOutput.deckCreation(1)
      val player1Hand : List[BasicCards] = random.shuffle(player1Deck)
      InputOutput.deckCreation(2)
      val player2Hand : List[BasicCards] = random.shuffle(player2Deck)

      for (x <- 0 until 5) {
        val player1HandCards : List[BasicCards] = List(player1Hand(x))
        InputOutput.HandCardCreation(player1HandCards, 1)
        val player2HandCards : List[BasicCards] = List(player2Hand(x))
        InputOutput.HandCardCreation(player2HandCards, 2)
      }
    } else if (pAmount == 3) {
      InputOutput.deckCreation(3)
      val player3Hand : List[BasicCards] = random.shuffle(player3Deck)

      for (x <- 0 until 5) {
        val player3HandCards: List[BasicCards] = List(player3Hand(x))
        InputOutput.HandCardCreation(player3HandCards, 3)
      }
    } else if (pAmount == 4) {
      InputOutput.deckCreation(4)
      val player4Hand : List[BasicCards] = random.shuffle(player4Deck)

      for (x <- 0 until 5) {
        val player4HandCards: List[BasicCards] = List(player4Hand(x))
        InputOutput.HandCardCreation(player4HandCards, 4)
      }
    } else if (pAmount == 5) {
      InputOutput.deckCreation(5)
      val player5Hand : List[BasicCards] = random.shuffle(player5Deck)

      for (x <- 0 until 5) {
        val player5HandCards: List[BasicCards] = List(player5Hand(x))
        InputOutput.HandCardCreation(player5HandCards, 5)
      }
    }
  }
}