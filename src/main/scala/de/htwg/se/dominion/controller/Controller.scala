package de.htwg.se.dominion.controller
import de.htwg.se.dominion.util.Observable

class Controller() extends Observable {

  def newGame(): Unit = {
    notifyObservers
  }
}