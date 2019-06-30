package de.htwg.se.dominion.aview.gui

import de.htwg.se.dominion.controller.maincontroller.Controller

import scala.swing._
import Swing._
import scala.collection.immutable
import scala.swing.event.ButtonClicked


class GameEndPanel(controller: Controller) extends BoxPanel(Orientation.Vertical) {

  val myFont = new Font("Charlemagne Std Bold", java.awt.Font.BOLD, 15)
  preferredSize = new Dimension(1800, 1200)

  val quitButton = new Button("Quit")
  listenTo(quitButton)

  val scorePanel = new BoxPanel(Orientation.Vertical) {
    val score = controller.getCurrentScore
    val labelList: immutable.IndexedSeq[Label] = for (i <- score.indices) yield new Label {
      text = score(i)._2 + ", Points: " + score(i)._1.toString
      font = myFont
    }
    labelList.foreach(x => contents += x)
  }

  val scorePanelWQuit = new BoxPanel(Orientation.Vertical) {
    contents += scorePanel
    contents += quitButton
  }

  contents += new BorderPanel {
    layout(scorePanelWQuit) = BorderPanel.Position.Center
  }

  reactions += {
    case ButtonClicked(`quitButton`) => System.exit(0)
  }
}
