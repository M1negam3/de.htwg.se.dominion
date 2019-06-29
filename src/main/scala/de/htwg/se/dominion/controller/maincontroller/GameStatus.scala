package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.model.stringComponent.Output

object GameStatus extends Enumeration {

  type GameStatus = Value
  val IDLE, PREP, ACTION, BUY = Value

  val map = Map[GameStatus, String] (
    IDLE -> "",
    PREP -> Output().printNextTurn(),
    ACTION -> Output().printActionPhase(),
    BUY -> Output().printBuyPhase()
    )

  def message(gameStatus: GameStatus): String = {
    map(gameStatus)
  }
}
