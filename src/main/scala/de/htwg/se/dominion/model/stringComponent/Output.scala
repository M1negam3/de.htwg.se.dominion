package de.htwg.se.dominion.model.stringComponent

import de.htwg.se.dominion.controller.maincontroller.RoundManager
import de.htwg.se.dominion.model.playerComponent._
import de.htwg.se.dominion.model.deckComponent.Cards
import de.htwg.se.dominion.model.gameComponent.{GameTurnRe, StrategyPatternForActionPhase}

import scala.collection.mutable.ListBuffer
import de.htwg.se.dominion.model.gameComponent.GameTurnRe

object Output {

  var check = false
  var availableCards: ListBuffer[Int] = ListBuffer()
  var availableCards1: ListBuffer[Int] = ListBuffer()

  def printHeader(): String = {
    Console.BLACK +
      """
    ╔═══════════════════════════════════════════ Dominion ════════════════════════════════════════════════╗

                                            Welcome to Dominion!

                                        Press q to QUIT the Game!

    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝
    """.stripMargin
  }

  def printPrep(): String = {
    Console.BLACK +
      """
    ╔═══════════════════════════════════════════ Dominion ════════════════════════════════════════════════╗
    |
    |                                    Game preparation finished!
    |
    |                                    Press t to START the first Turn!
    |                                    Press q to QUIT the Game!
    |
    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝
    """.stripMargin
  }

  def printNextTurn(): String = {
    Console.BLACK +
      """
    ╔═══════════════════════════════════════════ Dominion ════════════════════════════════════════════════╗
    |
    |                                    Press any Button to START the next Turn!
    |                                    Press r to REDO your Turn!
    |                                    Press u to Reset the Game!
    |                                    Press q to QUIT the Game!
    |
    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝
    """.stripMargin
  }

  def printRules(): String = {
    // TODO REGELN
    Console.WHITE +
      """
        |DAS SIND DIE REGELN
      """.stripMargin
  }

  def printActionPhase(): String = {
    Console.BLACK +
      """
    ════════════════════════════════════════════ Action Phase ═════════════════════════════════════════════
    """.stripMargin
  }

  def printBuyPhase(): String = {
    Console.BLACK +
      """
    ═══════════════════════════════════════════ Buy Phase ═════════════════════════════════════════════════
    """.stripMargin
  }

  def printTurn(idx: Int): String = {
    Console.BLUE + " Player " + (idx + 1) + "`s turn!\n"
  }

  def printTurnEnd(idx: Int): String = {
    Console.BLUE + "     Player " + (idx + 1) + "`s turn ends!\n"
  }

  def printEnd(): String = {
    Console.BLACK +
      """
    ╔═══════════════════════════════════════════ Game End ════════════════════════════════════════════════╗
      |
      |                         Press e to END the Game and get the Score!
      |                         Press q to QUIT the Game!
      |
    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝
    """.stripMargin
  }

  def printScore(m: Map[Int, String]): String = {
    var s = "    ╔═══════════════════════════════════════════ Score ═══════════════════════════════════════════════════╗\n \n" +
            "                                             Player| Points \n \n"
    for ((k, v) <- m) {

      s += "                                           " + v + "| " + k + "\n"
    }
    s += "\n    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝" +
      "\n \n" + printHeader()
    s
  }
  def printPlayerQuestion(): String = {
    Console.BLUE + "\n     How many Player´s are you?(between 2 and 5)\n"
  }

