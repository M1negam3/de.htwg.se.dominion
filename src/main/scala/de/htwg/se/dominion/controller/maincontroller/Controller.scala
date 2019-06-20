package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.controller.ControllerInterface
import de.htwg.se.dominion.model._
import de.htwg.se.dominion.util._

import scala.collection.mutable.ListBuffer

class Controller(r: RoundManager) extends ControllerInterface {

  var phaseString = Output.printHeader()
  var gameInfoString = " "
  var state = "Init"
  val undoManager = new UndoManager
  var roundmanager = r
  var startRoundmanager = r
  var memory: ListBuffer[RoundManager] = ListBuffer()

  override def newGame(): Unit = {
    roundmanager = roundmanager.getNumberOfPlayers(roundmanager)
    roundmanager = roundmanager.getNames(roundmanager)
    roundmanager = roundmanager.createPlayer(roundmanager)
    gameInfoString = Output.printPlayers(roundmanager)
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
    roundmanager = roundmanager.playerTurn(RoundManager(roundmanager.players, roundmanager.numberOfRounds + 1,
      roundmanager.numberOfPlayers, roundmanager.names, roundmanager.score, roundmanager.idx + 1))
    if (GameTurn.end) {
      state = "end"
    }
  }

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
    undoManager.undoStep()
    notifyObservers
  }

  override def redo(): Unit = {
    undoManager.redoStep()
    memory -= memory.last
    roundmanager = memory.last
    phaseString = Output.printActionPhase() + Output.printTurn(roundmanager.idx)
    memory += roundmanager
    roundmanager = roundmanager.turn(roundmanager.idx ,roundmanager)
    phaseString = Output.printTurnEnd(roundmanager.idx) + GameTurn.endCheck(GameTurn.end)
    notifyObservers
  }
}