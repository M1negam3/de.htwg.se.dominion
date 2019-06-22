package de.htwg.se.dominion.controller.maincontroller

object GameStatusRe extends Enumeration {

  type GameStatus = Value
  val IDLE, PREP, FTURN, TURN, END = Value

  val map = Map[GameStatus, String] (
    IDLE -> "",
    PREP -> "LEL",
    FTURN -> "LOL",
    TURN -> "LUL",
    END -> "LELE"
  )

  def message(gameStatus: GameStatus): String = {
    map(gameStatus)
  }

}