  def printPlayers(r: RoundManager): String = {
    var s = ""
    for (i <- 0 until r.numberOfPlayers) {
      s += "     Player " + (i + 1) + " was created!\n"
    }
    s
  }
  def getPlayingStateString(l: List[Player], playerturn: Int, stringValue : Int): String = {
    var actionString: String = ""
    var s: String = Console.BLUE + "     Your Hand Cards are: " + l(playerturn).hand.head.CardName + Console.BLACK + " (0)\n"
    var t: String = Console.BLUE + "     Player " + (playerturn + 1) + "`s turn\n"
    var s2: String = Console.BLUE + "     Your Actions are: " + l(playerturn).actions + "\n"
    var s3: String = ""
    var z: String = ""
    var x: String = ""
    val y = l(playerturn).hand.length - 1
    availableCards = ListBuffer()
    availableCards1 = ListBuffer()

    for (i <- 1 until l(playerturn).hand.length) {
      s += Console.BLUE + "                          " + l(playerturn).hand(i).CardName + Console.BLACK + " (" + i + ")\n"
    }
    s += "\n"

    for (i <- 0 until GameTurnRe.playingDecks.length) {
      if (GameTurnRe.playingDecks(i).head.CostValue <= 4) {
        s3 += "                " + Console.BLUE + GameTurnRe.playingDecks(i).head.CardName + Console.MAGENTA + " [" + Cards.playingDeck(i).head.CostValue + "]" + Console.BLUE + " Card Effect: " + GameTurnRe.playingDecks(i).head.EffectValue + Console.BLACK + " (" + i + ")\n"
        availableCards += i
      }
    }

    for (j <- 0 until GameTurnRe.playingDecks.length) {
      if (StrategyPatternForActionPhase.discardCardValue >= Cards.playingDeck(j).head.CostValue) {
        z += "                                " + Console.BLUE + Cards.playingDeck(j).head.CardName + Console.CYAN + " {" + Cards.playingDeck(j).length + "} " + Console.MAGENTA +
          "[" + Cards.playingDeck(j).head.CostValue + "]" + Console.BLUE + " Card Effect: " + Cards.playingDeck(j).head.EffectValue + Console.BLACK + " (" + j + ")\n"
        availableCards1 += j
      }
    }

    /*for (g <- 0 until GameTurnRe.playingDecks.length) {
      if (GameTurnRe.getMoney(l(playerturn)) >= Cards.playingDeck(g).head.CostValue) {
        x += Console.BLUE + "                        " + GameTurnRe.playingDecks(g).head.CardName + Console.CYAN + " {" + GameTurnRe.playingDecks(g).length + "} " + Console.MAGENTA + "[" + GameTurnRe.playingDecks(g).head.CostValue + "]" + Console.BLUE + " Card Effect: " + GameTurnRe.playingDecks(g).head.EffectValue + Console.BLACK + " (" + g + ")" + "\n"
      }
    }*/

    stringValue match {
      case 0 => ""
      case 1 => t + s + Console.RED + "\n     You dont have any Actioncards to play\n" + Console.BLACK + "     Enter any button to continue!"
      case 2 => t + s2 + s + Console.RED + "     You dont have any Actioncards to play\n" + Console.BLACK + "     Enter any button to continue!"
      case 3 => {
        for (i <- 0 until l(playerturn).hand.length) {
          if (l(playerturn).hand(i).Type.equals("Action") && !check) {
            actionString += l(playerturn).hand(i).CardName + Console.BLACK + " (" + i + ")" + "\n"
            check = true
          } else if (l(playerturn).hand(i).Type.equals("Action") && check) {
            actionString += "                            " + Console.BLUE + l(playerturn).hand(i).CardName + Console.BLACK + " (" + i + ")" + "\n"
          }
        }
        check = false
        t + s2 + s + Console.BLUE + "     Your action cards are: " + actionString + "\n" + Console.YELLOW + "     Do you want to play a Card? (Y/N)"
      }
      case 4 => Console.BLACK + "     Enter a number to choose a card, which you want to play"
      case 5 => Console.BLUE + "     Your card effect is: " + Console.BLACK + l(playerturn).playingCards.head.EffectValue + "\n\n" + s
      case 6 => Console.BLUE + s
      case 7 => Console.BLUE + "     Your card effect is: " + Console.BLACK + l(playerturn).playingCards.head.EffectValue + "\n\n" + s + "\n" + Console.BLACK + "     Enter the amount of Cards to Discard"
      case 8 => Console.BLUE + "     Choose some Card(s), separate them with a blank"
      case 9 => Console.RED + "     Please enter a Card from your hand between 0 and " + y
      case 10 => Console.RED + "     Please enter the correct amount of Cards to discard"
      case 11 => Console.RED + "     Dont enter the same number twice"
      case 12 => Console.RED + "     Please enter a correct number!"
      case 13 => "     Choose a Card from your hand"
      case 14 => Console.BLUE + "     Your card effect is: " + Console.BLACK + l(playerturn).playingCards.head.EffectValue + "\n\n" + s + "\n" + Console.BLACK + "     Choose one Moneycard to upgrade"
      case 15 => Console.RED + "     Choose a valid Card from your hand"
      case 16 => Console.BLUE + "     Your card effect is: " + Console.BLACK + l(playerturn).playingCards.head.EffectValue + "\n\n" + s + "\n" + Console.YELLOW + "     Which card to you want to trash?"
      case 17 => Console.BLUE + "     You choose: " + Console.BLACK + l(playerturn).hand(stringValue).CardName
      case 18 => Console.BLUE + "     Choose a Card you want to add to your hand\n     You can choose a card that cost up to " + StrategyPatternForActionPhase.discardCardValue + " Money\n" +
        "     You can pick one of these: " + Console.CYAN + "{Quantity}" + Console.MAGENTA + " [Cost]" + Console.BLACK + " (PRESS)\n" + z + Console.YELLOW +
        "\n \n     Which card to you want to add to your hand?\n"
      case 19 => Console.RED + "     Invalid Input, try again"
      case 20 => Console.BLUE + "     You have a Silver on you Hand, +2 Gold"
      case 21 => Console.RED + "     Please choose a Card which is listed above!\n"
      case 22 => Console.RED + "     You dont have a Silver on your Hand"
      case 23 => Console.RED + "     You cant add that, please enter a valid number"
      case 24 => Console.RED + "     Try Y or N!"
      case 25 => Console.BLUE + " Your money is: " + GameTurnRe.getMoney(l(playerturn))
      case 26 => Console.BLUE + "     Your Buy actions are: " + l(playerturn).buys
      case 27 => Console.BLUE + "     You can buy these: " + Console.CYAN + "{Quantity}" + Console.MAGENTA + "[Cost]" + Console.BLACK + "(PRESS)\n"
      case 28 => x
      case 29 => Console.YELLOW + "\n     Do you want to buy a Card? (Y/N)\n"
      case 30 => Console.YELLOW + "\n     Which Card do you want to buy?\n"
      case 31 => Console.BLUE + "\n     The Card  was bought and added to your stacker\n \n"
      case 32 => Console.RED + "     You cant buy that, please enter a valid number"
      case 33 => Console.BLUE + "     Your card effect is: " + Console.BLACK + l(playerturn).playingCards.head.EffectValue +
        "\n\n" + s + "\n" + Console.BLACK + "     You can choose a card costing up to 4\n" + Console.BLUE +
        "     You can choose one of these:" + Console.MAGENTA + " [Cost]" + Console.BLACK + " (PRESS)\n" + s3 + "\n" + Console.YELLOW + "     Which Card do you want?\n"
      case 34 => Console.BLUE + "\n     You don´t have any buys"
      case 35 => Console.BLUE + "     A Silver Card was added to your Stacker"
      case 36 => Console.BLUE + "     A Gold Card was added to your Stacker"
      case 37 => Console.RED + "      Please choose a Money Card!"
      case 38 => Console.BLUE + "     This Card is only usefull with a Silver on your Hand, are you sure you want to play it? (0/1)\n"
      case 39 => Console.BLUE + "     Press a button to continue"
      case 40 => Console.BLUE + "     Card gained"
      case 41 => Console.RED + "      Needs to be larger than 0"
      case _ => "MÖP"
    }
  }
}
