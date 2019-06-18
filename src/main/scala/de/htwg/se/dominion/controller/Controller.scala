package de.htwg.se.dominion.controller
import de.htwg.se.dominion.model._
import de.htwg.se.dominion.util.Observable

class Controller() extends Observable {
  var pCount = 0
  var players: List[Player] = Nil
  var phaseString = Output.printHeader()
  var playerTurn = 0

  def newGame(): Unit = {
    pCount = GameInit.getPlayerCount()
    val names = GameInit.getPlayerName(pCount)
    players = Player.createPlayer(pCount, names)
    phaseString = Output.printPrep()
    notifyObservers
  }

  def turn(): Unit = {
    phaseString = Output.printActionPhase() + Output.printTurn(playerTurn)
    notifyObservers
    players = RoundLogic.actionPhase(players, playerTurn)
    playerTurn += 1
  }

  def help(): Unit = {
    phaseString = Output.printRules()
    notifyObservers
  }

  def endGame(): Unit = {
    notifyObservers
  }
}