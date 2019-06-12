package de.htwg.se.dominion.controller
import de.htwg.se.dominion.model.{InputOutput, Player}
import de.htwg.se.dominion.util.Observable

class Controller() extends Observable {
  var pCount = 0

  def newGame(): Unit = {
    pCount = InputOutput.getPlayerCount()
    val names = InputOutput.getPlayerName(pCount)
    val players = Player.createPlayer(pCount, names)
    for (i <- 0 until pCount) {
      Player.getMoney(Player.getHand(players(i)))
    }
    Player.turn(players.head)
    notifyObservers
  }
}