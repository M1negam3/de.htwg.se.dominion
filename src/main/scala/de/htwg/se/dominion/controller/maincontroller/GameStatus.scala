package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.model.stringComponent.baseOutputComponent.Output

object GameStatus extends Enumeration {

  type GameStatus = Value
  val IDLE, PREP, ACTION, BUY, SAVED, LOADED = Value

  val map: Map[GameStatus, String] = Map[GameStatus, String] (
    IDLE -> "",
    PREP -> Output().printNextTurn(),
    ACTION -> Output().printActionPhase(),
    BUY -> Output().printBuyPhase(),
    SAVED -> "SAVED",
    LOADED -> "LOADED"
    )

  def message(gameStatus: GameStatus): String = {
    map(gameStatus)
  }

}
