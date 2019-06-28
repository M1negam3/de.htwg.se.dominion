package de.htwg.se.dominion.controller.maincontroller

import java.io.BufferedReader

import de.htwg.se.dominion.controller.ControllerInterface
import de.htwg.se.dominion.controller.maincontroller.GameStatus.GameStatus
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Card
import de.htwg.se.dominion.model.gameComponent.gameTurnComponent.GameTurn
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.playerInterface
import de.htwg.se.dominion.model.stringComponent.baseOutputComponent.Output
import de.htwg.se.dominion.util.UndoManager

import scala.util.control.Breaks.{break, breakable}
import scala.collection.mutable.ListBuffer

class Controller(var roundManager: RoundManager) extends ControllerInterface {

  val undoManager = new UndoManager
  var gameStatus: GameStatus = GameStatus.IDLE
  var controllerState: ControllerState = PlayerCountState(this)

  override def eval(input: String): Unit = {
    undoManager.doStep(new SetCommand(this))
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

  override def getCurrentStateAsString: String = controllerState.getCurrentStateAsString

  override def controllerStateAsString: String = {
    controllerState match {
      case _: PlayerCountState => "PlayerCountState"
      case _: NameSetupState => "NameSetupState"
      case _: playingState => "PlayingState"
      case _: EndState => "EndState"
    }
  }

  override def getCurrentPlayerturn: Int = roundManager.playerturn

  override def getCurrentActions: Int = roundManager.players(roundManager.playerturn).getActions

  override def getCurrentBuys: Int = roundManager.players(roundManager.playerturn).getBuys

  override def getCurrentMoney: Int = roundManager.players(roundManager.playerturn).getMoney

  override def getPlayerName: String = roundManager.players(roundManager.playerturn).getName

  override def getCurrentDeck: List[Card] = roundManager.players(roundManager.playerturn).getDeck

  override def getCurrentStacker: List[Card] = roundManager.players(roundManager.playerturn).getStacker

  override def getCurrentHand: List[Card] = roundManager.players(roundManager.playerturn).getHand

  override def getCurrentPlayingDecks: List[List[Card]] = roundManager.playingDecks

  override def getCurrentPhase: Boolean = roundManager.action

  override def getCurrentPhaseAsString(b: Boolean): String = {
    b match {
      case true => "Action Phase"
      case false => "Buy Phase"
    }
  }

  override def getCurrentStringValue: Int = roundManager.players(roundManager.playerturn).getStringValue

  override def getCurrentScore: List[(Int, String)] = roundManager.score
}

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

case class PlayerCountState(controller: Controller) extends ControllerState {

  override def evaluate(input: String): Unit = {
    val number = Controller.toInt(input)
    if (number.isEmpty || number.get < 2 || number.get > 5) {
      return
    }
    controller.nextState()

    controller.roundManager = controller.roundManager.copy(numberOfPlayer = number.get)
  }

  override def getCurrentStateAsString: String = Output().printHeader() + Output().printPlayerQuestion()

  override def nextState: ControllerState = NameSetupState(controller)
}

case class NameSetupState(controller: Controller) extends ControllerState {

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

case class playingState(controller: Controller) extends ControllerState {
  var runthrough = 0
  var continue = false
  var buycount = 0
  var buy = true
  var availableCards: ListBuffer[Int] = ListBuffer()
  var skip = false
  var empty = 0
  var end = false
  var z = 0

  override def evaluate(input: String): Unit = {
    if (input.isEmpty) {
      return
    }
    // Action phase
    if (controller.roundManager.action) {
      if (controller.roundManager.players(controller.roundManager.playerturn).getActions > 0) {
        if (runthrough == 5) {
          // Card Mine
          if (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 8 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 9 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 10 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 11) {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect2(controller.roundManager, input), playingDecks = GameTurn().playingDecks)
            runthrough = 9999999
          }
          if (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 9 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 10 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 11) {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect2(controller.roundManager, input), playingDecks = GameTurn().playingDecks)
            runthrough = 5
          }

          // Card Remodel 2
          if (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 18 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 23) {
            if (Controller.toInt(input).isEmpty) {
              runthrough = 5
              return
            }
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect2(controller.roundManager, input), playingDecks = GameTurn().playingDecks)
            runthrough = 9999999
          }
          if (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 23) {
            if (Controller.toInt(input).isEmpty) {
              return
            }
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect2(controller.roundManager, input), playingDecks = GameTurn().playingDecks)
            runthrough = 5
          }
        }
        // Its a special card
        if (runthrough == 4) {
          // Card Cellar
          if (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 7 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 13 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 41 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 45) {
            if (Controller.toInt(input).isEmpty) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 45),
                playingDecks = GameTurn().playingDecks)
              runthrough = 4
              return
            }
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get),
              playingDecks = GameTurn().playingDecks)
            runthrough = 5
          }
          if (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 13 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 41) {
            if (Controller.toInt(input).isEmpty) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 45),
                playingDecks = GameTurn().playingDecks)
              runthrough = 4
              return
            }
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get),
              playingDecks = GameTurn().playingDecks)
            runthrough = 4
          }

          // Card Mine
          if (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 14 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 15 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 37 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 46) {
            if (Controller.toInt(input).isEmpty) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 46),
                playingDecks = GameTurn().playingDecks)
              runthrough = 4
              return
            }
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get),
              playingDecks = GameTurn().playingDecks)
            runthrough = 9999999
          }
          if (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 49) {
            runthrough = 9999999
          }
          if (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 15 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 37) {
            if (Controller.toInt(input).isEmpty) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 46),
                playingDecks = GameTurn().playingDecks)
              runthrough = 4
              return
            }
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get),
              playingDecks = GameTurn().playingDecks)
            runthrough = 4
          }

          // Card Remodel
          if (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 16  ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 19 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 47) {
            if (Controller.toInt(input).isEmpty) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 47),
                playingDecks = GameTurn().playingDecks)
              runthrough = 4
              return
            }
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get),
              playingDecks = GameTurn().playingDecks)
            runthrough = 5
          }
          if (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 19) {
            if (Controller.toInt(input).isEmpty) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 47),
                playingDecks = GameTurn().playingDecks)
              runthrough = 4
              return
            }
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get),
              playingDecks = GameTurn().playingDecks)
            runthrough = 4
          }

          // Card Workshop
          if (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 33 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 21 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 48) {
            if (Controller.toInt(input).isEmpty) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 48),
                playingDecks = GameTurn().playingDecks)
              runthrough = 4
              return
            }
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, input.toInt),
              playingDecks = GameTurn().playingDecks)
            runthrough = 9999999
          }
          if (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 21) {
            if (Controller.toInt(input).isEmpty) {
              controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 48),
                playingDecks = GameTurn().playingDecks)
              runthrough = 4
              return
            }
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionCardEffect1(controller.roundManager, Controller.toInt(input).get),
              playingDecks = GameTurn().playingDecks)
            runthrough = 4
          }
        }

        // Y, you wanted to play a card, which one
        if (runthrough == 3) {
          if (Controller.toInt(input).isEmpty) {
            runthrough = 3
            return
          }
          controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionPhase2(controller.roundManager, Controller.toInt(input).get),
            playingDecks = GameTurn().playingDecks)
          runthrough = 4
          if (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 5 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 20 ||
            controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 22) {
            runthrough = 9999999
          }
          if (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 9) {
            runthrough = 3
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionPhase2(controller.roundManager, Controller.toInt(input).get),
              playingDecks = GameTurn().playingDecks)
          }
        }

        // Do you want to play a Card? Just Y/N
        if (runthrough == 2) {
          if (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 1) {
            controller.gameStatus = GameStatus.BUY
            controller.roundManager = controller.roundManager.copy(playingDecks = GameTurn().playingDecks, action = false)
            runthrough = 10
          }
          if (input.equals("Y")) {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 4), playingDecks = GameTurn().playingDecks)
            runthrough = 3
          } else if (input.equals("N")) {
            controller.gameStatus = GameStatus.BUY
            controller.roundManager = controller.roundManager.copy(playingDecks = GameTurn().playingDecks, action = false)
            runthrough = 10
          } else {
            runthrough = 2
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 24), playingDecks = GameTurn().playingDecks)
          }
        }

        // Clear Actioncards and action - 1
        if (runthrough == 9999999) {
          controller.roundManager = controller.roundManager.copy(players = controller.roundManager.updateActions(controller.roundManager), playingDecks = GameTurn().playingDecks)
          runthrough = 1
        }

        // draw these Card
        if (runthrough == 0) {
          controller.roundManager = controller.roundManager.copy(players = controller.roundManager.getHand(controller.roundManager), playingDecks = GameTurn().playingDecks)
          controller.gameStatus = GameStatus.ACTION
          runthrough = 1
        }

        // first time action
        if (runthrough == 1) {
          controller.roundManager = controller.roundManager.copy(players = controller.roundManager.actionPhase(controller.roundManager), playingDecks = GameTurn().playingDecks)
          runthrough = 2
        }

      } else {
        controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 2), playingDecks = GameTurn().playingDecks,
          action = false)
        runthrough = 0
      }
    }

    // Buy Phase
    if (!controller.roundManager.action) {
      if(controller.roundManager.players(controller.roundManager.playerturn).getBuys >= 1) {
        if (buycount == 0) {
          controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 25))
          controller.roundManager = controller.roundManager.copy(players = controller.roundManager.updateMoney(controller.roundManager, GameTurn().getMoney(
            controller.roundManager.players(controller.roundManager.playerturn))))
          for (g <- 0 until GameTurn().playingDecks.length) {
            if (controller.roundManager.players(controller.roundManager.playerturn).getMoney >= GameTurn().playingDecks(g).head.CostValue) {
              availableCards += g
            }
          }
          buycount += 1
          return
        } else if (buycount == 1 && (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 25 ||
          controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 24)) {
          if (input.equals("Y")) {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 30), playingDecks = GameTurn().playingDecks)
            return
          } else if (input.equals("N")) {
            buycount = 0
            skip = true
            controller.roundManager = controller.roundManager.copy(playingDecks = GameTurn().playingDecks, action = true)
            runthrough = 0
          } else {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 24), playingDecks = GameTurn().playingDecks)
            return
          }

        } else if (buycount == 1 && (controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 30
          || controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 32 ||
          controller.roundManager.players(controller.roundManager.playerturn).getStringValue == 48)) {
          if (Controller.toInt(input).isEmpty) {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 48), playingDecks = GameTurn().playingDecks)
            return
          } else if (availableCards.contains(input.toInt)) {
            controller.roundManager = controller.roundManager.copy(players = (GameTurn().buyPhase(controller.roundManager.players,controller.roundManager.playerturn,input.toInt)),
              playingDecks = GameTurn().playingDecks)
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 31), playingDecks = GameTurn().playingDecks)
            breakable {
              for (i <- GameTurn().playingDecks.indices) {
                if (GameTurn().playingDecks(i).isEmpty) {
                  if (i == 5) {
                    controller.roundManager = controller.roundManager.copy(end = true)
                  }
                  GameTurn().playingDecks = GameTurn().updatePlayingDecks(GameTurn().playingDecks, i)
                  controller.roundManager = controller.roundManager.copy(empty = controller.roundManager.empty + 1, playingDecks = GameTurn().playingDecks)
                  if (controller.roundManager.empty == 3) {
                    controller.roundManager = controller.roundManager.copy(end = true)
                  }
                  break
                }
              }
            }
            return
          } else {
            controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 32), playingDecks = GameTurn().playingDecks)
            return
          }
        }
      } else {
        controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 34), playingDecks = GameTurn().playingDecks)
        controller.roundManager = controller.roundManager.copy(players = GameTurn().clearHand(controller.roundManager.players,controller.roundManager.playerturn),
          playingDecks = GameTurn().playingDecks, action = true)
        buycount = 0
        skip = true
        runthrough = 0
      }
    }

    breakable {
      for (i <- GameTurn().playingDecks.indices) {
        if (GameTurn().playingDecks(i).isEmpty) {
          if (i == 5) {
            controller.roundManager = controller.roundManager.copy(end = true)
          }
          GameTurn().playingDecks = GameTurn().updatePlayingDecks(GameTurn().playingDecks, i)
          controller.roundManager = controller.roundManager.copy(empty = controller.roundManager.empty + 1, playingDecks = GameTurn().playingDecks)
          if (controller.roundManager.empty == 3) {
            controller.roundManager = controller.roundManager.copy(end = true)
          }
          break
        }
      }
    }

    // next Player/State
    if (skip) {
      controller.roundManager = controller.roundManager.copy(playerturn = controller.roundManager.nextPlayer(), playingDecks = GameTurn().playingDecks)
      controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 0), playingDecks = GameTurn().playingDecks)
      controller.gameStatus = GameStatus.PREP
      skip = false
    }

    if (controller.roundManager.end) {
      controller.gameStatus = GameStatus.IDLE
      controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 0), playingDecks = GameTurn().playingDecks)
      controller.roundManager = controller.roundManager.copy(players = controller.roundManager.end(controller.roundManager), playingDecks = GameTurn().playingDecks)
      controller.roundManager = controller.roundManager.copy(score = controller.roundManager.score(controller.roundManager), playingDecks = GameTurn().playingDecks)
      controller.nextState()
    }
  }

  override def getCurrentStateAsString: String = Output().getPlayingStateString(controller.roundManager.players,
    controller.roundManager.playerturn, controller.roundManager.players(controller.roundManager.playerturn).getStringValue)

  override def nextState: ControllerState = EndState(controller)
}

case class EndState(controller: Controller) extends ControllerState {
  override def evaluate(input: String): Unit = {}

  override def getCurrentStateAsString: String = Output().printScore(controller.roundManager.score)

  override def nextState: ControllerState = this
}