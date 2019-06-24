package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.model.gameComponent._
import de.htwg.se.dominion.model.playerComponent.Player
import scala.collection.mutable.ListBuffer

case class RoundManager(players: List[Player] = List(),
                        names: List[String] = List(),
                        numberOfPlayer: Int = 0,
                        playerturn: Int = 0,
                        score: Map[Int, String] = Map()) {

  def getNames(r: RoundManager, name: String): RoundManager = {
    val copiedRoundManagerRe = r
    val names = GameInit.getPlayerName(copiedRoundManagerRe.names, name)
    RoundManager(copiedRoundManagerRe.players, names, copiedRoundManagerRe.numberOfPlayer, copiedRoundManagerRe.playerturn, copiedRoundManagerRe.score)
  }

  def getNameSetupStrings(): String = {
    "     Player " + (playerturn + 1) + ", please enter your name:"
  }

  def nextPlayer(): Int = {
    if (playerturn < numberOfPlayer - 1) {
      playerturn + 1
    } else {
      0
    }
  }

  def createPlayer(r: RoundManager): List[Player] = {
    val copiedRoundManagerRe = r
    val p = Player.createPlayer(copiedRoundManagerRe.numberOfPlayer, copiedRoundManagerRe.names)
    p
  }

  def actionPhase(r: RoundManager): List[Player] = {
    val copiedRoundManagerRe = r
    val p = GameTurn.actionPhase(copiedRoundManagerRe.players, copiedRoundManagerRe.playerturn)
    p
  }

  def actionPhase2(r: RoundManager, cardnumber: Int): List[Player] = {
    val copiedRoundManagerRe = r
    val p = GameTurn.actionPhase2(copiedRoundManagerRe.players, copiedRoundManagerRe.playerturn, cardnumber)
    p
  }

  def editStringValue(r: RoundManager, newStringValue: Int): List[Player] = {
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

  def actionCardEffect1(r: RoundManager, input: Int): List[Player] = {
    val copiedRoundManagerRe = r
    val l = StrategyPatternForActionPhase.getCardname(copiedRoundManagerRe.players, copiedRoundManagerRe.playerturn, input.toInt)
    l
  }

  def actionCardEffect2(r: RoundManager, input: String): List[Player] = {
    val copiedRoundManagerRe = r
    val l = StrategyPatternForActionPhase.getCardName2(copiedRoundManagerRe.players, copiedRoundManagerRe.playerturn, input)
    l
  }

  def updateActions(r: RoundManager): List[Player] = {
    val copiedRoundManagerRe = r
    var p = copiedRoundManagerRe.players
    var l: ListBuffer[Player] = new ListBuffer[Player]
    for (i <- 0 until p.length) {
      if (i == copiedRoundManagerRe.playerturn) {
        l += new Player(p(copiedRoundManagerRe.playerturn).name, p(copiedRoundManagerRe.playerturn).value, p(copiedRoundManagerRe.playerturn).deck, p(copiedRoundManagerRe.playerturn).stacker, p(copiedRoundManagerRe.playerturn).hand, List(), (p(copiedRoundManagerRe.playerturn).actions - 1), p(copiedRoundManagerRe.playerturn).buys, p(copiedRoundManagerRe.playerturn).stringValue, p(copiedRoundManagerRe.playerturn).money)
      } else {
        l += p(i)
      }
    }
    val o: List[Player] = l.toList
    o
  }

  def updateMoney(r: RoundManager, money: Int): List[Player] = {
    val copiedRoundManagerRe = r
    var p = copiedRoundManagerRe.players
    var q: ListBuffer[Player] = ListBuffer()
    for (i <- 0 until copiedRoundManagerRe.players.length) {
      if (i == copiedRoundManagerRe.playerturn) {
        q += new Player(p(copiedRoundManagerRe.playerturn).name, p(copiedRoundManagerRe.playerturn).value, p(copiedRoundManagerRe.playerturn).deck, p(copiedRoundManagerRe.playerturn).stacker, p(copiedRoundManagerRe.playerturn).hand, p(copiedRoundManagerRe.playerturn).playingCards, p(copiedRoundManagerRe.playerturn).actions, p(copiedRoundManagerRe.playerturn).buys, p(copiedRoundManagerRe.playerturn).stringValue, money)
      } else {
        q += p(i)
      }
    }
    val l = q.toList
    l
  }
  def updateActions(r: RoundManager, actions: Int): List[Player] = {
    val copiedRoundManagerRe = r
    var p = copiedRoundManagerRe.players
    var q: ListBuffer[Player] = ListBuffer()
    for (i <- 0 until copiedRoundManagerRe.players.length) {
      if (i == copiedRoundManagerRe.playerturn) {
        q += new Player(p(copiedRoundManagerRe.playerturn).name, p(copiedRoundManagerRe.playerturn).value, p(copiedRoundManagerRe.playerturn).deck, p(copiedRoundManagerRe.playerturn).stacker, p(copiedRoundManagerRe.playerturn).hand, p(copiedRoundManagerRe.playerturn).playingCards, actions, p(copiedRoundManagerRe.playerturn).buys, p(copiedRoundManagerRe.playerturn).stringValue, p(copiedRoundManagerRe.playerturn).money)
      } else {
        q += p(i)
      }
    }
    val l = q.toList
    l
  }

  def getHand(r: RoundManager): List[Player] = {
    val copiedRoundManagerRe = r
    var l = copiedRoundManagerRe.players
    l = Player.updatePlayer(l, Player.getHand(l(copiedRoundManagerRe.playerturn)))
    l
  }

  def end(r: RoundManager): List[Player] = {
    val copiedRoundManagerRe = r
    var l = copiedRoundManagerRe.players
    l = GameEnd.end(l)
    l
  }

  def score(r: RoundManager): Map[Int, String] = {
    val copiedRoundManagerRe = r
    val score = GameEnd.score(copiedRoundManagerRe.players)
    score
  }
}
