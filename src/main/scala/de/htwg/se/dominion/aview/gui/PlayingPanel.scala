package de.htwg.se.dominion.aview.gui

import de.htwg.se.dominion.controller.maincontroller.Controller
import de.htwg.se.dominion.model.deckComponent.cardComponent.Cards
import javax.swing.ImageIcon

import scala.collection.immutable
import scala.swing._
import Swing._
import scala.swing.event.ButtonClicked

class PlayingPanel(controller: Controller) extends BoxPanel(Orientation.Vertical) {

  val myFont = new Font("Charlemagne Std Bold", java.awt.Font.BOLD, 20)
  preferredSize = new Dimension(2000, 1000)

  contents += new BoxPanel(Orientation.Vertical) {
      contents += new Label("Player: " + (controller.getCurrentPlayerturn + 1) + " (" + controller.getPlayerName + ")")
      contents += new Label("Actions: " + controller.getCurrentActions)
      contents += new Label("Buys: " + controller.getCurrentBuys)
      contents += new Label("Money: " + controller.getCurrentMoney)
      font = myFont
  }

  contents += new BoxPanel(Orientation.Horizontal) {
    val Hand: List[Cards] = controller.getCurrentHand
    val labelList: immutable.IndexedSeq[Label] = for (i <-  Hand.indices) yield new Label {
      private val temp = new ImageIcon("src/main/resources/cards/" + Hand(i).CardName + ".png").getImage
      private val resize = temp.getScaledInstance(100, 133, java.awt.Image.SCALE_SMOOTH)
    }

    labelList.foreach(x => contents += x)
  }

  val nextButton = new Button("\u2192")
  val prevButton = new Button("\u2190")

  listenTo(nextButton)
  listenTo(prevButton)

  contents += new BoxPanel(Orientation.Horizontal) {
    contents += prevButton
    contents += HGlue
    contents += nextButton
  }

  reactions += {
    case ButtonClicked(`nextButton`) => controller.eval("a")
    case ButtonClicked(`prevButton`) => controller.undo()
  }
}
