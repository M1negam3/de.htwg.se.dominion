package de.htwg.se.dominion.controller.maincontroller


import de.htwg.se.dominion.model._


case class RoundManager(players: List[Player] = Nil,
                        numberOfRounds: Int = 0,
                        numberOfPlayers: Int = 0,
                        names: List[String] = Nil,
                        score: Map[String, Int] = Map()) {

  def getnumberOfPlayers() : RoundManager ={
     val copiedNumber = GameInit.getPlayerCount()
    RoundManager(players,numberOfRounds,copiedNumber,names,score)
  }

  def names(numberOfPlayers: Int): RoundManager = {
    val copiedNames = GameInit.getPlayerName(numberOfPlayers)
    RoundManager(players,numberOfRounds,numberOfPlayers,copiedNames,score)
  }

  def createPlayer (numberOfPlayers: Int, names: List[String]) : RoundManager = {
    val Players = Player.createPlayer(numberOfPlayers, names)
    RoundManager(Players,0,numberOfPlayers,names,score)
  }

  def actionPhase(players: List[Player],idx: Int, numberOfRounds: Int): RoundManager = {
    val action = GameTurn.actionPhase(players, 5)
    val copiedNumberOfRounds = numberOfRounds+1
    RoundManager(action,copiedNumberOfRounds,numberOfPlayers,names,score)
  }

  def buyPhase(players: List[Player], idx: Int, numberOfRounds: Int) : RoundManager = {
    val buy = GameTurn.buyPhase(players, 5)
    RoundManager(buy,numberOfRounds,numberOfPlayers,names,score)
  }

  def score(players: List[Player]): RoundManager = {
    val copiedScore = GameEnd.score(players)
    RoundManager(players,numberOfRounds,numberOfPlayers,names,copiedScore)
  }
}
