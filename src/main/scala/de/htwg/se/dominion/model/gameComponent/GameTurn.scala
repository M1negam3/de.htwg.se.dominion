package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.deckComponent.{Cards, goldDeck, silverDeck}
import de.htwg.se.dominion.model.playerComponent.Player
import de.htwg.se.dominion.model.playerComponent.Player._
import de.htwg.se.dominion.model.stringComponent.Output

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.{break, breakable}

object GameTurn {
  var actionString = ""
  var inputStr = ""
  var inputInt = 0
  var index = 0
  var money = 0
  var buys = 1
  var draws = 0
  var empty = 0
  var end = false
  var discardNumber = ""
  var discardAmount = 0
  var boo = true
  var boo2 = true
  var playingDecks: List[List[Cards]] = Cards.playingDeck
  var playingCards: List[Cards] = Nil
  var l: List[Player] = List()

  def actionPhase(list: List[Player], idx: Int): List[Player] = {
    l = list
    index = idx
    var actionNumber = 0
    var z: Integer = 0
    var x: Integer = 0
    var y: Integer = 0
    var cardNumber = 0
    var check = false
    playingCards = Nil

    money = 0
    l = Player.updatePlayer(l, Player.getHand(l(index)))
    money = Player.getMoney(l(index))

    for (f <- 0 until 5) {
      if (l(index).hand(f).Type.equals("Action") && !check) {
        actionNumber = 1
      }
    }
    if (actionNumber == 0) {
      println(Console.BLUE + "     You dont have any Actioncards to play")
    }
    while (actionNumber > 0) {
      breakable {
        for (h <- 0 until l(index).hand.length) {
          if (l(index).hand(h).Type.equals("Action") && !check) {
            actionString += l(index).hand(h).CardName + Console.BLACK + " (" + h + ")" + "\n"
            check = true
          } else if (l(index).hand(h).Type.equals("Action") && check) {
            actionString += "                            " + Console.BLUE + l(index).hand(h).CardName + Console.BLACK + " (" + h + ")" + "\n"
          }
          if (l(index).hand(h).Type == "Money") {
            z += 1
          }
        }
        check = false
        x = l(index).hand.length
        y = l(index).hand.length - 1
        if (z.equals(x)) {
          println(Console.BLUE + "     Your actions are: " + actionNumber)
          println(Console.RED + "     You dont have any Action Cards to play!")
          actionNumber = 0
          inputStr = ""
          break
        }
        z = 0
        println(Console.BLUE + "     Your actions are: " + actionNumber)
        println(Console.BLUE + "     Your action cards are: " + actionString)
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
                if (cardNumber < x && l(index).hand(cardNumber).Type == "Action") {
                  playingCards = l(index).hand(cardNumber) :: Nil
                  l = Player.updatePlayer(l, removeHandcard(cardNumber, l(index)))
                  money += playingCards.head.BonusMoneyValue
                  buys += playingCards.head.BuyAdditionValue
                  draws += playingCards.head.DrawingValue

                  print(Console.BLUE + "     Your card effect is: "+ Console.BLACK + playingCards.head.EffectValue + "\n\n")
                  print(Console.BLUE + "     Your Hand cards are: " + l(index).hand.head.CardName + Console.BLACK + " (" + 0 + ")\n")
                  for (i <- 1 until l(index).hand.length) {
                    print(Console.BLUE + "                          " + l(index).hand(i).CardName + Console.BLACK + " (" + i + ")\n")
                  }

                  l = StrategyPatternForActionPhase.strategy

                  if (playingCards.head.CardName == "Merchant") {
                    money = money + merchant(l, idx)
                  }

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
                  l = Player.updatePlayer(l, draw(l(index), draws))
                  draws = 0
                  l = Player.updatePlayer(l, updateStacker(l(index), playingCards.head))
                  actionNumber += playingCards.head.ActionValue
                  actionNumber -= 1
                  playingCards = Nil
                  actionString = ""
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
    print(Output.printBuyPhase())
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
      print(Console.BLUE + "     You can buy these: " + Console.CYAN + "{Quantity}" + Console.MAGENTA + "[Cost]" + Console.BLACK + "(PRESS)\n")
      for (g <- 0 until playingDecks.length) {
        if (money >= playingDecks(g).head.CostValue) {
          availableCards += g
          print(Console.BLUE + "                        " + playingDecks(g).head.CardName + Console.CYAN + " {" + playingDecks(g).length + "} " + Console.MAGENTA + "[" + playingDecks(g).head.CostValue
            + "]" + Console.BLUE + " Card Effect: " + playingDecks(g).head.EffectValue + Console.BLACK + " (" + g + ")" + "\n")
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
    l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, List[Cards]()))
    buys = 1
    l
  }

  def cellar(list: List[Player], idx: Int): List[Player] = {
    var l = list
    val x = l(idx).hand.length
    val y = l(idx).hand.length - 1
    var same = false
    while(boo2)
        try {
          println(Console.BLUE + "     Enter the amount of Cards to Discard")
          discardAmount = scala.io.StdIn.readInt()
          if (discardAmount <= x){
            while(boo) {
              try {
                println(Console.BLUE + "     Choose some Card(s), separate them with a blank")
                discardNumber = scala.io.StdIn.readLine()
                val test = discardNumber.split(" ")
                if (test.length > 1) {
                  same = false
                  for (i <- 0 until test.length - 1) {
                    for (j <- 1 until test.length) {
                      if (test(i) == test(j)) {
                        same = true
                      }
                    }
                  }
                }
                if (!same) {
                if (test.length == discardAmount) {
                  for (r <- 0 until discardAmount) {
                    if (test(r).toInt < x) {
                      l = updatePlayer(l, updateStacker(l(idx), l(idx).hand((test(r).toInt))))
                      l = updatePlayer(l, removeHandcard(test(r).toInt, l(idx)))
                      draws += 1
                    } else
                      println(Console.RED + "     Please enter a Card from your hand between 0 and " + y)
                  }
                  l = updatePlayer(l, draw(l(idx), draws))
                  boo = false
                } else {
                  println(Console.RED + "     Please enter the correct amount of Cards to discard")
                }
                } else {
                  println(Console.RED + "     Dont enter the same number twice")
                }
              } catch {
                case exception: NumberFormatException => println(Console.RED + "     Please enter a correct number!")
              }
            }
            boo2 = false
          } else
            println("     Choose a Card from your hand")
        } catch {
          case exception: NumberFormatException => println(Console.RED + "     Please enter a correct number!")
        }
    l
  }

  def mine(list: List[Player], idx: Int): List[Player] = {
    var l = list
    val x = l(idx).hand.length
    var z = true
    var y = 0
    while(z) {
      try {
        println(Console.BLUE + "     Choose one Moneycard to upgrade")
        discardAmount = scala.io.StdIn.readInt()
        if(l(idx).hand(discardAmount).Type == "Money") {
          y = 1
        }
        if (discardAmount < x && y == 1){
          if(l(idx).hand(discardAmount).CardName == "Copper") {
            l = Player.updatePlayer(l, upgrading(l(idx),discardAmount, silverDeck.silverDeck))
            playingDecks = updateDeck(playingDecks, copyList(playingDecks(1)), 1)
            z = false
          } else if (l(idx).hand(discardAmount).CardName == "Silver"){
            l = Player.updatePlayer(l, upgrading(l(idx),discardAmount, goldDeck.goldDeck))
            playingDecks = updateDeck(playingDecks, copyList(playingDecks(2)), 2)
            z = false
          } else if (l(idx).hand(discardAmount).CardName == "Gold")
            l = Player.updatePlayer(l, upgrading(l(idx),discardAmount, goldDeck.goldDeck))
            playingDecks = updateDeck(playingDecks, copyList(playingDecks(2)), 2)
            z = false
        } else
          println(Console.RED + "     Choose a valid Card from you hand")
      } catch {
        case exception: NumberFormatException => println(Console.RED + "     Please enter a correct number!")
      }
    }
    l
  }

  def remodel(list: List[Player], idx: Int): List[Player] = {
    var l = list
    var discardCardValue = 0
    var availableCards: ListBuffer[Int] = ListBuffer()
    breakable {
      try {
        while (true) {
          println(Console.YELLOW + "     Which card to you want to trash?")
          inputInt = scala.io.StdIn.readInt()
          if (inputInt >= 0 && inputInt < l(idx).hand.length) {
            println(Console.BLUE + "     You choose: " + Console.BLACK + l(idx).hand(inputInt).CardName)
            discardCardValue = l(idx).hand(inputInt).CostValue
            discardCardValue = discardCardValue + 2
            l = updatePlayer(l, removeHandcard(inputInt, l(idx)))
            println(Console.BLUE + "     Choose a Card you want to add to your hand")
            println(Console.BLUE + "     You can choose a card that cost up to " + discardCardValue + " Money")
            println(Console.BLUE + "     You can pick one of these: " + Console.CYAN + "{Quantity}" + Console.MAGENTA + " [Cost]" + Console.BLACK + " (PRESS)")
            for (i <- 0 until playingDecks.length) {
              if (discardCardValue >= playingDecks(i).head.CostValue) {
                println("                         " + Console.BLUE + playingDecks(i).head.CardName + Console.CYAN + " {" + playingDecks(i).length + "} " + Console.MAGENTA + "[" + playingDecks(i).head
                  .CostValue + "]" + Console.BLUE + " Card Effect: " + playingDecks(i).head.EffectValue + Console.BLACK + " (" + i + ")")
                availableCards += i
              }
            }
              print(Console.YELLOW + "\n \n     Which card to you want to add to your hand?\n")
              while (true) {
                inputInt = scala.io.StdIn.readInt()
                if (availableCards.contains(inputInt)) {
                  l = updatePlayer(l, addCardToHand(l(idx), inputInt))
                  playingDecks = updateDeck(playingDecks, copyList(playingDecks(inputInt)), inputInt)
                  break
                } else {
                  println(Console.RED + "     You cant add that, please enter a valid number")
                }
              }
          } else {
            println(Console.RED + "     Invalid Input, try again!")
          }
        }
      } catch {
        case exception: NumberFormatException => println(Console.RED + "     Please enter a correct number!")
      }
    }
    l
  }

  def workshop (list: List[Player], idx: Integer): List[Player] = {
    var l = list
    var x = true
    var availableCards: ListBuffer[Int] = ListBuffer()
    var count = 0
    while (x) {
      println("You can choose a card costing up to 4")
      for (i <- 0 until playingDecks.length) {
        if (playingDecks(i).head.CostValue <= 4) {
          println("                " + Console.BLUE + playingDecks(i).head.CardName + " Card Effect: " + playingDecks(i).head.EffectValue + Console.BLACK + " (" + i + ")")
          availableCards += 1
          count = i
        }
      }
      while (x) {
        try {
          println("Choose one Card")
          inputInt = scala.io.StdIn.readInt()
          if(inputInt <= availableCards.length){
            l = updatePlayer(l, updateStacker(l(idx), playingDecks(inputInt).head))
            playingDecks = updateDeck(playingDecks, copyList(playingDecks(inputInt)), inputInt)
            println(l(idx).stacker)
            x = false
          } else {
            println(Console.RED + "Invalid Input, try again!")
          }
        } catch {
          case exception: NumberFormatException => println(Console.RED + "Please enter a correct number!")
        }
      }
      }
    l
  }

  def merchant(list: List[Player], idx: Int): Int = {
    var l = list
    var copiedplayer = l(idx)
    var addMoney = 0
    for (i <- 0 until copiedplayer.hand.length) {
      if (copiedplayer.hand(i).CardName.equals("Silver")) {
        addMoney = 1
      }
    }
    addMoney
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

  def addCardToHand(p : Player, idx: Int): Player = {
    val copiedPlayer = p
    var listBuffer: ListBuffer[Cards] = ListBuffer()
    for (i <- 0 until copiedPlayer.hand.length) {
      listBuffer += copiedPlayer.hand(i)
    }
    listBuffer += playingDecks(idx).head
    val updatedHand = listBuffer.toList
    new Player(copiedPlayer.name, copiedPlayer.value, copiedPlayer.deck, copiedPlayer.stacker, updatedHand)
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
    listBuffer1 -= player.hand(i)
    z = listBuffer1.toList
    Player(copiedName, copiedNumber, copiedDeck, copiedStacker, z)
  }

  def copyList(cards: List[Cards]): List[Cards] = {
    var l = new ListBuffer[Cards]

    for (j <- 1 until cards.length) {
      l += cards(j)
    }
    val copiedList: List[Cards] = l.toList
    copiedList
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