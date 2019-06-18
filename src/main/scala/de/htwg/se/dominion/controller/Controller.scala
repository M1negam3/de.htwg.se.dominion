package de.htwg.se.dominion.controller
import de.htwg.se.dominion.model._
import de.htwg.se.dominion.util.Observable

class Controller() extends Observable {
  var pCount = 0
  var playerTurn = 0
  var players: List[Player] = Nil
  var phaseString = Output.printHeader()

  def newGame(): Unit = {
    pCount = GameInit.getPlayerCount()
    val names = GameInit.getPlayerName(pCount)
    players = Player.createPlayer(pCount, names)
    phaseString = Output.printPrep()
    notifyObservers
  }

  def turn(): Unit = {
    var cPlayers = players
    playerTurn = GameTurn.round(pCount, playerTurn)
    phaseString = Output.printActionPhase() + Output.printTurn(playerTurn)
    notifyObservers
    cPlayers = GameTurn.actionPhase(cPlayers, playerTurn)
    phaseString = Output.printBuyPhase()
    notifyObservers
    cPlayers = GameTurn.buyPhase(cPlayers, playerTurn)
    phaseString = Output.printTurnEnd(playerTurn) + Output.prtintNextTurn()
    playerTurn += 1
    notifyObservers
  }

  def help(): Unit = {
    phaseString = Output.printRules()
    notifyObservers
  }

  def endGame(): Unit = {
    notifyObservers
  }
}