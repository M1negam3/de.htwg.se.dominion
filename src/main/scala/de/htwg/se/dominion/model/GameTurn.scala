package de.htwg.se.dominion.model
import de.htwg.se.dominion.model._
import de.htwg.se.dominion.model.Player._
import scala.util.control.Breaks._

import scala.collection.mutable.ListBuffer
import scala.language.postfixOps

object GameTurn {
  var actionString = ""
  var inputStr = ""
  var inputInt = 0
  var money = 0
  var buys = 1
  var draws = 0
  var empty = 0
  var end = false
  var playingDecks = Cards.playingDeck

  def actionPhase(list: List[Player], idx: Int): List[Player] = {
    // TODO INPUT Check eventuell Strings überarbeiten
    var l = list
    var actionNumber = 0
    var z:Integer = 0
    var x:Integer = 0
    var cardNumber = 0
    var playingCards: List[Cards] = Nil

    money = 0
    l = Player.updatePlayer(l, Player.getHand(l(idx)))
    money = Player.getMoney(l(idx))

    for (f <- 0 until 5) {
      if (l(idx).hand(f).Type.equals("Action")) {
        actionNumber += 1
        actionString += l(idx).hand(f).CardName + "(" + f + ")" + ", "
      }
    }
    if (actionNumber == 0) {
      println("You dont have any Actioncards to play")
    } else {
      println("Your action cards are: " + actionString)
    }
    while(actionNumber != 0) {
      breakable {
        for(h <- 0 until l(idx).hand.length) {
          if(l(idx).hand(h).Type == "Money"){
            z +=1
          }
        }
        x= l(idx).hand.length
        if (z.equals(x)) {
          actionNumber = 0
          inputStr = ""
          break
        }
        z = 0
        print("\nDo you want to play a Card? (Y/N)\n")

        inputStr = scala.io.StdIn.readLine()

        if (inputStr.equals("N")) {
          actionNumber = 0
          inputStr = ""
          break
        } else {
          println("Choose with a number the card to play")
          while(true){
            try {
              cardNumber= scala.io.StdIn.readInt()
              if (cardNumber < x) {
                playingCards = l(idx).hand(cardNumber) :: Nil
                l = Player.updatePlayer(l, removeHandcard(cardNumber, l(idx)))
                money += playingCards.head.BonusMoneyValue
                buys += playingCards.head.BuyAdditionValue
                draws += playingCards.head.DrawingValue

                l = Player.updatePlayer(l, draw(l(idx), draws))
                actionNumber += playingCards(idx).ActionValue
                actionNumber -= 1
                println(playingCards)

                l = Player.updatePlayer(l, updateStacker(l(idx), playingCards.head))
                println(l(idx).hand)
                playingCards = Nil
                actionString = ""


                for (f <- 0 until l(idx).hand.length) {
                  if (l(idx).hand(f).Type.equals("Action")) {
                    actionString += l(idx).hand(f).CardName + "(" + f + ")" + ", "
                  }
                }
                println("Your action cards are: " + actionString)
                -1
              }else
                println(Console.RED + "Please enter a digit between 0 and " + l(idx).hand.length)
            } catch {
              case exception: NumberFormatException => println(Console.RED + "Please enter a correct number!")
            }
            break
          }
          //inputInt = scala.io.StdIn.readInt()

        }

      }
    }
    actionString = ""
    l
  }

  def buyPhase(list: List[Player], idx: Int): List[Player] = {
    // TODO INPUT Check eventuell Strings überarbeiten
    // TODO check buys
    var l = list
    while (buys != 0) {
      println("Your money is: " + money)
      println("Your Buy actions are: " + buys)
      print("You can buy these: ")
      for (g <- 0 until playingDecks.length) {
        if (money >= playingDecks(g).head.CostValue) {
          print(playingDecks(g).head.CardName + "{" + playingDecks(g).length + "}" + "[" + playingDecks(g).head.CostValue + "]" + "(" + g + "), ")
        }
      }
      breakable {
        print("\nDo you want to buy a Card? (Y/N)\n")
        inputStr = scala.io.StdIn.readLine()
        if (inputStr.equals("N")) {
          buys = 0
          inputStr = ""
          break
        } else {
          print("\nWhich Card do you want to buy?\n")
          inputInt = scala.io.StdIn.readInt()
          var copiedCard = playingDecks(inputInt).head
          l = Player.updatePlayer(l, updateStacker(l(idx), copiedCard))
          money = money - playingDecks(inputInt).head.CostValue
          playingDecks = updateDeck(playingDecks, copyList(playingDecks(inputInt)), inputInt)
          print("\nThe Card " + copiedCard.CardName + " was bought and added to your stacker\n \n")
          for (h <- 0 until playingDecks.length) {
            if (playingDecks(h).isEmpty) {
              if (h == 3) {
                end = true
              }
              playingDecks = updatePlayingDecks(playingDecks, h)
              empty += 1
              if (empty == 3) {
                end = true
              }
              break
            }
          }
          buys -= 1
        }
      }
    }

    for (e <- 0 until l(idx).hand.length) {
      l = Player.updatePlayer(l, updateStacker(l(idx), l(idx).hand(e)))
    }
    println("STACKER TEST " + l(idx).stacker)
    println("HAND TEST " + l(idx).hand)
    println("DECK TEST " + l(idx).deck)
    buys = 1
    l
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

  def updatePlayingDecks(l: List[List[Cards]], idx: Int): List[List[Cards]] = {
    val copiedPD = l
    var updatedPD = new ListBuffer[List[Cards]]
    for (i <- 0 until copiedPD.length) {
      updatedPD += copiedPD(i)
      if (i == idx) {
        updatedPD -= updatedPD(i)
      }
    }
    val updatedList: List[List[Cards]] = updatedPD.toList
    updatedList
  }

  def round(pCount: Int, playerTurn: Int): Int = {
    val playerCount = pCount - 1
    var turn = playerTurn
    if (playerTurn > playerCount) {
      turn = 0
    }
    turn
  }

  def endCheck(end: Boolean): String = {
    var s =
      """
        |Press t to START the next Turn!
        |Press q to QUIT the Game!
      """.stripMargin
    if (end == true) {
      s = Output.printEnd()
    }
    s
  }
}
