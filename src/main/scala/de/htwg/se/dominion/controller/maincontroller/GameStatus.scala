package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.model.stringComponent.Output

object GameStatus extends Enumeration {

  type GameStatus = Value
  val IDLE, PREP, FTURN, TURN, END = Value

  val map = Map[GameStatus, String] (
    IDLE -> "",
    PREP -> Output.printHeader(),
    FTURN -> Output.printPrep(),
    TURN -> Output.printNextTurn(),
    END -> Output.printEnd()
    )

  def message(gameStatus: GameStatus): String = {
    map(gameStatus)
  }

}
