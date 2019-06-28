package de.htwg.se.dominion.model.stringComponent

import de.htwg.se.dominion.model.playerComponent.Player

trait OutputInterface {

  def printHeader(): String

  def printPrep(): String

  def printNextTurn(): String

  def printActionPhase(): String

  def printBuyPhase(): String

  def printTurn(idx: Int): String

  def printTurnEnd(idx: Int): String

  def printEnd(): String

  def printScore(m: List[(Int, String)]): String

  def printPlayerQuestion(): String

  def getPlayingStateString(l: List[Player], playerturn: Int, stringValue : Int): String
}
