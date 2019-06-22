package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.model._
import de.htwg.se.dominion.model.gameComponent.{GameEnd, GameInit, GameTurn}
import de.htwg.se.dominion.model.playerComponent.Player
import de.htwg.se.dominion.model.stringComponent.Output
import de.htwg.se.dominion.util.Observable

import scala.collection.mutable.ListBuffer

case class RoundManager (players: List[Player] = Nil,
                        numberOfRounds: Int = 0,
                        numberOfPlayers: Int = 0,
                        names: List[String] = Nil,
                        score: Map[Int, String] = Map(),
                        idx: Int = 0,
                        gameInfo: String = "") {

  def getNumberOfPlayers(roundManager: RoundManager): RoundManager = {
      val copiedRoundManager = roundManager
      val copiedNumber = GameInit.getPlayerCount()
      new RoundManager(copiedRoundManager.players, copiedRoundManager.numberOfRounds, copiedNumber, copiedRoundManager.names, copiedRoundManager.score, copiedRoundManager.idx, "")
    }

    def getNames(roundManager: RoundManager): RoundManager = {
      val copiedRoundManager = roundManager
      val copiedNames = GameInit.getPlayerName(roundManager.numberOfPlayers)
      new RoundManager(copiedRoundManager.players, copiedRoundManager.numberOfRounds, copiedRoundManager.numberOfPlayers, copiedNames, copiedRoundManager.score, copiedRoundManager.idx, "")
    }

    def createPlayer(roundManager: RoundManager): RoundManager = {
      val copiedRoundManager = roundManager
      val Players = Player.createPlayer(roundManager.numberOfPlayers, roundManager.names)
      new RoundManager(Players, copiedRoundManager.numberOfRounds, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedRoundManager.score, copiedRoundManager.idx, "")
    }

    def actionPhase(roundManager: RoundManager): RoundManager = {
      val copiedRoundManager = roundManager
      val action = GameTurn.actionPhase(roundManager.players, roundManager.idx)
      new RoundManager(action, copiedRoundManager.numberOfRounds, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedRoundManager.score, copiedRoundManager.idx, "")
    }

    def buyPhase(roundManager: RoundManager): RoundManager = {
      val copiedRoundManager = roundManager
      val buy = GameTurn.buyPhase(roundManager.players, roundManager.idx)
      new RoundManager(buy, copiedRoundManager.numberOfRounds, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedRoundManager.score, copiedRoundManager.idx, "")
    }

    def score(roundManager: RoundManager): RoundManager = {
      val copiedRoundManager = roundManager
      val copiedScore = GameEnd.score(roundManager.players)
      new RoundManager(copiedRoundManager.players, copiedRoundManager.numberOfRounds, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedScore, copiedRoundManager.idx, "")
    }

    def playerTurn(roundManager: RoundManager): RoundManager = {
      val copiedRoundManager = roundManager
      val round = GameTurn.round(roundManager.numberOfPlayers, roundManager.idx)
      new RoundManager(copiedRoundManager.players, copiedRoundManager.numberOfRounds, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedRoundManager.score, round, "")
    }

  def roundNumber(roundManager: RoundManager): RoundManager = {
    val copiedRoundManager = roundManager
    val roundNumber = copiedRoundManager.numberOfRounds
    if (copiedRoundManager.idx == 0) {
      new RoundManager(copiedRoundManager.players, roundNumber + 1, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedRoundManager.score, copiedRoundManager.idx, "")
      return this
    }
    this
  }

  def gameInit(r: RoundManager): RoundManager = {
    var copiedRoundManager = r
    copiedRoundManager = getNumberOfPlayers(copiedRoundManager)
    copiedRoundManager = getNames(copiedRoundManager)
    copiedRoundManager = createPlayer(copiedRoundManager)
    GameTurn.end = false
    GameTurn.empty = 0
    copiedRoundManager
  }

  def turn(playerturn: Int, r: RoundManager): RoundManager = {
    val copiedRoundManager = r
    var turnRoundManager = new RoundManager(copiedRoundManager.players, copiedRoundManager.numberOfRounds,
      copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedRoundManager.score, playerturn, "")
    turnRoundManager = actionPhase(turnRoundManager)
    turnRoundManager = buyPhase(turnRoundManager)
    turnRoundManager
  }

  def end(r: RoundManager): RoundManager = {
    val copiedRoundManager = r
    val end = GameEnd.end(copiedRoundManager.players)
    val score = GameEnd.score(end)
    new RoundManager(end, copiedRoundManager.numberOfRounds, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, score, copiedRoundManager.idx, "")
  }

}

object RoundManager {

  case class Builder() {
    var numberOfPlayers: Int = 0
    var numberOfRounds: Int = 0
    var idx: Int = 0

    def addNumberOfPlayers(players: Int): Builder = {
      numberOfPlayers = players
      this
    }

    def addNumberOfRounds(rounds: Int): Builder = {
      numberOfRounds = rounds
      this
    }

    def addIdx(turn: Int): Builder = {
      idx = turn
      this
    }

    def build(): RoundManager = {
      new RoundManager(List[Player](), numberOfRounds, numberOfPlayers, List[String](), Map[Int, String](), idx, "")
    }
  }

}