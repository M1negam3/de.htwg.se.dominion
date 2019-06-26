package de.htwg.se.dominion.aview.gui

import java.awt.Color
import de.htwg.se.dominion.controller.maincontroller.Controller
import de.htwg.se.dominion.model.deckComponent.cardComponent.Cards
import javax.swing.{BorderFactory, ImageIcon}
import scala.swing.BorderPanel.Position._
import scala.collection.immutable
import scala.swing._
import Swing._
import scala.swing.event.ButtonClicked

class PlayingPanel(controller: Controller) extends BoxPanel(Orientation.Vertical) {

  val myFont = new Font("Charlemagne Std Bold", java.awt.Font.BOLD, 15)
  preferredSize = new Dimension(1800, 1200)

  val infoPanel = new BoxPanel(Orientation.Vertical) {
    contents += new Label("Player: " + (controller.getCurrentPlayerturn + 1) + " (" + controller.getPlayerName + ")")
    contents += new Label("Actions: " + controller.getCurrentActions)
    contents += new Label("Buys: " + controller.getCurrentBuys)
    contents += new Label("Money: " + controller.getCurrentMoney)
    font = myFont
    border = BorderFactory.createLineBorder(Color.BLACK, 2)
  }

  val deckPanel = new BoxPanel(Orientation.Horizontal) {
    contents += new Label {
      private val temp = new ImageIcon("src/main/resources/cards/Card_back.png").getImage
      private val resize = temp.getScaledInstance(118, 184, java.awt.Image.SCALE_SMOOTH)
      icon = new ImageIcon(resize)
    }
    if (controller.getCurrentPhase) {
        val Hand: List[Cards] = controller.getCurrentHand
        val labelList: immutable.IndexedSeq[Label] = for (i <- Hand.indices) yield new Label {
          private val temp = new ImageIcon("src/main/resources/cards/" + Hand(i).CardName + ".png").getImage
          private val resize = temp.getScaledInstance(118, 184, java.awt.Image.SCALE_SMOOTH)
          icon = new ImageIcon(resize)
        }
        labelList.foreach(x => contents += x
        )
      }
    }
    /*contents += new Label {
      text = "      Size: " + controller.getCurrentDeck.length
      font = myFont
    }*/

  val playingDeckPanel = new FlowPanel() {
    val playingDecks: List[List[Cards]] = controller.getCurrentPlayingDecks
    val labelList: immutable.IndexedSeq[Label] = for (i <- playingDecks.indices) yield new Label {
      private val temp = new ImageIcon("src/main/resources/cards/" + playingDecks(i).head.CardName + ".png").getImage
      private val resize = temp.getScaledInstance(118, 184, java.awt.Image.SCALE_SMOOTH)
      icon = new ImageIcon(resize)
      text = "      Size: " + playingDecks(i).length
      font = myFont
    }
    labelList.foreach(x => contents += x)
  }

  val nextButton = new Button("\u2192")
  val prevButton = new Button("\u2190")

  contents += new BorderPanel {
    layout(infoPanel) = North
    layout(playingDeckPanel) = Center
    layout(deckPanel) = South
    layout(nextButton) = East
    layout(prevButton) = West
  }

  listenTo(nextButton)
  listenTo(prevButton)

  reactions += {
    case ButtonClicked(`nextButton`) => controller.eval("a")
    case ButtonClicked(`prevButton`) => controller.undo()
  }

}
