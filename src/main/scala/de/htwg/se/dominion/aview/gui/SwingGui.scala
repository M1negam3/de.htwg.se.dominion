package de.htwg.se.dominion.aview.gui

import de.htwg.se.dominion.controller.maincontroller.Controller
import de.htwg.se.dominion.util.Observer
import scala.swing._
import Swing._

class SwingGui (controller: Controller) extends Frame with Observer {
  controller.add(this)

  title = "Dominion"

  contents = new WelcomePanel(controller)

  //peer.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE)
  visible = true
  centerOnScreen()
  resizable = false
  pack()

  override def update(): Boolean = {
    contents = SwingGui.getPanel(controller)

    repaint()
    true
  }
}

object SwingGui {
  def getPanel(controller: Controller): Panel = {
    controller.controllerStateAsString match {
      case _ => new WelcomePanel(controller)
    }
  }
}
