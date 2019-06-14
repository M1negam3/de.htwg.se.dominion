package de.htwg.se.dominion.model
import de.htwg.se.dominion.model._
import de.htwg.se.dominion.model.Player._
import scala.util.control.Breaks._

import scala.collection.mutable.ListBuffer
import scala.language.postfixOps

object RoundLogic {

  def turn1(list: List[Player]): Unit = {
    var l = list
    var money = 0
    var actionNumber = 0
    var actionString = ""
    var buys = 2
    var inputInt = 0
    var inputStr = ""
    var playingDecks = Cards.playingDeck
    var playingCards: List[Cards] = Nil
    var bufferStacker: List[Cards] = Nil

    for (i <- 0 until l.length) {
      print("---------------------- New Turn ----------------------\n \n")
      println("Player " + l(i).value + " `s turn")
      l = Player.updatePlayer(l, Player.getHand(l(i)))
      money = Player.getMoney(l(i))

      print("\n---------------------- Actionphase ----------------------\n \n")
      for (f <- 0 until 5) {
        if (l(i).hand(f).Type.equals("Action")) {
          actionNumber += 1
          actionString += l(i).hand(f).CardName + "(" + f + ")" + ", "
        }
      }
      if (actionNumber == 0) {
        println("You dont have any Actioncards to play")
      } else {
        println("Your action cards are: " + actionString)
      }
      do {
        println("Do you want to play a Card? (Y/N)")
        inputStr = scala.io.StdIn.readLine()
        if (inputStr.equals("N")) {
          break
        }
        println("Choose with a number the card to play")
            inputInt = scala.io.StdIn.readInt()-1
            playingCards = l(i).hand(inputInt) :: Nil
            actionNumber += playingCards(inputInt).ActionValue
            actionNumber -= 1
            println(playingCards)
            l = Player.updatePlayer(l, removeHandcard(inputInt,l(i)))
            println(l(i).hand)
        } while(actionNumber > 0)

      playingCards = Nil

      print("\n---------------------- Buy Phase ----------------------\n \n")
      while (buys != 0) {
        println("Your money is: " + money)
        println("Your Buy actions are: " + buys)
        print("You can buy these: ")
        for (g <- 0 until playingDecks.length) {
          if (money >= playingDecks(g).head.CostValue) {
            print(playingDecks(g).head.CardName + "{" + playingDecks(g).length + "}" + "[" + playingDecks(g).head.CostValue + "]" + "(" + g + "), ")
          }
        }
        println("Do you want to buy a Card? (Y/N)")
        inputStr = scala.io.StdIn.readLine()
        if (inputStr.equals("N")) {
          break
        }
        print("\nWhich Card do you want to buy?\n")
        inputInt = scala.io.StdIn.readInt()
        var copiedCard = playingDecks(inputInt).head
        l = Player.updatePlayer(l, updateStacker(l(i), copiedCard))
        playingDecks = updateDeck(playingDecks, copyList(playingDecks(inputInt)), inputInt)
        money = money - playingDecks(inputInt).head.CostValue
        for (h <- 0 until playingDecks.length) {
          if (playingDecks(h).isEmpty) {

          }
        }
        buys -= 1
      }


      println("You cant do anything anymore, your turn is over")
      println("Player" + l(i).value + "`s turn is over!")
      print("\n---------------------- Turn End ----------------------\n \n")
    }
  }

  def copyList(cards: List[Cards]): List[Cards] = {
    var l= new ListBuffer[Cards]

    for(j <- 1 until cards.length){
      l += cards(j)
    }
    val copiedList: List[Cards] = l.toList
    copiedList
  }

  def removeHandcard(i: Int, player: Player): Player ={
    val copiedName = player.name
    val copiedNumber = player.value
    val copiedDeck = player.deck
    val copiedStacker = player.stacker
    var listBuffer1: ListBuffer[Cards] = ListBuffer()
    for(j <- 0 until player.hand.length) {
      listBuffer1 += player.hand(j)
    }
    var z: List[Cards] = Nil
    listBuffer1.-=(player.hand(i))
    z = listBuffer1.toList
    Player(copiedName,copiedNumber,copiedDeck,copiedStacker,z)
  }

  def updateDeck(l: List[List[Cards]], o: List[Cards], i: Int): List[List[Cards]] = {
    var copiedPlayingDecks = l
    var changedList = o
    val idx = i
    var updatedPlayingDeck: ListBuffer[List[Cards]] = ListBuffer()
    for (i <- 0 until copiedPlayingDecks.length) {
      if (i == idx) {
        updatedPlayingDeck += changedList
      } else {
        updatedPlayingDeck += copiedPlayingDecks(i)
      }
    }
      val updatedList: List[List[Cards]] = updatedPlayingDeck.toList
      updatedList
  }

  def updateStacker(p: Player, c: Cards): Player = {
    var copiedPlayer = p
    val copiedCard = c
    var copiedStacker = new ListBuffer[Cards]
    for (i <- 0 until copiedPlayer.stacker.length) {
      copiedStacker += copiedPlayer.stacker(i)
    }
    copiedStacker += copiedCard
    val updatedStacker: List[Cards] = copiedStacker.toList
    new Player(copiedPlayer.name, copiedPlayer.value, copiedPlayer.deck, updatedStacker, copiedPlayer.hand)
  }

}
