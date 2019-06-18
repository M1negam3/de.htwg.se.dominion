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
  var playingDecks: List[List[Cards]] = Cards.playingDeck

  def actionPhase(list: List[Player], idx: Int): List[Player] = {
    var l = list
    var actionNumber = 0
    var z: Integer = 0
    var x: Integer = 0
    var y: Integer = 0
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
      println(Console.BLUE + "     You dont have any Actioncards to play")
    } else {
      println(Console.BLUE + "     Your action cards are: " + actionString)
    }
    while (actionNumber != 0) {
      breakable {
        for (h <- 0 until l(idx).hand.length) {
          if (l(idx).hand(h).Type == "Money") {
            z += 1
          }
        }
        x = l(idx).hand.length
        y = l(idx).hand.length - 1
        if (z.equals(x)) {
          actionNumber = 0
          inputStr = ""
          break
        }
        z = 0
        print(Console.YELLOW + "\n     Do you want to play a Card? (Y/N)\n")
        while (true) {
          inputStr = scala.io.StdIn.readLine()
          if (inputStr.equals("N")) {
            actionNumber = 0
            inputStr = ""
            break
          } else if (inputStr.equals("Y")) {
            println(Console.BLACK + "     Enter a number to choose a card, which you want to play")
            while (true) {
              try {
                cardNumber = scala.io.StdIn.readInt()
                if (cardNumber < x && l(idx).hand(cardNumber).Type == "Action") {
                  playingCards = l(idx).hand(cardNumber) :: Nil
                  l = Player.updatePlayer(l, removeHandcard(cardNumber, l(idx)))
                  money += playingCards.head.BonusMoneyValue
                  buys += playingCards.head.BuyAdditionValue
                  draws += playingCards.head.DrawingValue
                  l = Player.updatePlayer(l, draw(l(idx), draws))
                  draws = 0
                  actionNumber += playingCards.head.ActionValue
                  actionNumber -= 1
                  println(playingCards)
                  println(l(idx).hand)
                  playingCards = Nil
                  l = Player.updatePlayer(l, updateStacker(l(idx), playingCards.head))
                  actionString = ""

                  for (f <- 0 until l(idx).hand.length) {
                    if (l(idx).hand(f).Type.equals("Action")) {
                      actionString += l(idx).hand(f).CardName + "(" + f + ")" + ", "
                    }
                  }
                  println(Console.BLUE + "     Your action cards are: " + actionString)
                } else {
                  println(Console.RED + "     Please enter an Actioncard between 0 and " + y)
                }
              } catch {
                case exception: NumberFormatException => println(Console.RED + "      Please enter a correct number!")
              }
              break
            }
          } else {
            println(Console.RED + "     Try Y or N!")
          }
        }
      }
    }
    actionString = ""
    l
  }

  def buyPhase(list: List[Player], idx: Int): List[Player] = {
    var l = list
    var availableCards: ListBuffer[Int] = ListBuffer()
    while (buys > 0) {
      inputInt = 0
      inputStr = ""
      println(Console.BLUE + " Your money is: " + money)
      println(Console.BLUE + "     Your Buy actions are: " + buys)
      print(Console.BLUE + "     You can buy these: ")
      for (g <- 0 until playingDecks.length) {
        if (money >= playingDecks(g).head.CostValue) {
          availableCards += g
          print(playingDecks(g).head.CardName + "{" + playingDecks(g).length + "}" + "[" + playingDecks(g).head.CostValue + "]" + "(" + g + "), ")
        }
      }
      breakable {
        print(Console.YELLOW + "\n     Do you want to buy a Card? (Y/N)\n")
        while (buys > 0) {
          inputStr = scala.io.StdIn.readLine()
          if (inputStr.equals("N")) {
            buys = 0
            inputStr = ""
            break
          }
          else if (inputStr.equals("Y")) {
            while (buys > 0) {
              print(Console.YELLOW + "\n     Which Card do you want to buy?\n")
              try {
                inputInt = scala.io.StdIn.readInt()
                if (availableCards.contains(inputInt)) {
                  var copiedCard = playingDecks(inputInt).head
                  l = Player.updatePlayer(l, updateStacker(l(idx), copiedCard))
                  money = money - playingDecks(inputInt).head.CostValue
                  playingDecks = updateDeck(playingDecks, copyList(playingDecks(inputInt)), inputInt)
                  print(Console.BLUE + "\n     The Card " + copiedCard.CardName + " was bought and added to your stacker\n \n")
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
                  break
                } else {
                  println(Console.RED + "     You cant buy that, please enter a valid number")
                }
              } catch {
                case exception: NumberFormatException => println(Console.RED + "     Please enter a correct number!")
              }
            }
          } else {
            println(Console.RED + "     Try Y or N!")
          }
        }
      }
      buys -= 1
    }

    for (e <- 0 until l(idx).hand.length) {
      l = Player.updatePlayer(l, updateStacker(l(idx), l(idx).hand(e)))
    }
    buys = 1
    l
  }

  def copyList(cards: List[Cards]): List[Cards] = {
    var l = new ListBuffer[Cards]

    for (j <- 1 until cards.length) {
      l += cards(j)
    }
    val copiedList: List[Cards] = l.toList
    copiedList
  }

  def removeHandcard(i: Int, player: Player): Player = {
    val copiedName = player.name
    val copiedNumber = player.value
    val copiedDeck = player.deck
    val copiedStacker = player.stacker
    var listBuffer1: ListBuffer[Cards] = ListBuffer()
    for (j <- 0 until player.hand.length) {
      listBuffer1 += player.hand(j)
    }
    var z: List[Cards] = Nil
    listBuffer1.-=(player.hand(i))
    z = listBuffer1.toList
    Player(copiedName, copiedNumber, copiedDeck, copiedStacker, z)
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
      Console.BLACK +
      """
        |     Press t to START the next Turn!
        |     Press q to QUIT the Game!
      """.stripMargin
    if (end) {
      s = Output.printEnd()
    }
    s
  }
}
