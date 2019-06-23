package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.controller.ControllerInterface
import de.htwg.se.dominion.controller.maincontroller.GameStatus.GameStatus
import de.htwg.se.dominion.model.deckComponent.Cards
import de.htwg.se.dominion.model.gameComponent.GameInitRe
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
      if (number.isEmpty) {
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

    var phase = 0

    override def evaluate(input: String): Unit = {
      var skip = false
      if (input.isEmpty) {
        return
      }

      // Action phase
      runthrough += 1
      if (runthrough > 3 && controller.roundManager.players(controller.roundManager.playerturn).stringValue == 5) {
        controller.roundManager = controller.roundManager.copy() //TODO
      }
      if (runthrough > 2 && controller.roundManager.players(controller.roundManager.playerturn).stringValue == 4) {
        if (Controller.toInt(input).isEmpty) {
          return
        }
        controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionPhase2(controller.roundManager, Controller.toInt(input).get))
      }
      if (runthrough > 1 && (controller.roundManager.players(controller.roundManager.playerturn).stringValue == 3 ||controller.roundManager.players(controller.roundManager.playerturn).stringValue == 24 )) {
        if (input.equals("Y")) {
          controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 4))
          return
        }
        if (input.equals("N")) {
          phase = 1
        } else {
          controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 24))
        }
      }
      if (runthrough == 1 || skip) {
        controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionPhase(controller.roundManager))
      }



      // Buy Phase
      if (phase == 1) {

      }

      // next Player/State
      if (skip) {
        controller.roundManager = controller.roundManager.copy(playerturn = controller.roundManager.nextPlayer())
      }
      controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionPhase(controller.roundManager))
      //BuyPhase
      if(buy == true) {
        if(buycount == 0){
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
          for (g <- 0 until Cards.playingDeck.length) {
            if (controller.roundManager.players(controller.roundManager.playerturn).money >= Cards.playingDeck(g).head.CostValue) {
              availableCards += g
            }
          }
          controller.roundManager = controller.roundManager.copy(controller.roundManager.editStringValue(controller.roundManager, 28))
          buycount += 1
          return
        } else if (buycount == 4) {
          breakable {
            controller.roundManager = controller.roundManager.copy(controller.roundManager.editStringValue(controller.roundManager, 29))
          }
        } else if (buycount == 5) {
          while (controller.roundManager.players(controller.roundManager.playerturn).buys > 0) {
            if (input.equals("Y")) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 30))
            } else if (input.equals("N")) {
              skip = true
            } else {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 24))
            }
        }


      }
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
