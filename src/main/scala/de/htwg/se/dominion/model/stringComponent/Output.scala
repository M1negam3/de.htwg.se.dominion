package de.htwg.se.dominion.model.stringComponent

import de.htwg.se.dominion.controller.maincontroller.RoundManager
import de.htwg.se.dominion.model.playerComponent._
import de.htwg.se.dominion.model.deckComponent.Cards

object Output {

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
    var s: String = "     Your Hand Cards are: \n"
    var z: String = ""
    val y = l(playerturn).hand.length - 1
    for (i <- 0 until l(playerturn).hand.length) {
      s += "          " + l(playerturn).hand(i).CardName + "\n"
    }
    for (j <- 0 until Cards.playingDeck.length) {
      z += "                         " + Console.BLUE + Cards.playingDeck(j).head.CardName + Console.CYAN + " {" + Cards.playingDeck(j).length + "} " + Console.MAGENTA + "[" + Cards.playingDeck(j).head
        .CostValue + "]" + Console.BLUE + " Card Effect: " + Cards.playingDeck(j).head.EffectValue + Console.BLACK + " (" + j + ")"
    }
    stringValue match {
      case 0 => "     Press any button to start your turn"
      case 1 => s + "     You dont have any Actioncards to play"
      case 2 => ""
      case 3 =>
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
      case 17 => Console.BLUE + "     You choose: " + Console.BLACK + l(playerturn).hand(inputInt).CardName
      case 18 => Console.BLUE + "     Choose a Card you want to add to your hand"
      case 19 => Console.BLUE + "     You can choose a card that cost up to " + discardCardValue + " Money"
      case 20 => Console.BLUE + "     You can pick one of these: " + Console.CYAN + "{Quantity}" + Console.MAGENTA + " [Cost]" + Console.BLACK + " (PRESS)"
      case 21 => z
      case 22 => Console.YELLOW + "\n \n     Which card to you want to add to your hand?\n"
      case 23 => Console.RED + "     You cant add that, please enter a valid number"


    }
  }
}
