package de.htwg.se.dominion.model.playerComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.Cards

import scala.collection.mutable.ListBuffer

case class Player(name: String = "", value: Int = 0, deck: List[Cards]= Nil, stacker: List[Cards] = Nil, hand: List[Cards] = Nil, playingCards: List[Cards] = Nil, actions: Int = 1, buys: Int = 1, stringValue: Int = 0, money: Int = 0) {
  override def toString: String = this.name

  var deckLength = 0
  var copyList: List[Cards] = Nil


  def createPlayer(pCount: Int, names: List[String]): List[Player] = {
    var players = new ListBuffer[Player]
    for (i <- 0 until pCount) {
      players += Player(names(i), i + 1, Cards.shuffle(Cards.startDeck), Cards.stacker, Nil, Nil, 1, 1, 0, 0)
    }
    val Players: List[Player] = players.toList
    Players
  }

  def getHand(player: Player): Player = {
    var copiedPlayer = player
    copyList = copiedPlayer.deck
    deckLength = copyList.length
    var l = new ListBuffer[Cards]
    var d = new ListBuffer[Cards]
    copyList.length match {
      case 0 =>
        copiedPlayer = isEmpty(copiedPlayer)
        copyList = copiedPlayer.deck
        for (i <- 0 until 5) {
          l += copyList(i)
        }
      case 1 =>
        l += copyList.head
        copiedPlayer = isEmpty(copiedPlayer)
        copyList = copiedPlayer.deck
        for (i <- 0 until 4) {
          l += copyList(i)
        }
      case 2 =>
        l += copyList.head
        l += copyList(1)
        copiedPlayer = isEmpty(copiedPlayer)
        copyList = copiedPlayer.deck
        for (i <- 0 until 3) {
          l += copyList(i)
        }
      case 3 =>
        for (i <- 0 until copyList.length) {
          l += copyList(i)
        }
        copiedPlayer = isEmpty(copiedPlayer)
        copyList = copiedPlayer.deck
        for (i <- 0 until 2) {
          l += copyList(i)
        }
      case 4 =>
        for (i <- 0 until copyList.length) {
          l += copyList(i)
        }
        copiedPlayer = isEmpty(copiedPlayer)
        copyList = copiedPlayer.deck
        l += copyList.head
      case _ =>
        for (i <- 0 until 5) {
          l += copyList(i)
        }
    }
    for (f <- 5 until copyList.length) {
      d += copyList(f)
    }
    val hand: List[Cards] = l.toList
    val deck: List[Cards] = d.toList
    deckLength = 0
    Player(copiedPlayer.name, copiedPlayer.value, deck, copiedPlayer.stacker, hand, copiedPlayer.playingCards, 1, 1, 0,0)
  }

  def getMoney(player: Player): Int = {
    val copiedPlayer = player
    var m = 0
    for (i <- 0 until 5) {
      m += copiedPlayer.hand(i).MoneyValue
    }
    m
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

  def draw(player: Player, n: Integer): Player = {
    val copiedPlayer = player
    var listBuffer1: ListBuffer[Cards] = ListBuffer()
    var listBuffer2: ListBuffer[Cards] = ListBuffer()
    var listBuffer3: ListBuffer[Cards] = ListBuffer()
    var z: List[Cards] = Nil
    var x: List[Cards] = Nil
    var p = player

    for(o <- 0 until player.hand.length){
      listBuffer1 += player.hand(o)
    }
    if (player.deck.length < n) {
      p = isEmpty(player)
      listBuffer3 = listBuffer2
      for (j <- 0 until player.deck.length) {
        listBuffer2 += player.deck(j)
      }
      for (i <- 0 until p.deck.length) {
        listBuffer2 += p.deck(i)
      }
      for(i <- 0 until n){
        listBuffer1 += listBuffer2(i)
        listBuffer3 -= listBuffer2(i)
      }
      z = listBuffer1.toList
      x = listBuffer2.toList
      Player(copiedPlayer.name,copiedPlayer.value,x,p.stacker,z, copiedPlayer.playingCards, copiedPlayer.actions, copiedPlayer.buys, copiedPlayer.stringValue,copiedPlayer.money)
    }else{
      listBuffer3 = listBuffer2
      for(j <- 0 until player.deck.length){
        listBuffer2 += player.deck(j)
      }
      for (i <- 0 until n) {
        listBuffer1 += player.deck(i)
        listBuffer3 -= listBuffer2(i)
      }
      z = listBuffer1.toList
      x = listBuffer3.toList
      Player(copiedPlayer.name, copiedPlayer.value, x, copiedPlayer.stacker, z, copiedPlayer.playingCards, copiedPlayer.actions, copiedPlayer.buys, copiedPlayer.stringValue,copiedPlayer.money)
    }
  }
  def upgrading(player: Player, i : Integer, z: List[Cards]): Player  = {
    var copiedPlayer = player
    var listBuffer1: ListBuffer[Cards] = ListBuffer()
    for (j <- 0 until player.hand.length) {
      listBuffer1 += player.hand(j)
    }
    var x: List[Cards] = Nil
    listBuffer1 -= player.hand(i)
    listBuffer1 +=z.head
    x = listBuffer1.toList
    Player(copiedPlayer.name, copiedPlayer.value, copiedPlayer.deck,copiedPlayer.stacker,x, copiedPlayer.playingCards, copiedPlayer.actions, copiedPlayer.buys, copiedPlayer.stringValue,copiedPlayer.money)
  }

  def isEmpty(player: Player): Player = {
    val copiedPlayer = player
    val copiedStacker = player.stacker
    val copiedDeck = Cards.shuffle(copiedStacker)
    val stacker: List[Cards] = Nil
    new Player(copiedPlayer.name, copiedPlayer.value, copiedDeck, stacker, copiedPlayer.hand, copiedPlayer.playingCards, copiedPlayer.actions, copiedPlayer.buys, copiedPlayer.stringValue,copiedPlayer.money)
  }
  def updateMoney(player: Player,i: Int): Player ={
    val cP = player
    var m = 0
    var b = 0
    m += cP.money
    m -= i
    b += cP.buys
    b -= 1
    Player(cP.name,cP.value,cP.deck,cP.stacker,cP.hand,cP.playingCards,cP.actions,b,cP.stringValue,m)
  }
  def updateAction(player: Player,i: Int): Player ={
    val cP = player
    var a = 0
    a += cP.actions
    a -= i
    Player(cP.name,cP.value,cP.deck,cP.stacker,cP.hand,cP.playingCards,a,cP.buys,cP.stringValue,cP.money)
  }
}