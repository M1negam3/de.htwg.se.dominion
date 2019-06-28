package de.htwg.se.dominion.model.playerComponent.basePlayerComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.playerComponent.{PlayerInterface, StaticPlayerInterface}

import scala.collection.mutable.ListBuffer

case class Player(name: String, value: Int, deck: List[Cards], stacker: List[Cards], hand: List[Cards],playingCards: List[Cards],actions: Int,buys: Int,stringValue: Int, money: Int) extends PlayerInterface {
  override def toString: String = this.name
  override def getName: String = {
    name
  }
  override def getValue: Int= {
    value
  }
  override def getDeck: List[Cards] = {
    deck
  }
  override def getStacker: List[Cards] = {
    stacker
  }
  override def getHand: List[Cards] = {
    hand
  }
  override def getPlayingCards: List[Cards] = {
    playingCards
  }
  override def getActions: Int = {
    actions
  }
  override def getBuys: Int = {
    buys
  }
  override def getStringValue: Int = {
    stringValue
  }
  override def getMoney: Int = {
    money
  }

}
  case class StaticPlayer() extends StaticPlayerInterface {
  var deckLength = 0
  var copyList: List[Cards] = Nil


  override def createPlayer(pCount: Int, names: List[String]): List[PlayerInterface] = {
    var players = new ListBuffer[Player]
    for (i <- 0 until pCount) {
      players += new Player(names(i), i + 1, Cards.shuffle(Cards.startDeck), Cards.stacker, Nil, Nil, 1, 1, 0,0)
    }
    val Players: List[Player] = players.toList
    Players
  }

  override def getHand(player: PlayerInterface): PlayerInterface = {
    var copiedPlayer = player
    copyList = copiedPlayer.getDeck
    deckLength = copyList.length
    var l = new ListBuffer[Cards]
    var d = new ListBuffer[Cards]
    copyList.length match {
      case 0 =>
        copiedPlayer = isEmpty(copiedPlayer)
        copyList = copiedPlayer.getDeck
        for (i <- 0 until 5) {
          l += copyList(i)
        }
      case 1 =>
        l += copyList.head
        copiedPlayer = isEmpty(copiedPlayer)
        copyList = copiedPlayer.getDeck
        for (i <- 0 until 4) {
          l += copyList(i)
        }
      case 2 =>
        l += copyList.head
        l += copyList(1)
        copiedPlayer = isEmpty(copiedPlayer)
        copyList = copiedPlayer.getDeck
        for (i <- 0 until 3) {
          l += copyList(i)
        }
      case 3 =>
        for (i <- 0 until copyList.length) {
          l += copyList(i)
        }
        copiedPlayer = isEmpty(copiedPlayer)
        copyList = copiedPlayer.getDeck
        for (i <- 0 until 2) {
          l += copyList(i)
        }
      case 4 =>
        for (i <- 0 until copyList.length) {
          l += copyList(i)
        }
        copiedPlayer = isEmpty(copiedPlayer)
        copyList = copiedPlayer.getDeck
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
    new Player(copiedPlayer.getName, copiedPlayer.getValue, deck, copiedPlayer.getStacker, hand, copiedPlayer.getPlayingCards, 1, 1, 0,0)
  }

  override def getMoney(player: PlayerInterface): Int = {
    val copiedPlayer = player
    var m = 0
    for (i <- 0 until 5) {
      m += copiedPlayer.getHand(i).MoneyValue
    }
    m
  }

  override def updatePlayer(list: List[PlayerInterface], player: PlayerInterface): List[PlayerInterface] = {
    val copiedPlayer = player
    val copiedPlayerList = list
    val idx = copiedPlayer.getValue - 1
    var updatedPlayerList = new ListBuffer[PlayerInterface]
    for (i <- 0 until copiedPlayerList.length) {
      if (i == idx) {
        updatedPlayerList += copiedPlayer
      } else {
        updatedPlayerList += copiedPlayerList(i)
      }
    }
    val updatedList: List[PlayerInterface] = updatedPlayerList.toList
    updatedList
  }

  override def draw(player: PlayerInterface, n: Integer): PlayerInterface = {
    val copiedPlayer = player
    var listBuffer1: ListBuffer[Cards] = ListBuffer()
    var listBuffer2: ListBuffer[Cards] = ListBuffer()
    var listBuffer3: ListBuffer[Cards] = ListBuffer()
    var z: List[Cards] = Nil
    var x: List[Cards] = Nil
    var p = player

    for(o <-  player.getHand.indices){
      listBuffer1 += player.getHand(o)
    }
    if (player.getDeck.length < n) {
      p = isEmpty(player)
      listBuffer3 = listBuffer2
      for (j <- 0 until player.getDeck.length) {
        listBuffer2 += player.getDeck(j)
      }
      for (i <- 0 until p.getDeck.length) {
        listBuffer2 += p.getDeck(i)
      }
      for(i <- 0 until n){
        listBuffer1 += listBuffer2(i)
        listBuffer3 -= listBuffer2(i)
      }
      z = listBuffer1.toList
      x = listBuffer2.toList
      Player(copiedPlayer.getName,copiedPlayer.getValue,x,p.getStacker,z, copiedPlayer.getPlayingCards, copiedPlayer.getActions, copiedPlayer.getBuys, copiedPlayer.getStringValue,copiedPlayer.getMoney)
    }else{
      listBuffer3 = listBuffer2
      for(j <- 0 until player.getDeck.length){
        listBuffer2 += player.getDeck(j)
      }
      for (i <- 0 until n) {
        listBuffer1 += player.getDeck(i)
        listBuffer3 -= listBuffer2(i)
      }
      z = listBuffer1.toList
      x = listBuffer3.toList
      Player(copiedPlayer.getName, copiedPlayer.getValue, x, copiedPlayer.getStacker, z, copiedPlayer.getPlayingCards, copiedPlayer.getActions, copiedPlayer.getBuys, copiedPlayer.getStringValue,copiedPlayer.getMoney)
    }
  }

  override def upgrading(player: PlayerInterface, i : Integer, z: List[Cards]): PlayerInterface  = {
    var copiedPlayer = player
    var listBuffer1: ListBuffer[Cards] = ListBuffer()
    for (j <- 0 until player.getHand.length) {
      listBuffer1 += player.getHand(j)
    }
    var x: List[Cards] = Nil
    listBuffer1 -= player.getHand(i)
    listBuffer1 +=z.head
    x = listBuffer1.toList
    Player(copiedPlayer.getName, copiedPlayer.getValue, copiedPlayer.getDeck,copiedPlayer.getStacker,x, copiedPlayer.getPlayingCards, copiedPlayer.getActions, copiedPlayer.getBuys, copiedPlayer.getStringValue,copiedPlayer.getMoney)
  }

  override def isEmpty(player: PlayerInterface): PlayerInterface = {
    val copiedPlayer = player
    val copiedStacker = player.getStacker
    val copiedDeck = Cards.shuffle(copiedStacker)
    val stacker: List[Cards] = Nil
    new Player(copiedPlayer.getName, copiedPlayer.getValue, copiedDeck, stacker, copiedPlayer.getHand, copiedPlayer.getPlayingCards, copiedPlayer.getActions, copiedPlayer.getBuys, copiedPlayer.getStringValue,copiedPlayer.getMoney)
  }

  override def updateMoney(player: PlayerInterface,i: Int): PlayerInterface ={
    val cP = player
    var m = 0
    var b = 0
    m += cP.getMoney
    m -= i
    b += cP.getBuys
    b -= 1
    Player(cP.getName,cP.getValue,cP.getDeck,cP.getStacker,cP.getHand,cP.getPlayingCards,cP.getActions,b,cP.getStringValue,m)
  }

  override def updateAction(player: PlayerInterface,i: Int): PlayerInterface ={
    val cP = player
    var a = 0
    a += cP.getActions
    a -= i
    Player(cP.getName,cP.getValue,cP.getDeck,cP.getStacker,cP.getHand,cP.getPlayingCards,a,cP.getBuys,cP.getStringValue,cP.getMoney)
  }
}