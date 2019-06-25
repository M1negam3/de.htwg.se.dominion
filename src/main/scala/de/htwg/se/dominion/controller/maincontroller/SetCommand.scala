package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.util.Command

class SetCommand(controller: Controller) extends Command {

  var memory: (RoundManager, ControllerState) = (controller.roundManager.copy(), controller.controllerState)

  override def doStep(): Unit = memory = (controller.roundManager.copy(), controller.controllerState)

  override def undoStep(): Unit = {
    val newMemory = (controller.roundManager.copy(), controller.controllerState)
    controller.roundManager = memory._1
    controller.controllerState = memory._2
    memory = newMemory
  }

  override def redoStep(): Unit = {
    val newMemory = (controller.roundManager.copy(), controller.controllerState)
    controller.roundManager = memory._1
    controller.controllerState = memory._2
    memory = newMemory
  }
}