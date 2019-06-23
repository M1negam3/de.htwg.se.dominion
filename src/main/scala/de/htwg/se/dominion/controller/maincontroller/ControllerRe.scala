package de.htwg.se.dominion.controller.maincontroller

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
  var gameStatus: GameStatus = GameStatus.PREP
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

    override def getCurrentStateAsString: String = Output.printPlayerQuestion()

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
    var z = 0

    override def evaluate(input: String): Unit = {
      var skip = false
      if (input.isEmpty) {
        return
      }
      // Action phase
      if (action) {
        runthrough += 1
        // Card Effect phase when you played a Card
        if (runthrough > 4) {
          // Card cellar 2
          if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 8 ||
            controller.roundManager.players(controller.roundManager.playerturn).stringValue == 9 ||
            controller.roundManager.players(controller.roundManager.playerturn).stringValue == 10 ||
            controller.roundManager.players(controller.roundManager.playerturn).stringValue == 11) {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect2(controller.roundManager, input))
          }
          // Card Remodel 2
          if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 18 ||
            controller.roundManager.players(controller.roundManager.playerturn).stringValue == 23) {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect2(controller.roundManager, input))
          }
        }
        if (runthrough > 3) {
          // Card Cellar
          if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 7 ||
            controller.roundManager.players(controller.roundManager.playerturn).stringValue == 13) {
            if (Controller.toInt(input).isEmpty) {
              return
            }
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get))
          }
          // Card Mine
          if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 14 ||
            controller.roundManager.players(controller.roundManager.playerturn).stringValue == 15 ||
            controller.roundManager.players(controller.roundManager.playerturn).stringValue == 37) {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get))
          }
          // Card Remodel
          if ( controller.roundManager.players(controller.roundManager.playerturn).stringValue == 16 ||
            controller.roundManager.players(controller.roundManager.playerturn).stringValue == 19) {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get))
          }
          // Card Workshop
          if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 33 ||
            controller.roundManager.players(controller.roundManager.playerturn).stringValue == 21) {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get))
          }
          // Card Merchant
          if (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 38) {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get))
          }
        }

        // Choose a Action card to play
        if (runthrough > 2 && (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 4 ||
          controller.roundManager.players(controller.roundManager.playerturn).stringValue == 9)) {
          if (Controller.toInt(input).isEmpty) {
            return
          }
          controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionPhase2(controller.roundManager, Controller.toInt(input).get))
        }

        // Do you want to play a Card
        if (runthrough > 1 && (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 3 ||
          controller.roundManager.players(controller.roundManager.playerturn).stringValue == 24)) {
          if (input.equals("Y")) {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 4))
            return
          }
          if (input.equals("N")) {
            action = false
            // TODO STRING ACTION PHASE VORBEI

          } else {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 24))
          }
        }

        // No Action Cards on Hand
        if (runthrough > 1 && controller.roundManager.players(controller.roundManager.playerturn).stringValue == 1) {
          action = false
          // TODO STRING ACTION PHASE VORBEI
        }

        // First action phase check for action Cards
        if (runthrough == 1 /*|| skip*/) {
          controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionPhase(controller.roundManager))
        }
      }

      // Buy Phase
      if (!action) {
        if(controller.roundManager.players(controller.roundManager.playerturn).buys >= 1) {
          if (buycount == 0) {
            controller.roundManager = controller.roundManager.copy(controller.roundManager.editStringValue(controller.roundManager, 25))
            buycount += 1
            return
          } else if (buycount == 1) {
            controller.roundManager = controller.roundManager.copy(controller.roundManager.editStringValue(controller.roundManager, 26))
            buycount += 1
            return
          } else if (buycount == 2) {
            controller.roundManager = controller.roundManager.copy(controller.roundManager.editStringValue(controller.roundManager, 27))
            buycount += 1
            return
          } else if (buycount == 3) {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.updateMoney(controller.roundManager,GameTurnRe.getMoney(controller.roundManager.players(controller.roundManager.playerturn))))
            for (g <- 0 until GameTurnRe.playingDecks.length) {
              if (controller.roundManager.players(controller.roundManager.playerturn).money >= GameTurnRe.playingDecks(g).head.CostValue) {
                availableCards += g
                z += 1
              }
            }
            controller.roundManager = controller.roundManager.copy(controller.roundManager.editStringValue(controller.roundManager, 28))
            buycount += 1
            return
          } else if (buycount == 4) {
            controller.roundManager = controller.roundManager.copy(controller.roundManager.editStringValue(controller.roundManager, 29))
            buycount += 1
            return
          } else if (buycount == 5 && (controller.roundManager.players(controller.roundManager.playerturn).stringValue != 30)) {
            if (input.equals("Y")) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 30))
              return
            } else if (input.equals("N")) {
              skip = true
            } else {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 24))
            }

          } else if (buycount == 5 && ((controller.roundManager.players(controller.roundManager.playerturn).stringValue == 30) || (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 30))) {
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
          return
        }
      }

      // next Player/State
      if (skip) {
        controller.roundManager = controller.roundManager.copy(playerturn = controller.roundManager.nextPlayer())
      }
      //controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionPhase(controller.roundManager))

      if (false) {
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
