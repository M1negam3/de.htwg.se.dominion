package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.controller.ControllerInterface
import de.htwg.se.dominion.controller.maincontroller.GameStatus._
import de.htwg.se.dominion.model.gameComponent.GameTurn
import de.htwg.se.dominion.model.stringComponent.Output
import de.htwg.se.dominion.util._

import scala.collection.mutable.ListBuffer

class Controller(r: RoundManager) extends ControllerInterface {

  var gameStatus: GameStatus = GameStatus.PREP
  var gameInfoString = ""
  var phaseString = Output.printHeader()
  var state = "Init"
  private val undoManager = new UndoManager
  var roundmanager = r
  var startRoundmanager = r
  var memory: ListBuffer[RoundManager] = ListBuffer()

  /*override def newGame(): Unit = {
    gameInfoString = Output.printPlayerQuestion()
    phaseString = ""
    notifyObservers
    roundmanager = roundmanager.getNumberOfPlayers(roundmanager)
    roundmanager = roundmanager.getNames(roundmanager)
    roundmanager = roundmanager.createPlayer(roundmanager)
    gameInfoString = Output.printPlayers(roundmanager)
    startRoundmanager = roundmanager
    phaseString = Output.printPrep()
    state = "turn"
    notifyObservers
  }*/

  override def newGame(): Unit = {
    gameInfoString = Output.printPlayerQuestion()
    notifyObservers
    roundmanager = roundmanager.gameInit(roundmanager)
    startRoundmanager = roundmanager
    gameStatus = GameStatus.FTURN
    gameInfoString = ""
    state = "turn"
    notifyObservers
  }

  override def turn(): Unit = {
    gameInfoString = ""
    roundmanager = roundmanager.playerTurn(roundmanager)
    phaseString = Output.printActionPhase() + Output.printTurn(roundmanager.idx)
    notifyObservers
    undoManager.doStep(new SetCommand(roundmanager.idx,this))
    phaseString = Output.printTurnEnd(roundmanager.idx) + GameTurn.endCheck(GameTurn.end)
    gameStatus = GameStatus.TURN
    notifyObservers

    roundmanager = roundmanager.playerTurn(RoundManager(roundmanager.players, roundmanager.numberOfRounds + 1,
      roundmanager.numberOfPlayers, roundmanager.names, roundmanager.score, roundmanager.idx + 1))
    if (GameTurn.end) {
      state = "end"
      gameStatus = GameStatus.END
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
  }

  trait ControllerState {

    def evaluate(input: String): Unit

    def getCurrentStateAsString: String

    def nextState: ControllerState

  }

   def getPlayerString(roundManager: RoundManager): String = {
     getPlayerString(roundmanager)
   }
}