package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.controller.ControllerInterface
import de.htwg.se.dominion.controller.maincontroller.GameStatus._
import de.htwg.se.dominion.model.gameComponent.GameTurn
import de.htwg.se.dominion.model.stringComponent.Output
import de.htwg.se.dominion.util._

import scala.collection.mutable.ListBuffer

class Controller(r: RoundManager) {

  /*var gameStatus: GameStatus = GameStatus.PREP
  var gameInfoString = ""
  var phaseString = Output.printHeader()
  var state = "Init"
  private val undoManager = new UndoManager
  var roundmanager = r
  var startRoundmanager = r
  var memory: ListBuffer[RoundManager] = ListBuffer()

  def newGame(): Unit = {
    gameInfoString = Output.printPlayerQuestion()
    notifyObservers
    roundmanager = roundmanager.gameInit(roundmanager)
    startRoundmanager = roundmanager
    gameInfoString = Output.printPlayers(roundmanager)
    notifyObservers
    gameInfoString = ""
    gameStatus = GameStatus.FTURN
    state = "turn"
    notifyObservers
  }

  def turn(): Unit = {
    roundmanager = roundmanager.playerTurn(roundmanager)
    gameInfoString = Output.printActionPhase() + Output.printTurn(roundmanager.idx)
    notifyObservers
    undoManager.doStep(new SetCommand(roundmanager.idx,this))
    gameInfoString = Output.printTurnEnd(roundmanager.idx)
    if (GameTurn.end) {
      state = "end"
      gameStatus = GameStatus.END
      notifyObservers
      return
    }
    gameStatus = GameStatus.TURN
    notifyObservers
    roundmanager = roundmanager.playerTurn(RoundManager(roundmanager.players, roundmanager.numberOfRounds + 1,
      roundmanager.numberOfPlayers, roundmanager.names, roundmanager.score, roundmanager.idx + 1))
  }

  def help(): Unit = {
    phaseString = Output.printRules()
    notifyObservers
  }

   def endGame(): Unit = {
    roundmanager = roundmanager.end(roundmanager)
    gameInfoString = Output.printScore(roundmanager.score)
    notifyObservers
  }

  override def undo(): Unit = {
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
  }*/

}