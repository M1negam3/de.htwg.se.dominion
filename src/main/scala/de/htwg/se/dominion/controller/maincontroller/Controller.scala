package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.controller.ControllerInterface
import de.htwg.se.dominion.model._

class Controller() extends ControllerInterface {
  var pCount = 0
  var playerTurn = 0
  var players: List[Player] = Nil
  var cPlayers: List[Player] = Nil
  var phaseString = Output.printHeader()
  var state = "Init"

  def newGame(): Unit = {
    pCount = GameInit.getPlayerCount()
    val names = GameInit.getPlayerName(pCount)
    players = Player.createPlayer(pCount, names)
    phaseString = Output.printPrep()
    cPlayers = players
    state = "turn"
    notifyObservers
  }

  def turn(): Unit = {
    playerTurn = GameTurn.round(pCount, playerTurn)
    phaseString = Output.printActionPhase() + Output.printTurn(playerTurn)
    notifyObservers
    cPlayers = GameTurn.actionPhase(cPlayers, playerTurn)
    phaseString = Output.printBuyPhase()
    notifyObservers
    cPlayers = GameTurn.buyPhase(cPlayers, playerTurn)
    phaseString = Output.printTurnEnd(playerTurn) + GameTurn.endCheck(GameTurn.end)
    playerTurn += 1
    if (GameTurn.end) {
      state = "end"
    }
    notifyObservers
  }

  def help(): Unit = {
    phaseString = Output.printRules()
    notifyObservers
  }

  def endGame(): Unit = {
    val fPlayers = GameEnd.end(cPlayers)
    val score = GameEnd.score(fPlayers)
    phaseString = Output.printScore(score)
    notifyObservers
  }
}