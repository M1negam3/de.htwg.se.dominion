package de.htwg.se.dominion.controller
import de.htwg.se.dominion.util.Observable
import de.htwg.se.dominion.model.{Deck, Player}

class Controller(var deck: Deck, var players: Vector[Player]) extends Observable {
  var i = 0
  var current: Player = players(i)
  def newGame(): Unit = notifyObservers()
  def handToString: String = deck.toString
  def handValue() :Integer = {

  }
  def handActions() :Integer = {

  }
  def handBuyAddition() :Integer = {

  }
}
