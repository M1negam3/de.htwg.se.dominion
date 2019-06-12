package de.htwg.se.dominion.controller
import de.htwg.se.dominion.model.{InputOutput, Player}
import de.htwg.se.dominion.util.Observable

class Controller() extends Observable {
  var pCount = 0

  def newGame(): Unit = {
    pCount = InputOutput.getPlayerCount()
    val names = InputOutput.getPlayerName(pCount)
    val players = Player.createPlayer(pCount, names)
    Player.getMoney(Player.getHand(players.head))
    notifyObservers
  }
}