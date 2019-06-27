package de.htwg.se.dominion.aview.gui

import java.awt.Color

import de.htwg.se.dominion.controller.maincontroller.Controller
import de.htwg.se.dominion.model.deckComponent.cardComponent.Cards
import javax.swing.{BorderFactory, ImageIcon, JFrame, JOptionPane}

import scala.swing.BorderPanel.Position._
import scala.collection.immutable
import scala.swing._
import Swing._
import scala.collection.mutable.ListBuffer
import scala.swing.event.{ButtonClicked, MouseClicked}
import scala.util.control.Breaks.{breakable, break}

class PlayingPanel(controller: Controller) extends BoxPanel(Orientation.Vertical) {

  val myFont = new Font("Charlemagne Std Bold", java.awt.Font.BOLD, 15)
  preferredSize = new Dimension(1800, 1200)

  val infoPanel = new BoxPanel(Orientation.Vertical) {
    contents += new Label("Player: " + (controller.getCurrentPlayerturn + 1) + " (" + controller.getPlayerName + ")"
      + "                                                                             " + controller.getCurrentPhaseAsString(controller.getCurrentPhase) )
    contents += new Label("Actions: " + controller.getCurrentActions)
    contents += new Label("Buys: " + controller.getCurrentBuys)
    contents += new Label("Money: " + controller.getCurrentMoney)
    font = new Font("Charlemagne Std Bold", java.awt.Font.BOLD, 20)
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

  val handPanel = new BoxPanel(Orientation.Horizontal) {
      val Hand: List[Cards] = controller.getCurrentHand
      val labelList: immutable.IndexedSeq[Label] = for (i <- Hand.indices) yield new Label {
        private val temp = new ImageIcon("src/main/resources/cards/" + Hand(i).CardName + ".png").getImage
        private val resize = temp.getScaledInstance(177, 276, java.awt.Image.SCALE_SMOOTH)
        icon = new ImageIcon(resize)
        listenTo(mouse.clicks)
        if (controller.getCurrentStringValue == 4) {
          reactions += {
            case _: MouseClicked => controller.eval((i).toString)
          }
        }
      }
      labelList.foreach(x => contents += x)
  }



  val playingDeckPanel = new FlowPanel() {
    if (controller.getCurrentPhase) {
      val playingDecks: List[List[Cards]] = controller.getCurrentPlayingDecks
      val labelList: immutable.IndexedSeq[Label] = for (i <- playingDecks.indices) yield new Label {
        private val temp = new ImageIcon("src/main/resources/cards/" + playingDecks(i).head.CardName + ".png").getImage
        private val resize = temp.getScaledInstance(177, 276, java.awt.Image.SCALE_SMOOTH)
        icon = new ImageIcon(resize)
        text = "Count: " + playingDecks(i).length
        font = myFont
        listenTo(mouse.clicks)
      }
      for (i <- labelList.indices) {
        contents += labelList(i)
      }
    } else {
      val playingDecks: List[List[Cards]] = controller.getCurrentPlayingDecks
      val labelList: immutable.IndexedSeq[Label] = for (i <- playingDecks.indices) yield new Label {
        private val temp = new ImageIcon("src/main/resources/cards/" + playingDecks(i).head.CardName + ".png").getImage
        private val resize = temp.getScaledInstance(177, 276, java.awt.Image.SCALE_SMOOTH)
        icon = new ImageIcon(resize)
        text = "Count: " + playingDecks(i).length
        font = myFont
        listenTo(mouse.clicks)
        if (controller.getCurrentStringValue == 30 || controller.getCurrentStringValue == 32) {
          reactions += {
            case _: MouseClicked => {
              controller.eval((i).toString)
            }
          }
        }
      }
        for (i <- labelList.indices) {
          contents += labelList(i)
        }
    }
  }

  val yesButton = new Button("Yes")
  val noButton = new Button("No")
  val okButton = new Button("Okay")

  val optionPanelQuestion = new BoxPanel(Orientation.Vertical) {
    controller.getCurrentStringValue match {
      case 0 => contents += new Label("Press here to continue!")
      case 1 => contents += new Label("You dont have any Action cards to play")
      case 3 => contents += new Label("Do you want to play a Card?")
      case 4 => {
        contents += new Label("Which Card do you want to Play?")
        contents += new Label("Click on it")
      }
      case 25 => contents += new Label("Do you want to buy a Card?")
      case 30 => {
        contents += new Label("Which Card do you want to Buy?")
        contents += new Label("Click on it")
      }
      case 31 => {
        contents += new Label("The Card was bought and added to your stacker!")
        contents += new Label("Press here to continue!")
      }
      case 32 => {
        contents += new Label("You cant buy that, please click a valid Card")
      }
    }
  }

  val optionPanelButtons = new BoxPanel(Orientation.Horizontal) {
    if (controller.getCurrentStringValue == 0 || controller.getCurrentStringValue == 1 || controller.getCurrentStringValue == 31) {
      contents += okButton
    }
    if (controller.getCurrentStringValue == 3 || controller.getCurrentStringValue == 25) {
      contents += yesButton
      contents += noButton
    }
  }

  val optionPanel = new BoxPanel(Orientation.Vertical) {
    contents += optionPanelQuestion
    contents += optionPanelButtons
    border = BorderFactory.createLineBorder(Color.BLACK, 2)
  }

  val southPanel = new BoxPanel(Orientation.Horizontal) {
    contents += deckPanel
    contents += handPanel
    contents += optionPanel
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
  listenTo(yesButton)
  listenTo(noButton)
  listenTo(okButton)

  reactions += {
    case ButtonClicked(`yesButton`) => controller.eval("Y")
    case ButtonClicked(`noButton`) => controller.eval("N")
    case ButtonClicked(`okButton`) => controller.eval("o")
    case ButtonClicked(`nextButton`) => controller.eval("a")
    case ButtonClicked(`prevButton`) => controller.undo()
  }

}