package de.htwg.se.dominion.model

import scala.collection.immutable.List
import scala.util.Random

object GameStart {
  def createDeck(pAmount:Int): Unit ={
    val random = new Random

    val basicDeck : List[BasicCards] = List(BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.copper,
      BasicCards.copper, BasicCards.copper, BasicCards.mansion, BasicCards.mansion, BasicCards.mansion)

    val emptydeck : List[BasicCards] = List()
    val player1Deck : List[BasicCards] = random.shuffle(basicDeck)
    val player2Deck : List[BasicCards] = random.shuffle(basicDeck)
    val player3Deck : List[BasicCards] = random.shuffle(basicDeck)
    val player4Deck : List[BasicCards] = random.shuffle(basicDeck)
    val player5Deck : List[BasicCards] = random.shuffle(basicDeck)
    val player1Hand : List[BasicCards] = player1Deck.head :: player1Deck(1) :: player1Deck(2) :: player1Deck(3) :: player1Deck(4) :: emptydeck
    val player2Hand : List[BasicCards] = player2Deck.head :: player2Deck(1) :: player2Deck(2) :: player2Deck(3) :: player2Deck(4) :: emptydeck
    val player3Hand : List[BasicCards] = player3Deck.head :: player3Deck(1) :: player3Deck(2) :: player3Deck(3) :: player3Deck(4) :: emptydeck
    val player4Hand : List[BasicCards] = player4Deck.head :: player4Deck(1) :: player4Deck(2) :: player4Deck(3) :: player4Deck(4) :: emptydeck
    val player5Hand : List[BasicCards] = player5Deck.head :: player5Deck(1) :: player5Deck(2) :: player5Deck(3) :: player5Deck(4) :: emptydeck

    if (pAmount == 2) {
      println("Player 1 Hand: " + player1Hand.head.CardName + player1Hand(1).CardName + player1Hand(2).CardName + player1Hand(3).CardName + player1Hand(4).CardName)
      println("Player 2 Hand: " + player2Hand.head.CardName + player2Hand(1).CardName + player2Hand(2).CardName + player2Hand(3).CardName + player2Hand(4).CardName)
    } else if (pAmount == 3) {
      println("Player 1 Hand: " + player1Hand.head.CardName + player1Hand(1).CardName + player1Hand(2).CardName + player1Hand(3).CardName + player1Hand(4).CardName)
      println("Player 2 Hand: " + player2Hand.head.CardName + player2Hand(1).CardName + player2Hand(2).CardName + player2Hand(3).CardName + player2Hand(4).CardName)
      println("Player 3 Hand: " + player3Hand.head.CardName + player3Hand(1).CardName + player3Hand(2).CardName + player3Hand(3).CardName + player3Hand(4).CardName)
    } else if (pAmount == 4) {
      println("Player 1 Hand: " + player1Hand.head.CardName + player1Hand(1).CardName + player1Hand(2).CardName + player1Hand(3).CardName + player1Hand(4).CardName)
      println("Player 2 Hand: " + player2Hand.head.CardName + player2Hand(1).CardName + player2Hand(2).CardName + player2Hand(3).CardName + player2Hand(4).CardName)
      println("Player 3 Hand: " + player3Hand.head.CardName + player3Hand(1).CardName + player3Hand(2).CardName + player3Hand(3).CardName + player3Hand(4).CardName)
      println("Player 4 Hand: " + player4Hand.head.CardName + player4Hand(1).CardName + player4Hand(2).CardName + player4Hand(3).CardName + player4Hand(4).CardName)
    } else if (pAmount == 5) {
      println("Player 1 Hand: " + player1Hand.head.CardName + player1Hand(1).CardName + player1Hand(2).CardName + player1Hand(3).CardName + player1Hand(4).CardName)
      println("Player 2 Hand: " + player2Hand.head.CardName + player2Hand(1).CardName + player2Hand(2).CardName + player2Hand(3).CardName + player2Hand(4).CardName)
      println("Player 3 Hand: " + player3Hand.head.CardName + player3Hand(1).CardName + player3Hand(2).CardName + player3Hand(3).CardName + player3Hand(4).CardName)
      println("Player 4 Hand: " + player4Hand.head.CardName + player4Hand(1).CardName + player4Hand(2).CardName + player4Hand(3).CardName + player4Hand(4).CardName)
      println("Player 5 Hand: " + player5Hand.head.CardName + player5Hand(1).CardName + player5Hand(2).CardName + player5Hand(3).CardName + player5Hand(4).CardName)

    }
  }
}