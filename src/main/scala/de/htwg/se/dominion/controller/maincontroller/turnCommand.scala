package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.util.Command

class turnCommand(r: RoundManager, controller: Controller) extends Command {

  override def doStep(): Unit = controller.RoundManager = RoundManager()

  override def undoStep(): Unit = controller.RoundManager = RoundManager()

  override def redoStep(): Unit = controller.RoundManager = RoundManager()
}
