package de.htwg.se.dominion.controller
import de.htwg.se.dominion.aview.InputOutput
import de.htwg.se.dominion.util.Observable
import de.htwg.se.dominion.model.{Deck, GameStart, Player}

class Controller(var deck: Deck, var players: Vector[Player]) extends Observable {
  var i = 0
  var current: Player = players(i)
  var pCount = 0

  def newGame(): Unit = {
    pCount = InputOutput.getPlayerCount()
    GameStart.createDeck(pCount)
    InputOutput.getPlayerName(pCount)
    print(InputOutput.deckCreation(pCount))
    notifyObservers()
  }

  def finish(): Unit = {
    System.exit(0)
  }

  def turn(): Unit = {
    i += 1
    if (i > pCount) {
      i = 1
    }
    //InputOutput.HandCardCreation( ,i)
  }

  def suggestions: String = {
    "help"
  }
}
