package de.htwg.se.dominion.model

import de.htwg.se.dominion.model.Player.{copyList, getMoney}

object RoundLogic {

  def turn1(list: List[Player]): Unit = {
    var l = list
    var money = 0

    for (i <- 0 until l.length) {
      println("Player" + l(i).value + " `s turn")
      l = Player.updatePlayer(l, Player.getHand(l(i)))
      money = Player.getMoney(l(i))
      println("Your money is: " + money)

      println("------Actionphase--------")

      for (f <- 0 until 5) {
        print("Your action card(s) are: ")
        if (l(i).hand(f).Type.equals("Action")) {
          print(l(i).hand(f).CardName + "(" + f + ")" + ", ")
        } else {
          println("-")
          println("You dont have any action cards!")
        }
      }

      // TODO GESPIELTE KARTEN

      println("-----------Buy Phase----------")

    }
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

}
