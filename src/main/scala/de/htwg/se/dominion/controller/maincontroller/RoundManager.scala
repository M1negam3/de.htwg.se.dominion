package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Card
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent
import de.htwg.se.dominion.model.gameComponent.gameEndComponent.GameEnd
import de.htwg.se.dominion.model.gameComponent.gameInitComponent.GameInit
import de.htwg.se.dominion.model.gameComponent.gameTurnComponent.{GameTurn, StrategyPatternForActionPhase}
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player
import de.htwg.se.dominion.model.playerComponent.{PlayerInterface, StaticPlayerInterface}
import de.htwg.se.dominion.model.gameComponent.GameInitInterface
import de.htwg.se.dominion.model.gameComponent.GameEndInterface
import de.htwg.se.dominion.model.gameComponent.{GameTurnInterface, StaticGameTurnInterface}
import de.htwg.se.dominion.model.stringComponent.OutputInterface

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.{break, breakable}

case class RoundManager(players: List[PlayerInterface] = List(),
                        names: List[String] = List(),
                        numberOfPlayer: Int = 0,
                        playerturn: Int = 0,
                        score: List[(Int, String)] = List(),
                        playingDecks: List[List[Card]] = Card.playingDeck,
                        action: Boolean = true,
                        empty: Int = 0,
                        end: Boolean = false,
                        playerInterface: PlayerInterface  ,
                        staticPlayerInterface: StaticPlayerInterface,
                        gameInitInterface: GameInitInterface,
                        gameEndInterface: GameEndInterface,
                        gameTurnInterface: GameTurnInterface ,
                        staticGameTurnInterface: StaticGameTurnInterface
                       ) {

  def getNames(r: RoundManager, name: String): RoundManager = {
    val copiedRoundManagerRe = r
    val names = gameInitInterface.getPlayerName(copiedRoundManagerRe.names, name)
    RoundManager(copiedRoundManagerRe.players, names, copiedRoundManagerRe.numberOfPlayer, copiedRoundManagerRe.playerturn, copiedRoundManagerRe.score, playingDecks,copiedRoundManagerRe.action,copiedRoundManagerRe.empty,copiedRoundManagerRe.end,copiedRoundManagerRe.playerInterface,copiedRoundManagerRe.gameInitInterface,copiedRoundManagerRe.gameEndInterface)
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

  def createPlayer(r: RoundManager): List[PlayerInterface] = {
    val copiedRoundManagerRe = r
    val p = staticPlayerInterface.createPlayer(copiedRoundManagerRe.numberOfPlayer, copiedRoundManagerRe.names)
    p
  }

  def actionPhase(r: RoundManager): List[PlayerInterface] = {
    val copiedRoundManagerRe = r
    val p = gameTurnInterface.actionPhase(copiedRoundManagerRe.players, copiedRoundManagerRe.playerturn)
    p
  }

  def actionPhase2(r: RoundManager, cardnumber: Int): List[PlayerInterface] = {
    val copiedRoundManagerRe = r
    val p = gameTurnInterface.actionPhase2(copiedRoundManagerRe.players, copiedRoundManagerRe.playerturn, cardnumber)
    p
  }

  def editStringValue(r: RoundManager, newStringValue: Int): List[PlayerInterface] = {
    val copiedRoundManagerRe = r
    var p = copiedRoundManagerRe.players
    var q: ListBuffer[PlayerInterface] = ListBuffer()
    for (i <- 0 until copiedRoundManagerRe.players.length) {
      if (i == copiedRoundManagerRe.playerturn) {
        q += playerInterface(p(copiedRoundManagerRe.playerturn).getName, p(copiedRoundManagerRe.playerturn).getValue, p(copiedRoundManagerRe.playerturn).getDeck, p(copiedRoundManagerRe.playerturn).getStacker, p(copiedRoundManagerRe.playerturn).getHand, p(copiedRoundManagerRe.playerturn).getPlayingCards, p(copiedRoundManagerRe.playerturn).getActions, p(copiedRoundManagerRe.playerturn).getBuys, newStringValue, p(copiedRoundManagerRe.playerturn).getMoney)
      } else {
        q += p(i)
      }
    }
    val l = q.toList
    l
  }

  def actionCardEffect1(r: RoundManager, input: Int): List[PlayerInterface] = {
    val copiedRoundManagerRe = r
    val l = StrategyPatternForActionPhase(playerInterface,staticPlayerInterface,gameTurnInterface,staticGameTurnInterface).getCardname(copiedRoundManagerRe.players, copiedRoundManagerRe.playerturn, input.toInt)
    l
  }

  def actionCardEffect2(r: RoundManager, input: String): List[PlayerInterface] = {
    val copiedRoundManagerRe = r
    val l = StrategyPatternForActionPhase(playerInterface,staticPlayerInterface,gameTurnInterface,staticGameTurnInterface).getCardName2(copiedRoundManagerRe.players, copiedRoundManagerRe.playerturn, input)
    l
  }

  def updateActions(r: RoundManager): List[PlayerInterface] = {
    val copiedRoundManagerRe = r
    var p = copiedRoundManagerRe.players
    var l: ListBuffer[PlayerInterface] = new ListBuffer[PlayerInterface]
    for (i <- 0 until p.length) {
      if (i == copiedRoundManagerRe.playerturn) {
        l += playerInterface(p(copiedRoundManagerRe.playerturn).getName, p(copiedRoundManagerRe.playerturn).getValue, p(copiedRoundManagerRe.playerturn).getDeck, p(copiedRoundManagerRe.playerturn).getStacker, p(copiedRoundManagerRe.playerturn).getHand, List(), (p(copiedRoundManagerRe.playerturn).getActions - 1), p(copiedRoundManagerRe.playerturn).getBuys, p(copiedRoundManagerRe.playerturn).getStringValue, p(copiedRoundManagerRe.playerturn).getMoney)
      } else {
        l += p(i)
      }
    }
    val o: List[PlayerInterface] = l.toList
    o
  }

  def updateMoney(r: RoundManager, money: Int): List[PlayerInterface] = {
    val copiedRoundManagerRe = r
    var p = copiedRoundManagerRe.players
    var q: ListBuffer[PlayerInterface] = ListBuffer()
    for (i <- 0 until copiedRoundManagerRe.players.length) {
      if (i == copiedRoundManagerRe.playerturn) {
        q += playerInterface(p(copiedRoundManagerRe.playerturn).getName, p(copiedRoundManagerRe.playerturn).getValue, p(copiedRoundManagerRe.playerturn).getDeck, p(copiedRoundManagerRe.playerturn).getStacker, p(copiedRoundManagerRe.playerturn).getHand, p(copiedRoundManagerRe.playerturn).getPlayingCards, p(copiedRoundManagerRe.playerturn).getActions, p(copiedRoundManagerRe.playerturn).getBuys, p(copiedRoundManagerRe.playerturn).getStringValue, money)
      } else {
        q += p(i)
      }
    }
    val l = q.toList
    l
  }
  def updateActions(r: RoundManager, actions: Int): List[PlayerInterface] = {
    val copiedRoundManagerRe = r
    var p = copiedRoundManagerRe.players
    var q: ListBuffer[PlayerInterface] = ListBuffer()
    for (i <- 0 until copiedRoundManagerRe.players.length) {
      if (i == copiedRoundManagerRe.playerturn) {
        q += playerInterface(p(copiedRoundManagerRe.playerturn).getName, p(copiedRoundManagerRe.playerturn).getValue, p(copiedRoundManagerRe.playerturn).getDeck, p(copiedRoundManagerRe.playerturn).getStacker, p(copiedRoundManagerRe.playerturn).getHand, p(copiedRoundManagerRe.playerturn).getPlayingCards, actions, p(copiedRoundManagerRe.playerturn).getBuys, p(copiedRoundManagerRe.playerturn).getStringValue, p(copiedRoundManagerRe.playerturn).getMoney)
      } else {
        q += p(i)
      }
    }
    val l = q.toList
    l
  }

  def getHand(r: RoundManager): List[PlayerInterface] = {
    val copiedRoundManagerRe = r
    var l = copiedRoundManagerRe.players
    l = staticPlayerInterface.updatePlayer(l, staticPlayerInterface.getHand(l(copiedRoundManagerRe.playerturn)))
    l
  }

  def end(r: RoundManager): List[PlayerInterface] = {
    val copiedRoundManagerRe = r
    var l = copiedRoundManagerRe.players
    l =  gameEndInterface.end(l)
    l
  }

  def score(r: RoundManager): List[(Int, String)] = {
    val copiedRoundManagerRe = r
    val score = gameEndInterface.score(copiedRoundManagerRe.players)
    score
  }
}
