package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.deckComponent.{Cards, goldDeck, silverDeck}
import de.htwg.se.dominion.model.playerComponent.Player

object StrategyPatternForActionPhase {

  // TODO EFFEKTE UMSCHREIBEN

  var discardAmount = 0

  def getCardname(list:List[Player], playerTurn: Int, input: Int): List[Player] = {
    val l = list
    l(playerTurn).playingCards.head.CardName match {
      case "Cellar" => cellar(l, playerTurn, input)
      case "Mine" => mine(l, playerTurn, input)
      /*case "Remodel" => remodel(l, playerTurn)
      case "Workshop" => workshop(l, playerTurn)
      case "Merchant" => GameTurn.money = GameTurn.money + GameTurn.merchant(l, playerTurn)
        l*/
      case _ => l
    }
  }

  def getCardName2(list: List[Player], playerTurn: Int, input: String): List [Player] = {
    val l = list
    l(playerTurn).playingCards.head.CardName match {
      case "Cellar" => cellar2(l, playerTurn, input)
    }
  }

  def cellar(list: List[Player], idx: Int, input: Int): List[Player] = {
    var l = list
    if (input <= l(idx).hand.length) {
      discardAmount = input
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 8, l(idx).money))
    } else {
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 13, l(idx).money))
    }
    l
  }

  def cellar2(list: List[Player], idx: Int, strInput: String): List[Player] = {
    var l = list
    var test = strInput.split(" ")
    var same = false
    var draw = 0
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
      if ( test.length == discardAmount) {
        for (i <- 0 until discardAmount) {
          if (test(i).toInt < l(idx).hand.length) {
            l = Player.updatePlayer(l, GameTurnRe.updateStacker(l(idx), l(idx).hand(test(i).toInt)))
            l = Player.updatePlayer(l, GameTurnRe.removeHandcard(test(i).toInt, l(idx)))
            draw += 1
          } else {
            l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 9, l(idx).money))
          }
        }
        l = Player.updatePlayer(l, Player.draw(l(idx), draw))
        l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 100, l(idx).money))
      } else {
        l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 10, l(idx).money))
      }
    } else {
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 11, l(idx).money))
    }
    l
  }

  def mine(list: List[Player], idx: Int, input: Int): List[Player] = {
    var l = list
    if (input < l(idx).hand.length) {
      if (l(idx).hand(input).Type == "Money") {
        if (input < l(idx).hand.length) {
          if (l(idx).hand(input).CardName == "Copper") {
            l = Player.updatePlayer(l, Player.upgrading(l(idx), input, silverDeck.silverDeck))
            GameTurnRe.playingDecks = GameTurnRe.updateDeck(GameTurnRe.playingDecks, GameTurnRe.copyList(GameTurnRe.playingDecks(1)), 1)
            l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 35, l(idx).money))
          } else if (l(idx).hand(input).CardName == "Silver" || l(idx).hand(input).CardName == "Gold") {
            l = Player.updatePlayer(l, Player.upgrading(l(idx), input, goldDeck.goldDeck))
            GameTurnRe.playingDecks = GameTurnRe.updateDeck(GameTurnRe.playingDecks, GameTurnRe.copyList(GameTurnRe.playingDecks(2)), 2)
            l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 36, l(idx).money))
          }
        } else {
          l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 15, l(idx).money))
        }
      } else {
        l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 37, l(idx).money))
      }
    } else {
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 37, l(idx).money))
    }
    l
  }

  /*def mine(list: List[Player], idx: Int): List[Player] = {
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
  }*/

  /*var strategy = GameTurn.playingCards.head.CardName match {
    case "Cellar" => GameTurn.cellar(GameTurn.l, GameTurn.index)
    case "Mine" => GameTurn.mine(GameTurn.l, GameTurn.index)
    case "Remodel" => GameTurn.remodel(GameTurn.l, GameTurn.index)
    case "Workshop" => GameTurn.workshop(GameTurn.l, GameTurn.index)
    case "Merchant" =>
      GameTurn.money = GameTurn.money + GameTurn.merchant(GameTurn.l, GameTurn.index)
      GameTurn.l
    case _ => GameTurn.l
  }*/

}
