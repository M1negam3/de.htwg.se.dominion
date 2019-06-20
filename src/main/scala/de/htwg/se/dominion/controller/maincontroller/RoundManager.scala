package de.htwg.se.dominion.controller.maincontroller


import de.htwg.se.dominion.model._


case class RoundManager(players: List[Player] = Nil,
                        numberOfRounds: Int = 0,
                        numberOfPlayers: Int = 0,
                        names: List[String] = Nil,
                        score: Map[String, Int] = Map(),
                        idx: Int = 0) {



  def getNumberOfPlayers(roundManager: RoundManager): RoundManager = {
      val copiedRoundManager = roundManager
      val copiedNumber = GameInit.getPlayerCount()
      RoundManager(copiedRoundManager.players, copiedRoundManager.numberOfRounds, copiedNumber, copiedRoundManager.names, copiedRoundManager.score, copiedRoundManager.idx)
    }

    def getNames(roundManager: RoundManager): RoundManager = {
      val copiedRoundManager = roundManager
      val copiedNames = GameInit.getPlayerName(roundManager.numberOfPlayers)
      RoundManager(copiedRoundManager.players, copiedRoundManager.numberOfRounds, copiedRoundManager.numberOfPlayers, copiedNames, copiedRoundManager.score, copiedRoundManager.idx)
    }

    def createPlayer(roundManager: RoundManager): RoundManager = {
      val copiedRoundManager = roundManager
      val Players = Player.createPlayer(roundManager.numberOfPlayers, roundManager.names)
      RoundManager(Players, copiedRoundManager.numberOfRounds, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedRoundManager.score, copiedRoundManager.idx)
    }

    def actionPhase(roundManager: RoundManager): RoundManager = {
      val copiedRoundManager = roundManager
      val action = GameTurn.actionPhase(roundManager.players, roundManager.idx)
      RoundManager(action, copiedRoundManager.numberOfRounds, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedRoundManager.score, copiedRoundManager.idx)
    }

    def buyPhase(roundManager: RoundManager): RoundManager = {
      val copiedRoundManager = roundManager
      val buy = GameTurn.buyPhase(roundManager.players, roundManager.idx)
      RoundManager(buy, copiedRoundManager.numberOfRounds, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedRoundManager.score, copiedRoundManager.idx)
    }

    def score(roundManager: RoundManager): RoundManager = {
      val copiedRoundManager = roundManager
      val copiedScore = GameEnd.score(roundManager.players)
      RoundManager(copiedRoundManager.players, copiedRoundManager.numberOfRounds, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedScore, copiedRoundManager.idx)
    }

    def playerTurn(roundManager: RoundManager): RoundManager = {
      val copiedRoundManager = roundManager
      val round = GameTurn.round(roundManager.numberOfPlayers, roundManager.idx)
      RoundManager(copiedRoundManager.players, copiedRoundManager.numberOfRounds, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedRoundManager.score, round)
    }

  def roundNumber(roundManager: RoundManager): RoundManager = {
    val copiedRoundManager = roundManager
    val roundNumber = copiedRoundManager.numberOfRounds
    if (copiedRoundManager.idx == 0) {
      RoundManager(copiedRoundManager.players, roundNumber + 1, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedRoundManager.score, copiedRoundManager.idx)
      return this
    }
    this
  }

  def turn(playerturn: Int, r: RoundManager): RoundManager = {
    val copiedRoundManager = r
    var turnRoundManager = RoundManager(copiedRoundManager.players, copiedRoundManager.numberOfRounds, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedRoundManager.score, playerturn)
    turnRoundManager = actionPhase(turnRoundManager)
    turnRoundManager = buyPhase(turnRoundManager)
    turnRoundManager
  }

  def end(r: RoundManager): RoundManager = {
    val copiedRoundManager = r
    val end = GameEnd.end(copiedRoundManager.players)
    RoundManager(end, copiedRoundManager.numberOfRounds, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedRoundManager.score, copiedRoundManager.idx)
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
      RoundManager(List[Player](), numberOfRounds, numberOfPlayers, List[String](), Map[String, Int](), idx)
    }
  }
}