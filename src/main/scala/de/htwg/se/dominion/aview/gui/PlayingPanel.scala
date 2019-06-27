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
import scala.swing.event.{ButtonClicked, Key, KeyPressed, MouseClicked}
import scala.util.control.Breaks.{break, breakable}

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
      text = "    Deck Count: " + controller.getCurrentDeck.length
      font = myFont
    }
  }

  var s: String = ""
  var l: ListBuffer[Int] = ListBuffer()

  val handPanel = new BoxPanel(Orientation.Horizontal) {
      val Hand: List[Cards] = controller.getCurrentHand
      val labelList: immutable.IndexedSeq[Label] = for (i <- Hand.indices) yield new Label {
        private val temp = new ImageIcon("src/main/resources/cards/" + Hand(i).CardName + ".png").getImage
        private val resize = temp.getScaledInstance(177, 276, java.awt.Image.SCALE_SMOOTH)
        icon = new ImageIcon(resize)
        listenTo(mouse.clicks)
        if (controller.getCurrentStringValue == 4 || controller.getCurrentStringValue == 9
          || controller.getCurrentStringValue == 14 || controller.getCurrentStringValue == 37 || controller.getCurrentStringValue == 16) {
          reactions += {
            case _: MouseClicked => controller.eval((i).toString)
          }
        }
        if (controller.getCurrentStringValue == 8 || controller.getCurrentStringValue == 10 || controller.getCurrentStringValue == 11) {
          reactions += {
            case _: MouseClicked => {
              l += i
            }
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
        if (controller.getCurrentStringValue == 18 || controller.getCurrentStringValue == 23 || controller.getCurrentStringValue == 33
          || controller.getCurrentStringValue == 21) {
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
  val doneButton = new Button("Done")

  val optionPanelQuestion = new BoxPanel(Orientation.Vertical) {
    controller.getCurrentStringValue match {
      case 0 => contents += new Label("Press here to continue!")
      case 1 => contents += new Label("You dont have any Actions/Action Cards to play")
      case 3 => contents += new Label("Do you want to play a Card?")
      case 4 => {
        contents += new Label("Which Card do you want to Play?")
        contents += new Label("Click on it")
      }
      case 7 => contents += new Label("Enter the amount of Cards to Discard")
      case 8 => {
        contents += new Label("Choose some Card(s) by clicking on them")
        contents += new Label("Press here when you are done selecting")
      }
      case 9 => contents += new Label("Please choose a action Card!")
      case 10 => contents += new Label("Please select the right amount of Cards")
      case 11 => contents += new Label("Dont click the same Card twice!")
      case 13 => contents += new Label("Enter a Number that is not larger than you have Cards!")
      case 14 => contents += new Label("Choose one Moneycard to upgrade")
      case 16 => contents += new Label("Which card to you want to trash?")
      case 18 => contents += new Label("Click on the card you want to add to your hand!")
      case 21 => contents += new Label("Please choose a card that costs up to 4!")
      case 23 => contents += new Label("You cant add that, please choose a valid card")
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
      case 33 => contents += new Label("Click on the card you want to add")
      case 37 => contents += new Label("Please choose a Money Card!")
      case 45 => contents += new Label("Enter a number")
      case 41 => contents += new Label("You need to discard at least 1 Card")
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
    if (controller.getCurrentStringValue == 7 || controller.getCurrentStringValue == 13 || controller.getCurrentStringValue == 41
      || controller.getCurrentStringValue == 45) {
      contents += new TextField {
        listenTo(keys)
        reactions += {
          case KeyPressed(_, Key.Enter, _, _) => controller.eval(text)
        }
      }
    }
    if (controller.getCurrentStringValue == 8 || controller.getCurrentStringValue == 10 || controller.getCurrentStringValue == 11) {
      contents += doneButton
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
  listenTo(doneButton)

  reactions += {
    case ButtonClicked(`yesButton`) => controller.eval("Y")
    case ButtonClicked(`noButton`) => controller.eval("N")
    case ButtonClicked(`okButton`) => controller.eval("o")
    case ButtonClicked(`nextButton`) => controller.eval("a")
    case ButtonClicked(`doneButton`) => {
      for (i <- 0 until l.length - 1) {
        s += l(i).toString + ","
      }
      s += l.last
      controller.eval(s)
      l = ListBuffer()
      s = ""
    }
    case ButtonClicked(`prevButton`) => controller.undo()
  }

}