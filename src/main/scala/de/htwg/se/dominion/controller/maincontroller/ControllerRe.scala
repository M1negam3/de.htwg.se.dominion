package de.htwg.se.dominion.controller.maincontroller

import java.io.BufferedReader

import de.htwg.se.dominion.controller.ControllerInterface
import de.htwg.se.dominion.controller.maincontroller.GameStatus.GameStatus
import de.htwg.se.dominion.model.deckComponent.Cards
import de.htwg.se.dominion.model.gameComponent.{GameInitRe, GameTurnRe}
import de.htwg.se.dominion.model.playerComponent.Player
import de.htwg.se.dominion.model.stringComponent.Output
import de.htwg.se.dominion.util.UndoManager

import scala.util.control.Breaks.{break, breakable}
import scala.collection.mutable.ListBuffer

class ControllerRe (var roundManager: RoundManagerRe) extends ControllerInterface {

  private val undoManager = new UndoManager
  var gameStatus: GameStatus = GameStatus.IDLE
  var controllerState: ControllerState = PlayerCountState(this)

  def eval(input: String): Unit = {
    //undoManager.doStep()
    controllerState.evaluate(input)
    notifyObservers
  }

  override def undo(): Unit = {
    undoManager.undoStep()
    notifyObservers
  }

  override def redo(): Unit = {
    undoManager.redoStep()
    notifyObservers
  }

  def nextState(): Unit = controllerState = controllerState.nextState

  def getCurrentStateAsString: String = controllerState.getCurrentStateAsString

  object Controller {
    def toInt(s: String): Option[Int] = {
      try {
        Some(s.toInt)
      } catch {
        case _: Exception => None
      }
    }
  }

  trait ControllerState {

    def evaluate(input: String): Unit

    def getCurrentStateAsString: String

    def nextState: ControllerState
  }

  case class PlayerCountState(controller: ControllerRe) extends ControllerState {

    override def evaluate(input: String): Unit = {
      val number = Controller.toInt(input)
      if (number.isEmpty || number.get < 2 || number.get > 5) {
        return
      }
      controller.nextState()

      controller.roundManager = controller.roundManager.copy(numberOfPlayer = number.get)
    }

    override def getCurrentStateAsString: String = Output.printHeader() + Output.printPlayerQuestion()

    override def nextState: ControllerState = NameSetupState(controller)
  }

  case class NameSetupState(controller: ControllerRe) extends ControllerState {

    override def evaluate(input: String): Unit = {
      if (input.isEmpty) {
        return
      }
      controller.roundManager = controller.roundManager.getNames(controller.roundManager, input)
      controller.roundManager = controller.roundManager.copy(playerturn = controller.roundManager.nextPlayer())
      if (controller.roundManager.names.length == controller.roundManager.numberOfPlayer) {
        controller.roundManager = controller.roundManager.copy(players = controller.roundManager.createPlayer(controller.roundManager))
        controller.gameStatus = GameStatus.PREP
        controller.nextState()
      }
    }

    override def getCurrentStateAsString: String = controller.roundManager.getNameSetupStrings()

    override def nextState: ControllerState = playingState(controller)
  }

