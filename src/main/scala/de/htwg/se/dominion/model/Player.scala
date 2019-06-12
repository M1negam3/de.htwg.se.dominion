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
    var copiedPlayer = player
    var copyList = copiedPlayer.deck
    var l = new ListBuffer[Cards]
    var d = new ListBuffer[Cards]
    for (i <- 0 until 5) {
      if (i + 1 > copyList.length) {
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
  def turn(player: Player): Unit = {
    var money = 0
    var action = 0
   // var finishedTurn: List[Cards] = player.stacker
    //var test: List[Cards] = Cards.copperDeck
    /*for (j <- 0 until 4) {
      action += player.hand(j).ActionValue
    }
    println("Player " + player.value + " Action avaible are: " + action)
    if (action == 0){*/

    money= getMoney(player)
    println("Player " + player.value + " Money to buy is:" + money)
    if (money == 0) {
      println("You don´t have enough money to buy something")
    } else if (money == 1) {
      println("You can buy a coppper, press 1 to buy or 0 to not")
      if (scala.io.StdIn.readInt() == 1) {
        //player.stacker += Cards.copperDeck(1)
        //Cards.copperDeck(1) = Cards.copperDeck.head
        println("funktioniert")
      } else {
        println("nothing was bought")
      }
    } /* else if (money == 2) {
      println("You can buy a cellar and a copper")
    } else if (money == 3) {

    } else if (money == 4) {

  }*/

  }
  def isEmpty(player: Player): Player = {
    val copiedPlayer = player
    val copiedStacker = player.stacker
    val copiedDeck = Cards.shuffle(copiedStacker)
    val stacker: List[Cards] = Nil
    new Player(copiedPlayer.name, copiedPlayer.value, copiedDeck, stacker, copiedPlayer.hand)
  }
}