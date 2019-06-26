package de.htwg.se.dominion.aview.gui

import de.htwg.se.dominion.controller.maincontroller.Controller
import javax.swing.{BorderFactory, ImageIcon}

import scala.swing._
import scala.swing.event.ButtonClicked

class WelcomePanel(controller: Controller) extends BoxPanel(Orientation.Vertical) {

  val myFont = new Font("Charlemagne Std", java.awt.Font.BOLD, 20)

  val twoPlayerButton: Button = new Button("2 Players") {
    font = myFont
  }

  val threePlayerButton: Button = new Button("3 Players") {
    font = myFont
  }

  val fourPlayerButton: Button = new Button("4 Players") {
    font = myFont
  }

  val fivePlayerButton: Button = new Button("5 Players") {
    font = myFont
  }

  contents += new FlowPanel() {
    contents += new Label {
      private val temp = new ImageIcon("src/main/resources/dominion.png").getImage
      private val resize = temp.getScaledInstance(2000, 800, java.awt.Image.SCALE_SMOOTH)
      icon = new ImageIcon(resize)
    }
  }

  contents += new FlowPanel() {
    contents += new Label("How many Players are you?") {
      font = new Font("Charlemagne Std Bold", java.awt.Font.BOLD, 40)
    }
  }

  contents += new FlowPanel() {
    contents += twoPlayerButton
    contents += threePlayerButton
    contents += fourPlayerButton
    contents += fivePlayerButton
  }

  listenTo(twoPlayerButton)
  listenTo(threePlayerButton)
  listenTo(fourPlayerButton)
  listenTo(fivePlayerButton)

  reactions += {
    case ButtonClicked(`twoPlayerButton`) => controller.eval("2")
    case ButtonClicked(`threePlayerButton`) => controller.eval("3")
    case ButtonClicked(`fourPlayerButton`) => controller.eval("4")
    case ButtonClicked(`fivePlayerButton`) => controller.eval("5")
  }
}
