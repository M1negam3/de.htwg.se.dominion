package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.model.gameComponent.{GameInitRe, GameTurnRe}
import de.htwg.se.dominion.model.playerComponent.Player

import scala.collection.mutable.ListBuffer

case class RoundManagerRe(players: List[Player] = List(),
                          names: List[String] = List(),
                          numberOfPlayer: Int = 0,
                          playerturn: Int = 0,
                          score: Map[Int, String] = Map()) {

  def getNames(r: RoundManagerRe, name: String): RoundManagerRe = {
    val copiedRoundManagerRe = r
    val names = GameInitRe.getPlayerName(copiedRoundManagerRe.names, name)
    RoundManagerRe(copiedRoundManagerRe.players, names, copiedRoundManagerRe.numberOfPlayer, copiedRoundManagerRe.playerturn, copiedRoundManagerRe.score)
  }

  def getNameSetupStrings(): String = {
    "Player " + (playerturn + 1) + ", please enter your name:"
  }

  def nextPlayer(): Int = {
    if (playerturn < numberOfPlayer - 1) {
      playerturn + 1
    } else {
      0
    }
  }

  def createPlayer(r: RoundManagerRe): List[Player] = {
    val copiedRoundManagerRe = r
    val p = Player.createPlayer(copiedRoundManagerRe.numberOfPlayer, copiedRoundManagerRe.names)
    p
  }

  def actionPhase(r: RoundManagerRe): List[Player] = {
    val copiedRoundManagerRe = r
    val p = GameTurnRe.actionPhase(copiedRoundManagerRe.players, copiedRoundManagerRe.playerturn)
    p
  }

  def actionPhase2(r: RoundManagerRe, cardnumber: Int): List[Player] = {
    val copiedRoundManagerRe = r
    val p = GameTurnRe.actionPhase2(copiedRoundManagerRe.players, copiedRoundManagerRe.playerturn, cardnumber)
    p
  }

  def editStringValue(r: RoundManagerRe, newStringValue: Int): List[Player] = {
    val copiedRoundManagerRe = r
    var p = copiedRoundManagerRe.players
    var q: ListBuffer[Player] = ListBuffer()
    for (i <- 0 until copiedRoundManagerRe.players.length) {
      if (i == copiedRoundManagerRe.playerturn) {
        q += new Player(p(copiedRoundManagerRe.playerturn).name, p(copiedRoundManagerRe.playerturn).value, p(copiedRoundManagerRe.playerturn).deck, p(copiedRoundManagerRe.playerturn).stacker, p(copiedRoundManagerRe.playerturn).hand, p(copiedRoundManagerRe.playerturn).playingCards, p(copiedRoundManagerRe.playerturn).actions, p(copiedRoundManagerRe.playerturn).buys, newStringValue, p(copiedRoundManagerRe.playerturn).money)
      } else {
        q += p(i)
      }
    }
    val l = q.toList
    l
  }

  def actionCardEffect(r: RoundManagerRe): List[Player] = {
    // TODO
    val copiedRoundManagerRe = r
    copiedRoundManagerRe.players
  }
}
