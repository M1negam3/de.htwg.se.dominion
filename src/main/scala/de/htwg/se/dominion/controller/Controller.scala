package de.htwg.se.dominion.controller
import de.htwg.se.dominion.aview.InputOutput
import de.htwg.se.dominion.util.Observable
import de.htwg.se.dominion.model.{Deck, GameStart, Player}

class Controller(var deck: Deck, var players: Vector[Player]) extends Observable {
  var i = 0
  var current: Player = players(i)
  def newGame(): Unit = {
    GameStart.createDeck(InputOutput.gamestart())
    notifyObservers()
  }

  def finish(): Unit = {
    System.exit(0)
  }
  def handToString: String = deck.toString
  def handValue() :Integer = {
    0
  }
  def handActions() :Integer = {
    0
  }
  def handBuyAddition() :Integer = {
    0
  }
}
