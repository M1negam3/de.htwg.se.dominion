package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.controller.ControllerInterface
import de.htwg.se.dominion.controller.maincontroller.GameStatus.GameStatus
import de.htwg.se.dominion.model.gameComponent.{GameInit, GameInitRe}
import de.htwg.se.dominion.model.playerComponent.Player
import de.htwg.se.dominion.model.stringComponent.Output
import de.htwg.se.dominion.util.UndoManager

import scala.collection.mutable.ListBuffer

class ControllerRe (var roundManager: RoundManagerRe) extends ControllerInterface {

  private val undoManager = new UndoManager
  var gameStatus: GameStatus = GameStatus.PREP
  var controllerState: ControllerState = PlayerCountState(this)

  def eval(input: String): Unit = {
    //undoManager.doStep()
    controllerState.evaluate(input)
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

  def nextState(): Unit = controllerState = controllerState.nextState

  def getCurrentStateAsString: String = controllerState.getCurrentStateAsString

  object Controller {
    def toInt(s: String): Option[Int] = {
      try {
        Some(s.toInt)
      } catch {
        case _: Exception => None
      }
    }
  }

  trait ControllerState {

    def evaluate(input: String): Unit

    def getCurrentStateAsString: String

    def nextState: ControllerState
  }

  case class PlayerCountState(controller: ControllerRe) extends ControllerState {

    override def evaluate(input: String): Unit = {
      val number = Controller.toInt(input)
      if (number.isEmpty) {
        return
      }
      controller.nextState()

      controller.roundManager = controller.roundManager.copy(numberOfPlayer = number.get)
    }

    override def getCurrentStateAsString: String = Output.printPlayerQuestion()

    override def nextState: ControllerState = NameSetupState(controller)
  }

  case class NameSetupState(controller: ControllerRe) extends ControllerState {

    override def evaluate(input: String): Unit = {
      if (input.isEmpty) {
        return
      }
      controller.roundManager = controller.roundManager.getNames(controller.roundManager, input)
      controller.roundManager = controller.roundManager.copy(playerturn = controller.roundManager.nextPlayer())
      if (controller.roundManager.names.length == controller.roundManager.numberOfPlayer) {
        controller.roundManager = controller.roundManager.copy(players = controller.roundManager.createPlayer(controller.roundManager))
        controller.nextState()
      }
    }

    override def getCurrentStateAsString: String = controller.roundManager.getNameSetupStrings()

    override def nextState: ControllerState = playingState(controller)
  }

  case class playingState(controller: ControllerRe) extends ControllerState {

    override def evaluate(input: String): Unit = {
      var currentString = 0
      if (input.isEmpty) {
        return
      }
      // Action phase
      if (controller.roundManager.players(controller.roundManager.playerturn).actions != 0) {

      }

      if (false) {
        controller.nextState()
      }
    }

    override def getCurrentStateAsString: String = ""

    override def nextState: ControllerState = ???
  }

  case class EndState(controller: ControllerRe) extends ControllerState {
    override def evaluate(input: String): Unit = {}

    override def getCurrentStateAsString: String = Output.printScore(controller.roundManager.score)

    override def nextState: ControllerState = this
  }

}
