package de.htwg.se.dominion.model
import de.htwg.se.dominion.model._
import de.htwg.se.dominion.model.Player._
import scala.util.control.Breaks._

import scala.collection.mutable.ListBuffer
import scala.language.postfixOps

object RoundLogic {

  def actionPhase(list: List[Player], idx: Int): List[Player] = {
    var l = list
    var actionNumber = 0
    var actionString = ""
    var inputStr = ""
    var z:Integer = 0
    var x:Integer = 0
    var cardNumber = 0
    var playingCards: List[Cards] = Nil
    var money = 0

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
                actionNumber += playingCards(idx).ActionValue
                actionNumber -= 1
                println(playingCards)
                l = Player.updatePlayer(l, removeHandcard(cardNumber, l(idx)))
                println(l(idx).hand)
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
    l
  }

  def buyPhase(): Unit = {

  }

  def turn1(list: List[Player]): Unit = {
    var l = list
    var empty = 0
    var playingDecks = Cards.playingDeck

    for (i <- 0 until l.length) {
      var money = 0
      var actionNumber = 0
      var actionString = ""
      var buys = 1
      var inputInt = 0
      var inputStr = ""
      var z:Integer = 0
      var x:Integer = 0
      var cardNumber = 0
      var playingCards: List[Cards] = Nil
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
      while(actionNumber != 0) {
        breakable {
          for(h <- 0 until l(i).hand.length) {
            if(l(i).hand(h).Type == "Money"){
              z +=1
            }
          }
          x= l(i).hand.length
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
                  playingCards = l(i).hand(cardNumber) :: Nil
                  actionNumber += playingCards(i).ActionValue
                  actionNumber -= 1
                  println(playingCards)
                  l = Player.updatePlayer(l, removeHandcard(cardNumber, l(i)))
                  println(l(i).hand)
                  actionString = ""


                  for (f <- 0 until l(i).hand.length) {
                    if (l(i).hand(f).Type.equals("Action")) {
                      actionString += l(i).hand(f).CardName + "(" + f + ")" + ", "
                    }
                  }
                  println("Your action cards are: " + actionString)
                  -1
                }else
                  println(Console.RED + "Please enter a digit between 0 and " + l(i).hand.length)
              } catch {
                case exception: NumberFormatException => println(Console.RED + "Please enter a correct number!")
              }
              break
            }
            //inputInt = scala.io.StdIn.readInt()

          }
        }
      }

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
            l = Player.updatePlayer(l, updateStacker(l(i), copiedCard))
            money = money - playingDecks(inputInt).head.CostValue
            playingDecks = updateDeck(playingDecks, copyList(playingDecks(inputInt)), inputInt)
            print("\nThe Card " + copiedCard.CardName + " was bought and added to your stacker\n \n")
            /*for (h <- 0 until playingDecks.length) {
              if (playingDecks(h).isEmpty) {
                if (h == 3) {
                  GameEnd.end(0)
                }
                playingDecks = updatePlayingDecks(playingDecks, h)
                empty += 1
                if (empty == 3) {
                  GameEnd.end(1)
                }
                break
              }
            }*/
            buys -= 1
          }
        }
      }

      for (e <- 0 until l(i).hand.length) {
        l = Player.updatePlayer(l, updateStacker(l(i), l(i).hand(e)))
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

}
