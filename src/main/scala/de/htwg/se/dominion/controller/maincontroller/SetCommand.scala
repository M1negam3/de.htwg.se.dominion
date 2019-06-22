package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.util.Command

class SetCommand(idx: Int, controller: Controller) extends Command {

  override def doStep(): Unit = {
    controller.memory += controller.roundmanager
    controller.roundmanager = controller.roundmanager.turn(idx, controller.roundmanager)
  }

  override def undoStep(): Unit = {
    controller.memory += controller.startRoundmanager
    controller.roundmanager = controller.roundmanager.turn(0, controller.startRoundmanager)

  }

  override def redoStep(): Unit = {
    controller.memory -= controller.memory.last
    controller.roundmanager = controller.memory.last
    controller.memory += controller.roundmanager
    controller.roundmanager = controller.roundmanager.turn(controller.roundmanager.idx ,controller.roundmanager)
  }
}
