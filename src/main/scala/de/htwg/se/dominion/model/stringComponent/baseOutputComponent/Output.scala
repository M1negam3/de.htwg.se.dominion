package de.htwg.se.dominion.model.stringComponent.baseOutputComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.gameComponent.gameTurnComponent.{GameTurn, StrategyPatternForActionPhase}
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player
import de.htwg.se.dominion.model.stringComponent.OutputInterface

case class Output() extends OutputInterface {

  var check = false

  override def printHeader(): String = {
    Console.BLACK +
      """
    ╔═══════════════════════════════════════════ Dominion ════════════════════════════════════════════════╗

                                            Welcome to Dominion!

                                        Press q to QUIT the Game!

    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝
    """.stripMargin
  }

  override def printPrep(): String = {
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

  override def printNextTurn(): String = {
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

  override def printActionPhase(): String = {
    Console.BLACK +
      """
    ════════════════════════════════════════════ Action Phase ═════════════════════════════════════════════
    """.stripMargin
  }

  override def printBuyPhase(): String = {
    Console.BLACK +
      """
    ═══════════════════════════════════════════ Buy Phase ═════════════════════════════════════════════════
    """.stripMargin
  }

  override def printTurn(idx: Int): String = {
    Console.BLUE + " Player " + (idx + 1) + "`s turn!\n"
  }

  override def printTurnEnd(idx: Int): String = {
    Console.BLUE + "     Player " + (idx + 1) + "`s turn ends!\n"
  }

  override def printEnd(): String = {
    Console.BLACK +
      """
    ╔═══════════════════════════════════════════ Game End ════════════════════════════════════════════════╗

                                          Press q to QUIT the Game!

    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝
    """.stripMargin
  }

  override def printScore(m: List[(Int, String)]): String = {
    var s = Console.BLACK + "    ╔═══════════════════════════════════════════ Score ═══════════════════════════════════════════════════╗\n \n" +
            "                                            Player | Points \n \n"
    for (i <- 0 until m.length) {

      s += "                                             " + m(i)._2 + " | " + m(i)._1 + "\n"
    }
    s += "\n    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝" +
      "\n \n" + printEnd()
    s
  }
  override def printPlayerQuestion(): String = {
    Console.BLUE + "\n     How many Player´s are you?(between 2 and 5)\n"
  }

  override def getPlayingStateString(l: List[Player], playerturn: Int, stringValue : Int): String = {
    var actionString: String = ""
    var s: String = ""
    var t: String = Console.BLUE + "     Player " + (playerturn + 1) + "`s turn\n"
    var s2: String = Console.BLUE + "     Your Actions are: " + l(playerturn).actions + "\n"
    var s3: String = ""
    var z: String = ""
    var x: String = ""
    var s4: String = ""
    val y = l(playerturn).hand.length - 1

    stringValue match {
      case 0 => ""
      case 1 => {
        s = Console.BLUE + "     Your Hand Cards are: " + l(playerturn).hand.head.CardName + Console.BLACK + " (0)\n"
        for (i <- 1 until l(playerturn).hand.length) {
          s += Console.BLUE + "                          " + l(playerturn).hand(i).CardName + Console.BLACK + " (" + i + ")\n"
        }
        s += "\n"
        t + s2 + s + Console.RED + "\n     You dont have any Actions/Actioncards to play\n"
      }
      case 2 => {
        s = Console.BLUE + "     Your Hand Cards are: " + l(playerturn).hand.head.CardName + Console.BLACK + " (0)\n"
        for (i <- 1 until l(playerturn).hand.length) {
          s += Console.BLUE + "                          " + l(playerturn).hand(i).CardName + Console.BLACK + " (" + i + ")\n"
        }
        s += "\n"
        t +s2 + s + Console.RED + "     You dont have any Actions/Actioncards to play\n"
      }
      case 3 => {
        for (i <- 0 until l(playerturn).hand.length) {
          if (l(playerturn).hand(i).Type.equals("Action") && !check) {
            actionString += l(playerturn).hand(i).CardName + Console.BLACK + " (" + i + ")" + "\n"
            check = true
          } else if (l(playerturn).hand(i).Type.equals("Action") && check) {
            actionString += "                            " + Console.BLUE + l(playerturn).hand(i).CardName + Console.BLACK + " (" + i + ")" + "\n"
          }
        }
        s = Console.BLUE + "     Your Hand Cards are: " + l(playerturn).hand.head.CardName + Console.BLACK + " (0)\n"
        for (i <- 1 until l(playerturn).hand.length) {
          s += Console.BLUE + "                          " + l(playerturn).hand(i).CardName + Console.BLACK + " (" + i + ")\n"
        }
        s += "\n"
        check = false
        t + s2 + s + Console.BLUE + "     Your action cards are: " + actionString + "\n" + Console.YELLOW + "     Do you want to play a Card? (Y/N)"
      }
      case 4 => Console.BLACK + "     Enter a number to choose a card, which you want to play"
      case 5 => {
        for (i <- 1 until l(playerturn).hand.length) {
          s4 += Console.BLUE + "                          " + l(playerturn).hand(i).CardName + Console.BLACK + " (" + i + ")\n"
        }
        Console.BLUE + "     Your card effect is: " + Console.BLACK + l(playerturn).playingCards.head.EffectValue + "\n\n" + Console.BLUE + "     Your Hand Cards are: " + l(playerturn).hand.head.CardName + Console.BLACK + " (0)\n" + s4 + "\n"
      }
      case 6 => {
        s = Console.BLUE + "     Your Hand Cards are: " + l(playerturn).hand.head.CardName + Console.BLACK + " (0)\n"
        for (i <- 1 until l(playerturn).hand.length) {
          s += Console.BLUE + "                          " + l(playerturn).hand(i).CardName + Console.BLACK + " (" + i + ")\n"
        }
        s += "\n"
        Console.BLUE + s
      }
      case 7 => {
        s = Console.BLUE + "     Your Hand Cards are: " + l(playerturn).hand.head.CardName + Console.BLACK + " (0)\n"
        for (i <- 1 until l(playerturn).hand.length) {
          s += Console.BLUE + "                          " + l(playerturn).hand(i).CardName + Console.BLACK + " (" + i + ")\n"
        }
        s += "\n"
        Console.BLUE + "     Your card effect is: " + Console.BLACK + l(playerturn).playingCards.head.EffectValue + "\n\n" + s + "\n" + Console.BLACK + "     Enter the amount of Cards to Discard"
      }
      case 8 => Console.BLUE + "     Choose some Card(s), separate them with a blank"
      case 9 => Console.RED + "     Please enter a Card from your hand between 0 and " + y
      case 10 => Console.RED + "     Please enter the correct amount of Cards to discard"
      case 11 => Console.RED + "     Dont enter the same number twice"
      case 12 => Console.RED + "     Please enter a correct number!"
      case 13 => Console.RED + "     Needs to be smaller or equal to your Hand Card length!"
      case 14 => {
        s = Console.BLUE + "     Your Hand Cards are: " + l(playerturn).hand.head.CardName + Console.BLACK + " (0)\n"
        for (i <- 1 until l(playerturn).hand.length) {
          s += Console.BLUE + "                          " + l(playerturn).hand(i).CardName + Console.BLACK + " (" + i + ")\n"
        }
        s += "\n"
        Console.BLUE + "     Your card effect is: " + Console.BLACK + l(playerturn).playingCards.head.EffectValue + "\n\n" + s + "\n" + Console.BLACK + "     Choose one Moneycard to upgrade"
      }
      case 15 => Console.RED + "     Choose a valid Card from your hand"
      case 16 => {
        s = Console.BLUE + "     Your Hand Cards are: " + l(playerturn).hand.head.CardName + Console.BLACK + " (0)\n"
        for (i <- 1 until l(playerturn).hand.length) {
          s += Console.BLUE + "                          " + l(playerturn).hand(i).CardName + Console.BLACK + " (" + i + ")\n"
        }
        s += "\n"
        Console.BLUE + "     Your card effect is: " + Console.BLACK + l(playerturn).playingCards.head.EffectValue + "\n\n" + s + "\n" + Console.YELLOW + "     Which card to you want to trash?"
      }
      //case 17 => Console.BLUE + "     You choose: " + Console.BLACK + l(playerturn).hand(stringValue).CardName
      case 18 => {
        for (j <- 0 until GameTurn.playingDecks.length) {
          if (StrategyPatternForActionPhase.discardCardValue >= Cards.playingDeck(j).head.CostValue) {
            z += "                                " + Console.BLUE + Cards.playingDeck(j).head.CardName + Console.CYAN + " {" + Cards.playingDeck(j).length + "} " + Console.MAGENTA +
              "[" + Cards.playingDeck(j).head.CostValue + "]" + Console.BLUE + " Card Effect: " + Cards.playingDeck(j).head.EffectValue + Console.BLACK + " (" + j + ")\n"
          }
        }
        Console.BLUE + "     Choose a Card you want to add to your hand\n     You can choose a card that cost up to " + StrategyPatternForActionPhase.discardCardValue + " Money\n" +
          "     You can pick one of these: " + Console.CYAN + "{Quantity}" + Console.MAGENTA + " [Cost]" + Console.BLACK + " (PRESS)\n" + z + Console.YELLOW +
          "\n \n     Which card to you want to add to your hand?\n"
      }
      case 19 => Console.RED + "     Invalid Input, try again"
      case 20 => Console.BLUE + "     You have a Silver on your Hand, +1 Gold"
      case 21 => Console.RED + "     Please choose a Card which is listed above!\n"
      case 22 => Console.RED + "     You dont have a Silver on your Hand, Gold stays the same"
      case 23 => Console.RED + "     You cant add that, please enter a valid number"
      case 24 => Console.RED + "     Try Y or N!"
      case 25 => var j = GameTurn().getMoney(l(playerturn))
        for (g <- 0 until GameTurn.playingDecks.length) {
          if (j >= GameTurn.playingDecks(g).head.CostValue) {
            x += Console.BLUE + "                        " + GameTurn.playingDecks(g).head.CardName + Console.CYAN + " {" + GameTurn.playingDecks(g).length + "} " + Console.MAGENTA + "[" + GameTurn.playingDecks(g).head.CostValue + "]" + Console.BLUE + " Card Effect: " + GameTurn.playingDecks(g).head.EffectValue + Console.BLACK + " (" + g + ")" + "\n"
          }
        }
        Console.BLUE + "     Your money is: " + GameTurn().getMoney(l(playerturn)) + "\n" + "     Your Buy actions are: " + l(playerturn).buys +"\n" + "     You can buy these: " + Console.CYAN + "{Quantity}" + Console.MAGENTA + "[Cost]" + Console.BLACK + "(PRESS)\n" + x + "\n     Do you want to buy a Card? (Y/N)\n"
      case 26 => Console.BLUE + "     Your Buy actions are: " + l(playerturn).buys
      case 27 => Console.BLUE + "     You can buy these: " + Console.CYAN + "{Quantity}" + Console.MAGENTA + "[Cost]" + Console.BLACK + "(PRESS)\n"
      /*case 28 => var j = GameTurn.getMoney(l(playerturn))
        for (g <- 0 until GameTurn.playingDecks.length) {
          if (j >= GameTurn.playingDecks(g).head.CostValue) {
            x += Console.BLUE + "                        " + GameTurn.playingDecks(g).head.CardName + Console.CYAN + " {" + GameTurn.playingDecks(g).length + "} " + Console.MAGENTA + "[" + GameTurn.playingDecks(g).head.CostValue + "]" + Console.BLUE + " Card Effect: " + GameTurn.playingDecks(g).head.EffectValue + Console.BLACK + " (" + g + ")" + "\n"
          }
        }
        x*/
      case 29 => Console.YELLOW + "\n     Do you want to buy a Card? (Y/N)\n"
      case 30 => Console.YELLOW + "     Which Card do you want to buy?\n"
      case 31 => Console.BLUE + "\n     The Card  was bought and added to your stacker\n \n"
      case 32 => Console.RED + "     You cant buy that, please enter a valid number"
      case 33 => {
        for (i <- 0 until GameTurn.playingDecks.length) {
          if (GameTurn.playingDecks(i).head.CostValue <= 4) {
            s3 += "                " + Console.BLUE + GameTurn.playingDecks(i).head.CardName + Console.MAGENTA + " [" + Cards.playingDeck(i).head.CostValue + "]" + Console.BLUE + " Card Effect: " + GameTurn.playingDecks(i).head.EffectValue + Console.BLACK + " (" + i + ")\n"
          }
        }
        Console.BLUE + "     Your card effect is: " + Console.BLACK + l(playerturn).playingCards.head.EffectValue +
          "\n\n" + s + "\n" + Console.BLACK + "     You can choose a card costing up to 4\n" + Console.BLUE +
          "     You can choose one of these:" + Console.MAGENTA + " [Cost]" + Console.BLACK + " (PRESS)\n" + s3 + "\n" + Console.YELLOW + "     Which Card do you want?\n"
      }
      case 34 => Console.BLUE + "\n     You don´t have any buys"
      case 35 => Console.BLUE + "     A Silver Card was added to your Stacker"
      case 36 => Console.BLUE + "     A Gold Card was added to your Stacker"
      case 37 => Console.RED + "      Please choose a Money Card!"
      case 38 => Console.BLUE + "     Your card effect is: " + Console.BLACK + l(playerturn).playingCards.head.EffectValue + "\n\n" +
        s + "\n"
      case 39 => Console.BLUE + "     Press a button to continue"
      case 40 => Console.BLUE + "     Card gained"
      case 41 => Console.RED + "      Needs to be larger than 0!"
      case 42 => Console.RED + "\n     You don´t have any actions left and/or you dont have any Actioncards to play\n"
      case 43 => Console.RED + "\n     Please choose a Action card from your hand"
      case 44 => Console.BLUE + "\n     You successfully played a Card"
      case 45 => Console.RED + "      Enter numbers HUAN!\n"
      case 46 => Console.RED + "      Enter numbers HUAN!\n"
      case 47 => Console.RED + "      Enter numbers HUAN!\n"
      case 48 => Console.RED + "      Enter numbers HUAN!\n"
      case 49 => {
        s = Console.BLUE + "     Your Hand Cards are: " + l(playerturn).hand.head.CardName + Console.BLACK + " (0)\n"
        for (i <- 1 until l(playerturn).hand.length) {
          s += Console.BLUE + "                          " + l(playerturn).hand(i).CardName + Console.BLACK + " (" + i + ")\n"
        }
        s += "\n"
        Console.BLUE + "     Your card effect is: " + Console.BLACK + l(playerturn).playingCards.head.EffectValue + "\n\n" + s + "\n" + Console.RED + "      You dont have a Money Card on your Hand to upgrade!\n"
      }
    }
  }
}
