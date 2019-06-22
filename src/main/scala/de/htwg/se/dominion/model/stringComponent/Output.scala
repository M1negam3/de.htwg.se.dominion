package de.htwg.se.dominion.model.stringComponent

import de.htwg.se.dominion.controller.maincontroller.RoundManager
import de.htwg.se.dominion.model.playerComponent._
import de.htwg.se.dominion.model.deckComponent.Cards

object Output {

  var check = false

  def printHeader(): String = {
    Console.BLACK +
      """
    ╔═══════════════════════════════════════════ Dominion ════════════════════════════════════════════════╗

                                        Press n to START a NEW Game!
                                        Press h to get the Rules!
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
    |                                    Press t to START the next Turn!
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
    Console.BLUE + "     How many Player´s are you?(between 2 and 5)\n"
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
    var s: String = Console.BLUE + "     Your Hand Cards are: \n"
    var z: String = ""
    var x: String = ""
    val y = l(playerturn).hand.length - 1
    for (i <- 0 until l(playerturn).hand.length) {
      s += Console.BLUE + "          " + l(playerturn).hand(i).CardName + Console.BLACK + " (" + i + ")\n"
    }
    var s2: String = Console.BLUE + "     Your Actions are: " + l(playerturn).actions + "\n"

    for (j <- 0 until Cards.playingDeck.length) {
      z += "                         " + Console.BLUE + Cards.playingDeck(j).head.CardName + Console.CYAN + " {" + Cards.playingDeck(j).length + "} " + Console.MAGENTA + "[" + Cards.playingDeck(j).head
        .CostValue + "]" + Console.BLUE + " Card Effect: " + Cards.playingDeck(j).head.EffectValue + Console.BLACK + " (" + j + ")"
    }
    for (g <- 0 until Cards.playingDeck.length) {
      if (l(playerturn).money >= Cards.playingDeck(g).head.CostValue) {
        x += Console.BLUE + "                        " + Cards.playingDeck(g).head.CardName + Console.CYAN + " {" + Cards.playingDeck(g).length + "} " + Console.MAGENTA + "[" + Cards.playingDeck(g).head.CostValue + "]" + Console.BLUE + " Card Effect: " + Cards.playingDeck(g).head.EffectValue + Console.BLACK + " (" + g + ")" + "\n")
      }
    }
    stringValue match {
      case 0 => Console.WHITE + "     Press any button to start your turn"
      case 1 => s + Console.RED + "     You dont have any Actioncards to play"
      case 2 => s2 + Console.RED + "     You dont have any Actioncards to play"
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
        s2 + Console.BLUE + "     Your action cards are: " + actionString + "\n" + Console.YELLOW + "     Do you want to play a Card? (Y/N)"
      }
      case 4 => Console.BLACK + "     Enter a number to choose a card, which you want to play"
      case 5 => Console.BLUE + "     Your card effect is: "+ Console.BLACK + l(playerturn).playingCards.head.EffectValue + "\n\n"
      case 6 => Console.BLUE + s
      case 7 => Console.BLUE + "     Enter the amount of Cards to Discard"
      case 8 => Console.BLUE + "     Choose some Card(s), separate them with a blank"
      case 9 => Console.RED + "     Please enter a Card from your hand between 0 and " + y
      case 10 => Console.RED + "     Please enter the correct amount of Cards to discard"
      case 11 => Console.RED + "     Dont enter the same number twice"
      case 12 => Console.RED + "     Please enter a correct number!"
      case 13 => "     Choose a Card from your hand"
      case 14 => Console.BLUE + "     Choose one Moneycard to upgrade"
      case 15 => Console.RED + "     Choose a valid Card from you hand"
      case 16 => Console.YELLOW + "     Which card to you want to trash?"
      case 17 => Console.BLUE + "     You choose: " + Console.BLACK + l(playerturn).hand(stringValue).CardName
      case 18 => Console.BLUE + "     Choose a Card you want to add to your hand"
      case 19 => Console.BLUE + "     You can choose a card that cost up to " + /*discardCardValue +*/ " Money"
      case 20 => Console.BLUE + "     You can pick one of these: " + Console.CYAN + "{Quantity}" + Console.MAGENTA + " [Cost]" + Console.BLACK + " (PRESS)"
      case 21 => z
      case 22 => Console.YELLOW + "\n \n     Which card to you want to add to your hand?\n"
      case 23 => Console.RED + "     You cant add that, please enter a valid number"
      case 24 => Console.RED + "     Try Y or N!"
      case 25 => Console.BLUE + " Your money is: " + l(playerturn).money
      case 26 => Console.BLUE + "     Your Buy actions are: " + l(playerturn).buys
      case 27 => Console.BLUE + "     You can buy these: " + Console.CYAN + "{Quantity}" + Console.MAGENTA + "[Cost]" + Console.BLACK + "(PRESS)\n"
      case 28 => x
      case 29 => Console.YELLOW + "\n     Do you want to buy a Card? (Y/N)\n"
      case 30 => Console.YELLOW + "\n     Which Card do you want to buy?\n"
      case 31 => Console.BLUE + "\n     The Card " + Cards.playingDeck(stringValue).head.CardName + " was bought and added to your stacker\n \n"
      case 32 => Console.RED + "     You cant buy that, please enter a valid number"
    }
  }
}
