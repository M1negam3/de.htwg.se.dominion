package de.htwg.se.dominion.model

import de.htwg.se.dominion.model.Player.copyList

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
    println("Player " + copiedPlayer.value + " hat " + m + " Gold")
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

  def turn(player: Player): Unit = {
    var money = 0
    var action = 0
    var buyAddition = 0
    var bufferStacker: List[Cards] = Nil
    var emptynil: List[Cards] = Nil
    var actionCards = ""
    for (j <- 0 until 5) {
      action += player.hand(j).ActionValue
      buyAddition += player.hand(j).BuyAdditionValue
      actionCards += player.hand(j).Type
    }
    money = getMoney(player)
    if(actionCards == "MoneyMoneyMoneyMoneyMoney") {
      println("You don´t have any ActionCards to play")
      println("Player " + player.value + " Money to buy is: " + money +", and Buys are: " + buyAddition+1)
      if (money == 0) {
        println("You don´t have enough money to buy something")
      } else if (money == 1) {
        println("You can buy a coppper, press 1 to buy or 0 to not")
        if (scala.io.StdIn.readInt() == 1) {
          bufferStacker = player.stacker ::: Cards.copperDeck.head :: emptynil
          copyList(Cards.copperDeck)
          println("A copper has been bought and added to your stacker")
        }
      } else if (money == 2 && buyAddition == 0){
        println("You can buy a copper(1),mansion(2),cellar(3)")
        if (scala.io.StdIn.readInt() == 1) {
           bufferStacker = player.stacker ::: Cards.copperDeck.head :: emptynil
           copyList(Cards.copperDeck)
           println("A copper has been bought and added to your stacker")
        } else if (scala.io.StdIn.readInt() == 2)  {
           bufferStacker = player.stacker ::: Cards.mansionDeck.head :: emptynil
           copyList(Cards.mansionDeck)
           println("A Mansion has been bought and added to your stacker")
        } else if (scala.io.StdIn.readInt() == 3) {
           bufferStacker = player.stacker ::: Cards.cellarDeck.head :: emptynil
           copyList(Cards.cellarDeck)
           println("A Cellar has been bought and added to your stacker")
        }
      }
    } else {
      if (action == 0){
        println("You have one action to play a card choose one from your hand to play")
        println(player.hand.head.CardName+"(1) " +player.hand(1).CardName+"(2) "+player.hand(2).CardName+"(3) "+player.hand(3).CardName+"(4) "+player.hand(4).CardName+"(5)")
        if(scala.io.StdIn.readInt()== 1) {
          bufferStacker = player.stacker ::: player.hand.head :: emptynil
        }
      }
    }
  }
  def copyList(cards: List[Cards]): List[Cards] = {
    var l= new ListBuffer[Cards]
    val emptynil: List[Cards] = Nil

    for(j <- 1 until cards.length){
      l += cards(j)
    }
    val copiedList: List[Cards] = l.toList
    copiedList
  }
}