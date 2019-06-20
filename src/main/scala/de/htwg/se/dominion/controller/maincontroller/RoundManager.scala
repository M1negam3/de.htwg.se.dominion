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
      val copiedNumberOfRounds = roundManager.numberOfRounds + 1
      RoundManager(action, copiedNumberOfRounds, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedRoundManager.score, copiedRoundManager.idx)
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

    def round(roundManager: RoundManager): RoundManager = {
      val copiedRoundManager = roundManager
      val round = GameTurn.round(roundManager.numberOfPlayers,roundManager.idx)
      RoundManager(copiedRoundManager.players, copiedRoundManager.numberOfRounds, copiedRoundManager.numberOfPlayers, copiedRoundManager.names, copiedRoundManager.score, round)
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

    def addIdx(turn: Int): Build = {
      idx = turn
      this
    }

    def build(): RoundManager = {
      RoundManager(List[Player](), numberOfRounds, numberOfPlayers, List[String](), Map[String, Int](), idx)
    }
  }
}