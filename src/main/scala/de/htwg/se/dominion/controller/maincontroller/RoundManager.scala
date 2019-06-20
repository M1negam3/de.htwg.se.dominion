package de.htwg.se.dominion.controller.maincontroller


import de.htwg.se.dominion.model._


case class RoundManager(players: List[Player] = Nil,
                        numberOfRounds: Int = 0,
                        numberOfPlayers: Int = 0,
                        names: List[String] = Nil,
                        score: Map[String, Int] = Map()) {

  def getNumberOfPlayers(roundManager: RoundManager) : RoundManager ={
    val copiedRoundManager = roundManager
    val copiedNumber = GameInit.getPlayerCount()
    RoundManager(copiedRoundManager.players,copiedRoundManager.numberOfRounds,copiedNumber,copiedRoundManager.names,copiedRoundManager.score)
  }

  def names(roundManager: RoundManager): RoundManager = {
    val copiedRoundManager = roundManager
    val copiedNames = GameInit.getPlayerName(roundManager.numberOfPlayers)
    RoundManager(copiedRoundManager.players,copiedRoundManager.numberOfRounds,copiedRoundManager.numberOfPlayers,copiedNames,copiedRoundManager.score)
  }

  def createPlayer (roundManager: RoundManager) : RoundManager = {
    val copiedRoundManager = roundManager
    val Players = Player.createPlayer(roundManager.numberOfPlayers, roundManager.names)
    RoundManager(Players,copiedRoundManager.numberOfRounds,copiedRoundManager.numberOfPlayers,copiedRoundManager.names,copiedRoundManager.score)
  }

  def actionPhase(roundManager: RoundManager, idx: Int): RoundManager = {
    val copiedRoundManager = roundManager
    val action = GameTurn.actionPhase(roundManager.players, idx)
    val copiedNumberOfRounds = roundManager.numberOfRounds+1
    RoundManager(action,copiedNumberOfRounds,copiedRoundManager.numberOfPlayers,copiedRoundManager.names,copiedRoundManager.score)
  }

  def buyPhase(roundManager: RoundManager, idx: Int) : RoundManager = {
    val copiedRoundManager = roundManager
    val buy = GameTurn.buyPhase(roundManager.players, idx)
    RoundManager(buy,copiedRoundManager.numberOfRounds,copiedRoundManager.numberOfPlayers,copiedRoundManager.names,copiedRoundManager.score)
  }

  def score(roundManager: RoundManager): RoundManager = {
    val copiedRoundManager = roundManager
    val copiedScore = GameEnd.score(roundManager.players)
    RoundManager(copiedRoundManager.players,copiedRoundManager.numberOfRounds,copiedRoundManager.numberOfPlayers,copiedRoundManager.names,copiedScore)
  }

}

object RoundManager {
  case class Builder() {
    var numberOfPlayers: Int = 0
    var numberOfRounds: Int = 0

    def addNumberOfPlayers(players: Int): Builder = {
      numberOfPlayers = players
      this
    }

    def addNumberOfRounds(rounds: Int): Builder = {
      numberOfRounds = rounds
      this
    }

    def build(): RoundManager = {
      RoundManager(List[Player](), numberOfRounds, numberOfPlayers, List[String](), Map[String, Int]())
    }
  }
}