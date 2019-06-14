package de.htwg.se.dominion.controller
import de.htwg.se.dominion.model._
import de.htwg.se.dominion.aview.Tui
import de.htwg.se.dominion.util.Observable

class Controller() extends Observable {
  var pCount = 0
  var players: List[Player] = Nil

  def newGame(): Unit = {
    pCount = InputOutput.getPlayerCount()
    val names = InputOutput.getPlayerName(pCount)
    players = Player.createPlayer(pCount, names)
    print("\n---------------------- Game preparation finished!----------------------\n \n")
    notifyObservers
  }

  def turn(): Unit = {
    RoundLogic.turn1(players)
    notifyObservers
  }

  def help(): Unit = {
    notifyObservers
  }

  def endGame(): Unit = {
    notifyObservers
  }
}