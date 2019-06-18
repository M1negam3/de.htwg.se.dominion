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
    playerTurn = RoundLogic.round(pCount, playerTurn)
    phaseString = Output.printActionPhase() + Output.printTurn(playerTurn)
    notifyObservers
    players = RoundLogic.actionPhase(players, playerTurn)
    phaseString = Output.printBuyPhase()
    notifyObservers
    players = RoundLogic.buyPhase(players, playerTurn)
    phaseString = Output.prtintNextTurn()
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