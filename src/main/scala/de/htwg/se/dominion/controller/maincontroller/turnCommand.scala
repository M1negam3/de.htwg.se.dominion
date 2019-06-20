package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.util.Command

class turnCommand(idx: Int, controller: Controller) extends Command {

  override def doStep(): Unit = controller.roundmanager = controller.roundmanager.turn(idx ,controller.roundmanager)

  override def undoStep(): Unit = controller.roundmanager = controller.roundmanager.turn(0, controller.startRoundmanager)

  override def redoStep(): Unit = controller.roundmanager = controller.roundmanager.turn(idx ,controller.roundmanager)
}