  case class playingState(controller: ControllerRe) extends ControllerState {
    var runthrough = 0
    var continue = false
    var buycount = 0
    var buy = true
    var availableCards: ListBuffer[Int] = ListBuffer()
    var action = true
    var skip = false
    var empty = 0
    var end = false

    override def evaluate(input: String): Unit = {
      if (input.isEmpty) {
        return
      }
      // Action phase
      if (action) {
        if (controller.roundManager.players(controller.roundManager.playerturn).actions > 0) {
          if (runthrough == 6) {
            runthrough == 1
          }
          // Second Card Effect input
          if (runthrough == 5) {
            // Card Cellar 2
            if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 8) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect2(controller.roundManager, input))
              runthrough = 6
            }
            if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 9 ||
              controller.roundManager.players(controller.roundManager.playerturn).stringValue == 10 ||
              controller.roundManager.players(controller.roundManager.playerturn).stringValue == 11) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect2(controller.roundManager, input))
              runthrough = 5
            }
            // Card Remodel 2
            if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 18) {
              if (Controller.toInt(input).isEmpty) {
                runthrough = 5
                return
              }
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect2(controller.roundManager, input))
              runthrough = 6
            }
            if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 23) {
              if (Controller.toInt(input).isEmpty) {
                return
              }
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect2(controller.roundManager, input))
              runthrough = 5
            }
          }
          // First card effect input
          if (runthrough == 4) {
            if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 9) {
              runthrough = 3
            }
            if (Controller.toInt(input).isEmpty) {
              return
            }
            // Card Cellar
            if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 7) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get))
              runthrough = 5
            }
            if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 13 ||
              controller.roundManager.players(controller.roundManager.playerturn).stringValue == 41) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get))
              runthrough = 4
            }
            // Card Mine
            if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 14) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get))
              runthrough = 6
            }
            if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 15 ||
              controller.roundManager.players(controller.roundManager.playerturn).stringValue == 37) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get))
              runthrough = 4
            }
            // Card Remodel
            if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 16) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get))
              runthrough = 5
            }
            if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 19) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get))
              runthrough = 4
            }
            // Card Workshop
            if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 33) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, input.toInt))
              runthrough = 6
            }
            if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 21) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get))
              runthrough = 4
            }
            // Card Merchant
            if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 38) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get))
              runthrough = 6
            }
          }
          // You choose to play a card tell us which one and play it
          if (runthrough == 3) {
            if (Controller.toInt(input).isEmpty) {
              return
            }
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionPhase2(controller.roundManager, Controller.toInt(input).get))
            runthrough = 4
          }
          // either you got a action card or not
          if (runthrough == 2) {
            if (input.equals("Y")) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 4))
              runthrough = 3
            } else if (input.equals("N")) {
              controller.gameStatus = GameStatus.BUY
              action = false
              runthrough = 10
            } else {
              controller.gameStatus = GameStatus.BUY
              action = false
              runthrough = 10
            }
          }
          // draw these Cards
          if (runthrough == 0) {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.getHand(controller.roundManager))
            controller.gameStatus = GameStatus.ACTION
            runthrough = 1
          }
          // first time action
          if (runthrough == 1) {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionPhase(controller.roundManager))
            runthrough = 2
          }
        } else {
          println("MÖP")
        }
      }

      // Buy Phase
      if (!action) {
        if(controller.roundManager.players(controller.roundManager.playerturn).buys >= 1) {
          if (buycount == 0) {
            controller.roundManager = controller.roundManager.copy(controller.roundManager.editStringValue(controller.roundManager, 25))
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.updateMoney(controller.roundManager,GameTurnRe.getMoney(controller.roundManager.players(controller.roundManager.playerturn))))
            for (g <- 0 until GameTurnRe.playingDecks.length) {
              if (controller.roundManager.players(controller.roundManager.playerturn).money >= GameTurnRe.playingDecks(g).head.CostValue) {
                availableCards += g
              }
            }
            buycount += 1
            return
          } else if (buycount == 1 && (controller.roundManager.players(controller.roundManager.playerturn).stringValue != 30)) {
            if (input.equals("Y")) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 30))
              return
            } else if (input.equals("N")) {
              skip = true
              action = true
              runthrough = 0
            } else {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 24))
            }

          } else if (buycount == 1 && (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 30)) {
            if (availableCards.contains(input.toInt)) {
              controller.roundManager = controller.roundManager.copy(players = (GameTurnRe.buyPhase(controller.roundManager.players,controller.roundManager.playerturn,input.toInt)))
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 31))
              return
            } else {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 32))
              return
            }

          }
        } else {
          controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 34))
          controller.roundManager = controller.roundManager.copy(players = GameTurnRe.clearHand(controller.roundManager.players,controller.roundManager.playerturn))
          buycount = 0
          skip = true
          action = true
          runthrough = 0
        }

      }

      for (i <- 0 until GameTurnRe.playingDecks.length) {
        if (GameTurnRe.playingDecks(i).isEmpty) {
          if (i == 3) {
            end = true
          }
          GameTurnRe.playingDecks = GameTurnRe.updatePlayingDecks(GameTurnRe.playingDecks, i)
          empty += 1
          if (empty == 3) {
            end = true
          }
        }
      }

      // next Player/State
      if (skip) {
        controller.roundManager = controller.roundManager.copy(playerturn = controller.roundManager.nextPlayer())
        controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 0))
        controller.gameStatus = GameStatus.PREP
        skip = false
      }

      if (end) {
        controller.nextState()
      }
    }

    override def getCurrentStateAsString: String = Output.getPlayingStateString(controller.roundManager.players, controller.roundManager.playerturn, controller.roundManager.players(controller.roundManager.playerturn).stringValue)

    override def nextState: ControllerState = EndState(controller)
  }

  case class EndState(controller: ControllerRe) extends ControllerState {
    override def evaluate(input: String): Unit = {}

    override def getCurrentStateAsString: String = Output.printScore(controller.roundManager.score)

    override def nextState: ControllerState = this
  }

}
