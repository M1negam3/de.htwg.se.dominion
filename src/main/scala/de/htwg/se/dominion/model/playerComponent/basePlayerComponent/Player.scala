package de.htwg.se.dominion.model.playerComponent.basePlayerComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Card
import de.htwg.se.dominion.model.playerComponent.{PlayerInterface, StaticPlayerInterface}

import scala.collection.mutable.ListBuffer

case class playerInterface(name: String, value: Int, deck: List[Card], stacker: List[Card], hand: List[Card],
                           playingCards: List[Card], actions: Int, buys: Int, stringValue: Int, money: Int) extends PlayerInterface {

  override def toString: String = this.getName

  override def getName: String = {
    name
  }

  override def getValue: Int= {
    value
  }

  override def getDeck: List[Card] = {
    deck
  }

  override def getStacker: List[Card] = {
    stacker
  }

  override def getHand: List[Card] = {
    hand
  }

  override def getPlayingCards: List[Card] = {
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
  var copyList: List[Card] = Nil

  override def createPlayer(pCount: Int, names: List[String]): List[PlayerInterface] = {
    var players = new ListBuffer[playerInterface]
    for (i <- 0 until pCount) {
      players += new playerInterface(names(i), i + 1, Card.shuffle(Card.startDeck), Card.stacker, Nil, Nil, 1, 1, 0,0)
    }
    val Players: List[playerInterface] = players.toList
    Players
  }

  override def getHand(player: PlayerInterface): PlayerInterface = {
    var copiedPlayer = player
    copyList = copiedPlayer.getDeck
    deckLength = copyList.length
    var l = new ListBuffer[Card]
    var d = new ListBuffer[Card]
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
    val hand: List[Card] = l.toList
    val deck: List[Card] = d.toList
    deckLength = 0
    new playerInterface(copiedPlayer.getName, copiedPlayer.getValue, deck, copiedPlayer.getStacker, hand, copiedPlayer.getPlayingCards, 1, 1, 0,0)
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
    var listBuffer1: ListBuffer[Card] = ListBuffer()
    var listBuffer2: ListBuffer[Card] = ListBuffer()
    var listBuffer3: ListBuffer[Card] = ListBuffer()
    var z: List[Card] = Nil
    var x: List[Card] = Nil
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
      playerInterface(copiedPlayer.getName,copiedPlayer.getValue,x,p.getStacker,z, copiedPlayer.getPlayingCards, copiedPlayer.getActions, copiedPlayer.getBuys, copiedPlayer.getStringValue,copiedPlayer.getMoney)
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
      playerInterface(copiedPlayer.getName, copiedPlayer.getValue, x, copiedPlayer.getStacker, z, copiedPlayer.getPlayingCards, copiedPlayer.getActions, copiedPlayer.getBuys, copiedPlayer.getStringValue,copiedPlayer.getMoney)
    }
  }

  override def upgrading(player: PlayerInterface, i : Integer, z: List[Card]): PlayerInterface  = {
    var copiedPlayer = player
    var listBuffer1: ListBuffer[Card] = ListBuffer()
    for (j <- 0 until player.getHand.length) {
      listBuffer1 += player.getHand(j)
    }
    var x: List[Card] = Nil
    listBuffer1 -= player.getHand(i)
    listBuffer1 +=z.head
    x = listBuffer1.toList
    playerInterface(copiedPlayer.getName, copiedPlayer.getValue, copiedPlayer.getDeck,copiedPlayer.getStacker,x, copiedPlayer.getPlayingCards, copiedPlayer.getActions, copiedPlayer.getBuys, copiedPlayer.getStringValue,copiedPlayer.getMoney)
  }

  override def isEmpty(player: PlayerInterface): PlayerInterface = {
    val copiedPlayer = player
    val copiedStacker = player.getStacker
    val copiedDeck = Card.shuffle(copiedStacker)
    val stacker: List[Card] = Nil
    new playerInterface(copiedPlayer.getName, copiedPlayer.getValue, copiedDeck, stacker, copiedPlayer.getHand, copiedPlayer.getPlayingCards, copiedPlayer.getActions, copiedPlayer.getBuys, copiedPlayer.getStringValue,copiedPlayer.getMoney)
  }

  override def updateMoney(player: PlayerInterface,i: Int): PlayerInterface ={
    val cP = player
    var m = 0
    var b = 0
    m += cP.getMoney
    m -= i
    b += cP.getBuys
    b -= 1
    playerInterface(cP.getName,cP.getValue,cP.getDeck,cP.getStacker,cP.getHand,cP.getPlayingCards,cP.getActions,b,cP.getStringValue,m)
  }

  override def updateAction(player: PlayerInterface,i: Int): PlayerInterface ={
    val cP = player
    var a = 0
    a += cP.getActions
    a -= i
    playerInterface(cP.getName,cP.getValue,cP.getDeck,cP.getStacker,cP.getHand,cP.getPlayingCards,a,cP.getBuys,cP.getStringValue,cP.getMoney)
  }
}