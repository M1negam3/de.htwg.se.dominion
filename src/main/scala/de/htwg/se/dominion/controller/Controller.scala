package de.htwg.se.dominion.controller
import de.htwg.se.dominion.aview.InputOutput
import de.htwg.se.dominion.util.Observable
import de.htwg.se.dominion.model.{Deck, Player}

class Controller() extends Observable {
  var i = 0
  var pCount = 0

  def newGame(): Unit = {
    pCount = InputOutput.getPlayerCount()
    //GameStart.createDeck(pCount)
    InputOutput.getPlayerName(pCount)
    print(InputOutput.deckCreation(pCount))
    notifyObservers()
  }

  def finish(): Unit = {
    System.exit(0)
  }

  def turn(): Unit = {

  }

  def suggestions: String = {
    "help"
  }
}
