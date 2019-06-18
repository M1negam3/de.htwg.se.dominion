package de.htwg.se.dominion.model

object Output {

  def printHeader(): String = {
    Console.WHITE +
    """
    ╔═══════════════════════════════════════════ Dominion ════════════════════════════════════════════════╗

                                        Press n to START a NEW Game!
                                        Press h to get the Rules!
                                        Press q to QUIT the Game!

    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝
    """.stripMargin
  }

  def printPrep(): String = {
    Console.WHITE +
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

  def printRules(): String = {
    Console.WHITE +
    """
      |DAS SIND DIE REGELN
    """.stripMargin
  }

  def printActionPhase(): String = {
    Console.WHITE +
    """
    ════════════════════════════════════════════ Action Phase ═════════════════════════════════════════════
    """.stripMargin
  }

  def printBuyPhase(): String = {
    Console.WHITE +
    """
    ═══════════════════════════════════════════ Buy Phase ═════════════════════════════════════════════════
    """.stripMargin
  }

  def printTurn(idx: Int): String = {
    Console.WHITE + "Player " + (idx + 1) + "`s turn!\n"
   }

  def prtintNextTurn(): String = {
    """
      |Press t to START the next Turn!
      |Press q to QUIT the Game!
    """.stripMargin
  }

  def printTurnEnd(idx: Int): String = {
    Console.WHITE + "Player " + (idx + 1) + "`s turn ends!\n"
  }
}
