package de.htwg.de.dominion.controller.maincontroller

import de.htwg.se.dominion.controller.maincontroller.{Controller, RoundManager, SetCommand, playingState}
import org.scalatest.{Matchers, WordSpec}

class SetCommandSpec extends WordSpec with Matchers {
  "An EvalStep" when {

    val roundManager = RoundManager()
    val controller = new Controller(roundManager)
    val setCommand = new SetCommand(controller)
    "saves the current controller's state and round manager" in {
      val state = (controller.roundManager, controller.controllerState)
      setCommand.doStep()
      setCommand.memory should be(state)
    }

    "restore the previous state and save the current one" in {
      val oldState = (controller.roundManager.copy(), controller.controllerState)
      controller.controllerState = playingState(controller)
      val newState = (controller.roundManager.copy(), controller.controllerState)
      setCommand.undoStep()
      setCommand.memory should be(newState)
      controller.roundManager should be(oldState._1)
      controller.controllerState should be(oldState._2)
    }

    "revert the undo command" in {
      val afterUndoState = (controller.roundManager.copy(), controller.controllerState)
      val beforeUndoState = setCommand.memory
      setCommand.redoStep()
      setCommand.memory should be(afterUndoState)
      controller.roundManager should be(beforeUndoState._1)
      controller.controllerState should be(beforeUndoState._2)
    }
  }
}