package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.controller.ControllerInterface
import de.htwg.se.dominion.model._
import de.htwg.se.dominion.util._

class Controller(r: RoundManager) extends ControllerInterface {

  var phaseString = Output.printHeader()
  var state = "Init"
  val undoManager = new UndoManager
  var roundmanager = r
  var startRoundmanager = r

  override def newGame(): Unit = {
    roundmanager = roundmanager.getNumberOfPlayers(roundmanager)
    roundmanager = roundmanager.getNames(roundmanager)
    roundmanager = roundmanager.createPlayer(roundmanager)
    startRoundmanager = roundmanager
    phaseString = Output.printPrep()
    state = "turn"
    notifyObservers
  }

  override def turn(): Unit = {
    roundmanager = roundmanager.playerTurn(roundmanager)
    phaseString = Output.printActionPhase() + Output.printTurn(roundmanager.idx)
    notifyObservers
    undoManager.doStep(new turnCommand(roundmanager.idx,this))
    phaseString = Output.printTurnEnd(roundmanager.idx) + GameTurn.endCheck(GameTurn.end)
    notifyObservers
    roundmanager = roundmanager.playerTurn(RoundManager(roundmanager.players, roundmanager.numberOfRounds,
      roundmanager.numberOfPlayers, roundmanager.names, roundmanager.score, roundmanager.idx + 1))
    roundmanager = roundmanager.roundNumber(roundmanager)
    if (GameTurn.end) {
      state = "end"
    }
  }

  /*override def turn(): Unit = {
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

  override def help(): Unit = {
    phaseString = Output.printRules()
    notifyObservers
  }

   override def endGame(): Unit = {
    roundmanager = roundmanager.score(roundmanager.end(roundmanager))
    phaseString = Output.printScore(roundmanager.score)
    notifyObservers
  }

  override def undo(): Unit ={
    println("UNDO")
    undoManager.undoStep()
    notifyObservers
  }

  override def redo(): Unit = {
    println("REDO")
    undoManager.redoStep()
    notifyObservers
  }
}