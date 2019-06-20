package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.util.Command

import scala.collection.mutable.ListBuffer

class turnCommand(idx: Int, controller: Controller) extends Command {

  var memory: ListBuffer[RoundManager] = ListBuffer()

  override def doStep(): Unit = {
    controller.roundmanager = controller.roundmanager.turn(idx, controller.roundmanager)
    memory += controller.roundmanager
  }

  override def undoStep(): Unit = {
    controller.roundmanager = controller.roundmanager.turn(0, controller.startRoundmanager)
    memory += controller.roundmanager
  }

  override def redoStep(): Unit = {
    println("LUL " + memory)
    memory -= memory(memory.length)
    println(memory)
    controller.roundmanager = memory(memory.length)
    controller.roundmanager = controller.roundmanager.turn(controller.roundmanager.idx ,controller.roundmanager)
  }
}
