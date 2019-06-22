package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.controller.ControllerInterface
import de.htwg.se.dominion.controller.maincontroller.GameStatus.GameStatus
import de.htwg.se.dominion.model.gameComponent.{GameInit, GameInitRe}
import de.htwg.se.dominion.model.stringComponent.Output
import de.htwg.se.dominion.util.UndoManager

import scala.collection.mutable.ListBuffer

class ControllerRe (r: RoundManager) extends ControllerInterface {

  private val undoManager = new UndoManager
  var gameStatus: GameStatus = GameStatus.PREP
  var roundmanager = r
  var startRoundmanager = r
  var memory: ListBuffer[RoundManager] = ListBuffer()
  var test = Output.printPlayerQuestion()

  def eval(input: String): Unit = {
    var m = GameInitRe.getPlayerCount(input)
    println("Name BRUDER")
    GameInitRe.getPlayerName(m, input)
    test = ""
    notifyObservers
  }

  override def undo(): Unit = {
    undoManager.undoStep()
    notifyObservers
  }

  override def redo(): Unit = {
    undoManager.redoStep()
    notifyObservers
  }

}
