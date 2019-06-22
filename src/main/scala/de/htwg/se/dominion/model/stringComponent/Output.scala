package de.htwg.se.dominion.model.stringComponent

import de.htwg.se.dominion.controller.maincontroller.RoundManager
import de.htwg.se.dominion.model.playerComponent._

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
    for (i <- 0 until l(playerturn).hand.length) {
      s += Console.BLUE + "          " + l(playerturn).hand(i).CardName + Console.BLACK + " (" + i + ")\n"
    }
    var s2: String = Console.BLUE + "     Your Actions are: " + l(playerturn).actions + "\n"

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
    }
  }
}
