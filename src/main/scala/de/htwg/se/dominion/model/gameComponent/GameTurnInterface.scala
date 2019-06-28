package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Card
import de.htwg.se.dominion.model.playerComponent.PlayerInterface
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.playerInterface

trait GameTurnInterface {

  def actionPhase(list: List[PlayerInterface], index: Int): List[PlayerInterface]

  def actionPhase2(list: List[PlayerInterface], index: Int, cardnumber: Int): List[PlayerInterface]

  def buyPhase(list: List[PlayerInterface], index: Int, input: Int): List[PlayerInterface]

  def updateStacker(p: PlayerInterface, c: Card): PlayerInterface

  def addCardToHand(p : PlayerInterface, idx: Int): PlayerInterface

  def removeHandcard(i: Int, player: PlayerInterface): PlayerInterface

  def copyList(cards: List[Card]): List[Card]

  def updateDeck(l: List[List[Card]], o: List[Card], i: Int): List[List[Card]]

  def updatePlayingDecks(l: List[List[Card]], idx: Int): List[List[Card]]

  def round(pCount: Int, playerTurn: Int): Int

  def endCheck(end: Boolean): String

  def getMoney(player: PlayerInterface): Int

  def clearHand(list: List[PlayerInterface], idx : Int): List[PlayerInterface]

  def getCardsWCost4(): List[Int]

  def getCardsWC(): List[Int]

}
