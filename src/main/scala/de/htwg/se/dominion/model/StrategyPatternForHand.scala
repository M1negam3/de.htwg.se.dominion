package de.htwg.se.dominion.model

import de.htwg.se.dominion.model.Player.isEmpty

import scala.collection.mutable.ListBuffer

object StrategyPatternForHand {

  var strategy: ListBuffer[Cards] = Player.deckLength match {
    case 0 => strategy0(Player.copiedPlayer, Player.copyList)
    case 1 => strategy1(Player.copiedPlayer, Player.copyList)
    case 2 => strategy2(Player.copiedPlayer, Player.copyList)
    case 3 => strategy3(Player.copiedPlayer, Player.copyList)
    case 4 => strategy4(Player.copiedPlayer, Player.copyList)
    case _ => strategy_(Player.copiedPlayer, Player.copyList)
  }

  var l = new ListBuffer[Cards]

  def strategy0(cPlayer: Player, cList: List[Cards]): ListBuffer[Cards] = {
    var copiedPlayer = cPlayer
    var copyList = cList
    copiedPlayer = isEmpty(copiedPlayer)
    copyList = copiedPlayer.deck
    l = new ListBuffer[Cards]
    for (i <- 0 until 5) {
      l += copyList(i)
    }
    l
  }

  def strategy1(cPlayer: Player, cList: List[Cards]): ListBuffer[Cards] = {
    var copiedPlayer = cPlayer
    var copyList = cList
    l = new ListBuffer[Cards]
    l += copyList.head
    copiedPlayer = isEmpty(copiedPlayer)
    copyList = copiedPlayer.deck
    for (i <- 0 until 4) {
      l += copyList(i)
    }
    l
  }

  def strategy2(cPlayer: Player, cList: List[Cards]): ListBuffer[Cards] = {
    var copiedPlayer = cPlayer
    var copyList = cList
    l = new ListBuffer[Cards]
    l += copyList.head
    l += copyList(1)
    copiedPlayer = isEmpty(copiedPlayer)
    copyList = copiedPlayer.deck
    for (i <- 0 until 3) {
      l += copyList(i)
    }
    l
  }

  def strategy3(cPlayer: Player, cList: List[Cards]): ListBuffer[Cards] = {
    var copiedPlayer = cPlayer
    var copyList = cList
    l = new ListBuffer[Cards]
    for (i <- 0 until copyList.length) {
      l += copyList(i)
    }
    copiedPlayer = isEmpty(copiedPlayer)
    copyList = copiedPlayer.deck
    for (i <- 0 until 2) {
      l += copyList(i)
    }
    l
  }

  def strategy4(cPlayer: Player, cList: List[Cards]): ListBuffer[Cards] = {
    var copiedPlayer = cPlayer
    var copyList = cList
    l = new ListBuffer[Cards]
    for (i <- 0 until copyList.length) {
      l += copyList(i)
    }
    copiedPlayer = isEmpty(copiedPlayer)
    copyList = copiedPlayer.deck
    l += copyList.head
    l
  }

  def strategy_(cPlayer: Player, cList: List[Cards]): ListBuffer[Cards] = {
    val copiedPlayer = cPlayer
    val copyList = cList
    l = new ListBuffer[Cards]
    for (i <- 0 until 5) {
      l += copyList(i)
    }
    l
  }
}
