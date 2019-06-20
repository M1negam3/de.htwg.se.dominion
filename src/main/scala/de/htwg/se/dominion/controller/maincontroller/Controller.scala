package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.controller.ControllerInterface
import de.htwg.se.dominion.model._
import de.htwg.se.dominion.util._

class Controller(r: RoundManager) extends ControllerInterface {
  var pCount = 0
  var playerTurn = 0
  var players: List[Player] = Nil
  var cPlayers: List[Player] = Nil
  var phaseString = Output.printHeader()
  var state = "Init"
  val undoManager = new UndoManager
  var RoundManager = r

  /*def newGame(): Unit = {
    pCount = GameInit.getPlayerCount()
    val names = GameInit.getPlayerName(pCount)
    players = Player.createPlayer(pCount, names)
    phaseString = Output.printPrep()
    cPlayers = players
    state = "turn"
    notifyObservers
  }*/

  def newGame(): Unit = {
    RoundManager = RoundManager.getNumberOfPlayers(RoundManager)
    RoundManager = RoundManager.getNames(RoundManager)
    RoundManager = RoundManager.createPlayer(RoundManager)
    phaseString = Output.printPrep()
    state = "turn"
    notifyObservers
  }

  /*def turn(): Unit = {
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
  }*/

  def turn(): Unit = {
    undoManager.doStep(new turnCommand(RoundManager, this))
    notifyObservers
  }

  /*def turn(): Unit = {
    RoundManager = RoundManager.round(RoundManager)
    phaseString = Output.printActionPhase() + Output.printTurn(RoundManager.idx)
    notifyObservers
    RoundManager = RoundManager.actionPhase(RoundManager)
    phaseString = Output.printBuyPhase()
    notifyObservers
    RoundManager = RoundManager.buyPhase(RoundManager)
    phaseString = Output.printTurnEnd(playerTurn) + GameTurn.endCheck(GameTurn.end)
  }*/

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
  def undo(): Unit ={
    undoManager.undoStep()
    notifyObservers
  }
  def redo(): Unit = {
    undoManager.redoStep()
    notifyObservers
  }
}