package de.htwg.se.dominion.aview.gui

import java.awt.Color

import de.htwg.se.dominion.controller.maincontroller.Controller
import de.htwg.se.dominion.model.deckComponent.cardComponent.Cards
import javax.swing.{BorderFactory, ImageIcon}

import scala.swing.BorderPanel.Position._
import scala.collection.immutable
import scala.swing._
import Swing._
import scala.collection.mutable.ListBuffer
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

  val deckPanel = new BoxPanel(Orientation.Vertical) {
    contents += new Label {
      private val temp = new ImageIcon("src/main/resources/cards/Card_back.png").getImage
      private val resize = temp.getScaledInstance(177, 276, java.awt.Image.SCALE_SMOOTH)
      icon = new ImageIcon(resize)
    }
    contents += new Label {
      text = "        Count: " + controller.getCurrentDeck.length
      font = myFont
    }
  }

  val playingDecks: List[List[Cards]] = controller.getCurrentPlayingDecks
  var listBuffer: ListBuffer[List[Cards]] = ListBuffer()
  for (i <- playingDecks.indices) {
    if (playingDecks(i).head.CostValue < 20) {
      listBuffer += playingDecks(i)
    }
  }

  val handPanel = new BoxPanel(Orientation.Horizontal) {
    if (controller.getCurrentPhase) {
      val Hand: List[Cards] = controller.getCurrentHand
      val labelList: immutable.IndexedSeq[Label] = for (i <- Hand.indices) yield new Label {
        private val temp = new ImageIcon("src/main/resources/cards/" + Hand(i).CardName + ".png").getImage
        private val resize = temp.getScaledInstance(177, 276, java.awt.Image.SCALE_SMOOTH)
        icon = new ImageIcon(resize)
      }
      labelList.foreach(x => contents += x)
    }
  }
  /*val buyPanel = new BoxPanel(Orientation.Horizontal) {
    if (controller.getCurrentPhase) {
      var buyCards: List[Cards] = List()
      var buys = 3
      var listBuffer1 = new ListBuffer[Cards]
      val Hand: List[Cards] = controller.getCurrentHand
      /*for(i <- Hand.indices){
        buys += controller.getCurrentHand(i).MoneyValue
      }*/
      for (i <- playingDecks.indices) {
        if (playingDecks(i).head.CostValue <= buys ) {
          listBuffer1 += playingDecks(i).head
        }
      }
      buyCards = listBuffer1.toList
      val labelList1: immutable.IndexedSeq[Label] = for (i <- buyCards.indices) yield new Label {
        private val temp = new ImageIcon("src/main/resources/cards/" + actualPlayingDeck(i).head.CardName + ".png").getImage
        private val resize = temp.getScaledInstance(177, 276, java.awt.Image.SCALE_SMOOTH)
        icon = new ImageIcon(resize)
      }
      labelList1.foreach(x => contents += x)
    }
  }*/


  val actualPlayingDeck: List[List[Cards]] = listBuffer.toList
  listBuffer = ListBuffer()

  val playingDeckPanel = new FlowPanel() {
    val labelList: immutable.IndexedSeq[Label] = for (i <- actualPlayingDeck.indices) yield new Label {
      private val temp = new ImageIcon("src/main/resources/cards/" + actualPlayingDeck(i).head.CardName + ".png").getImage
      private val resize = temp.getScaledInstance(177, 276, java.awt.Image.SCALE_SMOOTH)
      icon = new ImageIcon(resize)
      text = "Count: " + actualPlayingDeck(i).length
      font = myFont
    }
    for (i <- labelList.indices) {
      contents += labelList(i)
    }
  }

  val southPanel = new BoxPanel(Orientation.Horizontal) {
    contents += deckPanel
    contents += handPanel
    //contents += buyPanel
  }

  val nextButton = new Button("\u2192")
  val prevButton = new Button("\u2190")

  contents += new BorderPanel {
    layout(infoPanel) = North
    layout(playingDeckPanel) = Center
    layout(southPanel) = South
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