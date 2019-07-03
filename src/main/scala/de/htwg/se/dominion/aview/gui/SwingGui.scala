package de.htwg.se.dominion.aview.gui

import de.htwg.se.dominion.controller.maincontroller.Controller
import de.htwg.se.dominion.util.Observer
import scala.swing._

class SwingGui (controller: Controller) extends Frame with Observer {
  controller.add(this)

  title = "Dominion"

  contents = new WelcomePanel(controller)

  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("Save") {controller.save()})
      contents += new MenuItem(Action("Load") {controller.load()})
      contents += new MenuItem(Action("Quit") {System.exit(0)})
    }
  }

  //peer.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE)
  visible = true
  centerOnScreen()
  resizable = true
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
      case "PlayerCountState" => new WelcomePanel(controller)
      case "NameSetupState" => new NameInitPanel(controller)
      case "PlayingState" => new PlayingPanel(controller)
      case "EndState" => new GameEndPanel(controller)
    }
  }
}
