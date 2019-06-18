package de.htwg.se.dominion.model

import scala.collection.mutable.ListBuffer

case class Player(name : String, value: Int, deck: List[Cards], stacker: List[Cards], hand: List[Cards]) {
  override def toString: String = this.name
}

object Player {

  def createPlayer(pCount: Int, names: List[String]): List[Player] = {
    var players = new ListBuffer[Player]
    for (i <- 0 until pCount) {
      players += new Player(names(i), i + 1, Cards.shuffle(Cards.startDeck), Cards.stacker, Cards.hand)
      print("Player " + (i + 1) + " wurde erstellt!\n")
    }
    val Players: List[Player] = players.toList
    Players
  }

  def getHand(player: Player): Player = {
    // TODO STACKER reinmischen geht nicht
    var copiedPlayer = player
    var copyList = copiedPlayer.deck
    var l = new ListBuffer[Cards]
    var d = new ListBuffer[Cards]
    for (i <- 0 until 5) {
      if (i > copyList.length) {
        copiedPlayer = isEmpty(copiedPlayer)
        copyList = copiedPlayer.deck
      }
      l += copyList(i)
    }
    for (f <- 5 until copyList.length) {
      d += copyList(f)
    }
    val hand: List[Cards] = l.toList
    val deck: List[Cards] = d.toList
    print("Player " + copiedPlayer.value + " Hand Cards are: ")
    for (f <- 0 until 4) {
      print(hand(f).CardName + ", ")
    }
    println(hand(4).CardName)
    new Player(copiedPlayer.name, copiedPlayer.value, deck, copiedPlayer.stacker, hand)
  }

  def getMoney(player: Player): Int = {
    val copiedPlayer = player
    var m = 0
    for (i <- 0 until 5) {
      m += copiedPlayer.hand(i).MoneyValue
    }
    m
  }

  def isEmpty(player: Player): Player = {
    val copiedPlayer = player
    val copiedStacker = player.stacker
    val copiedDeck = Cards.shuffle(copiedStacker)
    val stacker: List[Cards] = Nil
    new Player(copiedPlayer.name, copiedPlayer.value, copiedDeck, stacker, copiedPlayer.hand)
  }

  def updatePlayer(list: List[Player], player: Player): List[Player] = {
    val copiedPlayer = player
    val copiedPlayerList = list
    val idx = copiedPlayer.value - 1
    var updatedPlayerList = new ListBuffer[Player]
    for (i <- 0 until copiedPlayerList.length) {
      if (i == idx) {
        updatedPlayerList += copiedPlayer
      } else {
        updatedPlayerList += copiedPlayerList(i)
      }
    }
    val updatedList: List[Player] = updatedPlayerList.toList
    updatedList
  }

  def draw(player: Player, n : Integer): Player ={
    val copiedPlayer = player
    var listBuffer1: ListBuffer[Cards] = ListBuffer()
    var listBuffer2: ListBuffer[Cards] = ListBuffer()
    var z: List[Cards] = Nil
    var x: List[Cards] = Nil
    var p = player
    for(o <- 0 until player.hand.length){
      listBuffer1 += player.hand(o)
    }
    if(player.deck.length < n){
      p = isEmpty(player)
      for(j <- 0 until player.deck.length){
        listBuffer2 += p.deck(j)
      }
      for(i <- 0 until n){
        listBuffer1 += p.deck(i)
        listBuffer2 -= p.deck(i)
      }
      z = listBuffer1.toList
      x = listBuffer2.toList
    }else{
      for(j <- 0 until player.deck.length){
        listBuffer2 += player.deck(j)
      }
      for(i <- 0 until n){
        listBuffer1 += player.deck(i)
        listBuffer2 -= player.deck(i)
      }
      z = listBuffer1.toList
      x = listBuffer2.toList
    }
    Player(copiedPlayer.name,copiedPlayer.value,x,copiedPlayer.stacker,z)
  }
}