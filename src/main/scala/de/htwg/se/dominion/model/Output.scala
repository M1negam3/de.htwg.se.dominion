package de.htwg.se.dominion.model

import de.htwg.se.dominion.controller.maincontroller.RoundManager

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

  def printNextTurn(): String = {
    Console.BLACK +
      """
      |     Press t to START the next Turn!
      |     Press q to QUIT the Game!
    """.stripMargin
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

  def printScore(m: Map[String, Int]): String = {
    var s = "    ╔═══════════════════════════════════════════ Score ═══════════════════════════════════════════════════╗\n \n"
    for ((k, v) <- m) {

      s += "                               " + k + ": " + v + "\n"
    }
    s += "\n    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝\n \n" +
      "     Press n to StART a new Game!\n" + "     Press q to QUIT the Game!\n"
    s
  }
  def printTest(): String = {
    var test = "How many Player´s are you(Min 2 and Max. 5"
    test
  }

  def printPlayers(r: RoundManager): String = {
    var s = ""
    for (i <- 0 until r.numberOfPlayers) {
      s += "     Player " + (i + 1) + " was created!\n"
    }
    s
  }
}
