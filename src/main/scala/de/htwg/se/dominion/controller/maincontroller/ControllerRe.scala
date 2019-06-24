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
    var z = 0

    override def evaluate(input: String): Unit = {
      if (input.isEmpty) {
        return
      }
      // Action phase
      /*if(action) {
        if(runthrough == 0){
          controller.roundManager = controller.roundManager.copy(players = controller.roundManager.getHand(controller.roundManager))
        }
        for(j <- 0 until 5) {
          if(controller.roundManager.players(controller.roundManager.playerturn).hand(j).Type.equals("Money")) {

            z += 1
          }
        }
        if(z == 5){
          controller.roundManager = controller.roundManager.copy(players = controller.roundManager.updateActions(controller.roundManager,0))
        }
        if(controller.roundManager.players(controller.roundManager.playerturn).actions >= 1) {
          if(runthrough == 0) {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 3))
            runthrough += 1
          } else if(runthrough == 1 && (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 3)){
            if (input.equals("Y")) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 4))
              return
            } else if (input.equals("N")) {
              runthrough = 0
              action = false
              return
            } else {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 24))
            }
          } else if (runthrough == 1 && (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 4)) {
            if(controller.roundManager.players(controller.roundManager.playerturn).hand(input.toInt).Type.equals("Action") && controller.roundManager.players(controller.roundManager.playerturn).hand.length >= input.toInt) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionPhase2(controller.roundManager,input.toInt))
              runthrough = 2
              return
            } else {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 43))
              return
            }
          } else if (runthrough == 2 && controller.roundManager.players(controller.roundManager.playerturn).stringValue == 33) {
            if(GameTurnRe.getCardsWCost4().contains(input.toInt)) {
              controller.roundManager = controller.roundManager.copy(players = Player.updatePlayer(controller.roundManager.players, GameTurnRe.updateStacker(controller.roundManager.players(controller.roundManager.playerturn),GameTurnRe.playingDecks(input.toInt).head)))
              return
            } else {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 19))
              return
            }
          }
        } else {
          controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 42))
          action = false
        }
      }*/





      // Buy Phase
      /*if (!action) {
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
          } else if (buycount == 1 && (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 25)) {
            if (input.equals("Y")) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 30))
              return
            } else if (input.equals("N")) {
              buycount = 0
              skip = true
              action = true
              runthrough = 0
            } else {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 24))
            }

          } else if (buycount == 1 && (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 30 || controller.roundManager.players(controller.roundManager.playerturn).stringValue == 32)) {
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

      }*/

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
