package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player

trait GameTurnInterface {

  def actionPhase(list: List[Player], index: Int): List[Player]

  def actionPhase2(list: List[Player], index: Int, cardnumber: Int): List[Player]

  def buyPhase(list: List[Player], index: Int, input: Int): List[Player]

  def updateStacker(p: Player, c: Cards): Player

  def addCardToHand(p : Player, idx: Int): Player

  def removeHandcard(i: Int, player: Player): Player

  def copyList(cards: List[Cards]): List[Cards]

  def updateDeck(l: List[List[Cards]], o: List[Cards], i: Int): List[List[Cards]]

  def updatePlayingDecks(l: List[List[Cards]], idx: Int): List[List[Cards]]

  def round(pCount: Int, playerTurn: Int): Int

  def endCheck(end: Boolean): String

  def getMoney(player: Player): Int

  def clearHand(list: List[Player], idx : Int): List[Player]

  def getCardsWCost4(): List[Int]

  def getCardsWC(): List[Int]

}
