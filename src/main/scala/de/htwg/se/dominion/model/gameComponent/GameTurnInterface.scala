package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Card
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player

trait GameTurnInterface {

  def actionPhase(list: List[Player], index: Int): List[Player]

  def actionPhase2(list: List[Player], index: Int, cardnumber: Int): List[Player]

  def buyPhase(list: List[Player], index: Int, input: Int): List[Player]

  def updateStacker(p: Player, c: Card): Player

  def addCardToHand(p : Player, idx: Int): Player

  def removeHandcard(i: Int, player: Player): Player

  def copyList(cards: List[Card]): List[Card]

  def updateDeck(l: List[List[Card]], o: List[Card], i: Int): List[List[Card]]

  def updatePlayingDecks(l: List[List[Card]], idx: Int): List[List[Card]]

  def round(pCount: Int, playerTurn: Int): Int

  def endCheck(end: Boolean): String

  def getMoney(player: Player): Int

  def clearHand(list: List[Player], idx : Int): List[Player]

  def getCardsWCost4(): List[Int]

  def getCardsWC(): List[Int]

}
