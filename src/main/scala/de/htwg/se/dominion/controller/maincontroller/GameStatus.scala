package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.model.stringComponent.baseOutputComponent.Output
import de.htwg.se.dominion.model.stringComponent.OutputInterface

case class GameStatus (outputInterface: OutputInterface)extends Enumeration {

  type GameStatus = Value
  val IDLE, PREP, ACTION, BUY = Value

  val map: Map[GameStatus, String] = Map[GameStatus, String] (
    IDLE -> "",
    PREP -> outputInterface.printNextTurn(),
    ACTION -> outputInterface.printActionPhase(),
    BUY -> outputInterface.printBuyPhase()
    )

  def message(gameStatus: GameStatus): String = {
    map(gameStatus)
  }

}
