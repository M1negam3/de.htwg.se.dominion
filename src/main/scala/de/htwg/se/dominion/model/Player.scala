package de.htwg.se.dominion.model

import scala.collection.mutable.ListBuffer

case class Player(name : String, value: Int, deck: List[Cards], stacker: List[Cards], hand: List[Cards]) {
  override def toString: String = this.name
}

object Player {

  def createPlayer(pCount: Int, names: List[String]): List[Player] = {
    var players = new ListBuffer[Player]
    for (i <- 0 until pCount) {
      players += new Player(names(i), i + 1, Cards.startDeck, Cards.stacker, Cards.hand)
      print("Player " + (i + 1) + " wurde erstellt!\n")
    }
    val Players: List[Player] = players.toList
    Players
  }

  def getHand(player: Player): Player = {
    var copiedplayer = player
    val copylist = copiedplayer.deck
    var l = new ListBuffer[Cards]
    var d = new ListBuffer[Cards]
    for (i <- 0 until 5) {
      l += copylist(i)
    }
    for (f <- 5 until copylist.length) {
      d += copylist(f)
    }
    val hand: List[Cards] = l.toList
    val deck: List[Cards] = d.toList
    print("Player " + copiedplayer.value + " Hand Cards are: ")
    for (f <- 0 until 4) {
      print(hand(f).CardName + ", ")
    }
    println(hand(4).CardName)
    new Player(copiedplayer.name, copiedplayer.value, deck, copiedplayer.stacker, hand)
  }
}