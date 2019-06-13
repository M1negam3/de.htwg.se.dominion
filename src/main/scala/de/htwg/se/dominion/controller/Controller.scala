package de.htwg.se.dominion.controller
import de.htwg.se.dominion.model.{InputOutput, Player, Cards}
import de.htwg.se.dominion.util.Observable

class Controller() extends Observable {
  var pCount = 0
  var players: List[Player] = Nil

  def newGame(): Unit = {
    pCount = InputOutput.getPlayerCount()
    val names = InputOutput.getPlayerName(pCount)
    players = Player.createPlayer(pCount, names)
    notifyObservers
  }
}