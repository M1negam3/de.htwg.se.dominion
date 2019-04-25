package de.htwg.se.dominion.model

import scala.collection.immutable.List
import scala.util.Random

object GameStart {
  def createDeck(pAmount:Int): Unit ={
    val random = new Random

    val basicDeck : List[BasicCards] = List(BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.copper,
      BasicCards.copper, BasicCards.copper, BasicCards.mansion, BasicCards.mansion, BasicCards.mansion)
    val player1Deck : List[BasicCards] = random.shuffle(basicDeck)
    val player1HandCards : List[BasicCards] = List(player1Deck.head, player1Deck(1), player1Deck(2), player1Deck(3),
      player1Deck(4))

    if (pAmount == 2) {
      val player1Deck : List[BasicCards] = random.shuffle(basicDeck)
      InputOutput.deckCreation(1)
      val player2Deck : List[BasicCards] = random.shuffle(basicDeck)
      InputOutput.deckCreation(2)

      val player1HandCards : List[BasicCards] = List(player1Deck.head, player1Deck(1), player1Deck(2), player1Deck(3),
        player1Deck(4))
      val player2HandCards : List[BasicCards] = List(player2Deck.head, player2Deck(1), player2Deck(2), player2Deck(3),
        player2Deck(4))

      InputOutput.HandCardCreation(player1HandCards, 1)
      InputOutput.HandCardCreation(player2HandCards, 2)
    } else if (pAmount == 3) {

      val player1Deck : List[BasicCards] = random.shuffle(basicDeck)
      InputOutput.deckCreation(1)
      val player2Deck : List[BasicCards] = random.shuffle(basicDeck)
      InputOutput.deckCreation(2)
      val player3Deck : List[BasicCards] = random.shuffle(basicDeck)
      InputOutput.deckCreation(3)

      val player1HandCards : List[BasicCards] = List(player1Deck.head, player1Deck(1), player1Deck(2), player1Deck(3),
        player1Deck(4))
      val player2HandCards : List[BasicCards] = List(player2Deck.head, player2Deck(1), player2Deck(2), player2Deck(3),
        player2Deck(4))
      val player3HandCards: List[BasicCards] = List(player3Deck.head, player3Deck(1), player3Deck(2), player3Deck(3),
        player3Deck(4))

      InputOutput.HandCardCreation(player1HandCards, 1)
      InputOutput.HandCardCreation(player2HandCards, 2)
      InputOutput.HandCardCreation(player3HandCards, 3)
    } else if (pAmount == 4) {

      val player1Deck : List[BasicCards] = random.shuffle(basicDeck)
      InputOutput.deckCreation(1)
      val player2Deck : List[BasicCards] = random.shuffle(basicDeck)
      InputOutput.deckCreation(2)
      val player3Deck : List[BasicCards] = random.shuffle(basicDeck)
      InputOutput.deckCreation(3)
      val player4Deck : List[BasicCards] = random.shuffle(basicDeck)
      InputOutput.deckCreation(4)

      val player1HandCards : List[BasicCards] = List(player1Deck.head, player1Deck(1), player1Deck(2), player1Deck(3),
        player1Deck(4))
      val player2HandCards : List[BasicCards] = List(player2Deck.head, player2Deck(1), player2Deck(2), player2Deck(3),
        player2Deck(4))
      val player3HandCards: List[BasicCards] = List(player3Deck.head, player3Deck(1), player3Deck(2), player3Deck(3),
        player3Deck(4))
      val player4HandCards: List[BasicCards] = List(player4Deck.head, player4Deck(1), player4Deck(2), player4Deck(3),
        player4Deck(4))

      InputOutput.HandCardCreation(player1HandCards, 1)
      InputOutput.HandCardCreation(player2HandCards, 2)
      InputOutput.HandCardCreation(player3HandCards, 3)
      InputOutput.HandCardCreation(player4HandCards, 4)
    } else if (pAmount == 5) {

      val player1Deck : List[BasicCards] = random.shuffle(basicDeck)
      InputOutput.deckCreation(1)
      val player2Deck : List[BasicCards] = random.shuffle(basicDeck)
      InputOutput.deckCreation(2)
      val player3Deck : List[BasicCards] = random.shuffle(basicDeck)
      InputOutput.deckCreation(3)
      val player4Deck : List[BasicCards] = random.shuffle(basicDeck)
      InputOutput.deckCreation(4)
      val player5Deck : List[BasicCards] = random.shuffle(basicDeck)
      InputOutput.deckCreation(5)

      val player1HandCards : List[BasicCards] = List(player1Deck.head, player1Deck(1), player1Deck(2), player1Deck(3),
        player1Deck(4))
      val player2HandCards : List[BasicCards] = List(player2Deck.head, player2Deck(1), player2Deck(2), player2Deck(3),
        player2Deck(4))
      val player3HandCards: List[BasicCards] = List(player3Deck.head, player3Deck(1), player3Deck(2), player3Deck(3),
        player3Deck(4))
      val player4HandCards: List[BasicCards] = List(player4Deck.head, player4Deck(1), player4Deck(2), player4Deck(3),
        player4Deck(4))
      val player5HandCards: List[BasicCards] = List(player5Deck.head, player5Deck(1), player5Deck(2), player5Deck(3),
        player5Deck(4))

      InputOutput.HandCardCreation(player1HandCards, 1)
      InputOutput.HandCardCreation(player2HandCards, 2)
      InputOutput.HandCardCreation(player3HandCards, 3)
      InputOutput.HandCardCreation(player4HandCards, 4)
      InputOutput.HandCardCreation(player5HandCards, 5)


    }
    val moneyplayer1 = player1HandCards.head.MoneyValue + player1HandCards(1).MoneyValue + player1HandCards.head.MoneyValue + player1HandCards(1).MoneyValue + player1HandCards.head.MoneyValue
    if(moneyplayer1 >)
  }
}