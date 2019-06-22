package de.htwg.se.dominion.model.stringComponent

import org.scalatest._

class OutputSpec extends WordSpec with Matchers {
  var map: Map[Int, String]=Map(2 -> "Luca")
  "A Output" should {
    "have a method printPrep" in {
      Output.printPrep() should be (Console.BLACK +
        """
    ╔═══════════════════════════════════════════ Dominion ════════════════════════════════════════════════╗
    |
    |                                    Game preparation finished!
    |
    |                                    Press t to START the first Turn!
    |                                    Press q to QUIT the Game!
    |
    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝
    """.stripMargin)
    }
    "have a printActionPhase" in {
      Output.printActionPhase() should be (Console.BLACK +
        """
    ════════════════════════════════════════════ Action Phase ═════════════════════════════════════════════
    """.stripMargin)
    }
    "have a printTurn" in {
      Output.printTurn(1) should be (Console.BLUE + " Player " + 2 + "`s turn!\n")
    }
    "have a printNextTurn" ignore {
      Output.printNextTurn() should be (Console.BLACK +
        """
          |     Press t to START the next Turn!
          |     Press q to QUIT the Game!
        """.stripMargin)
    }
    "have a printTurnEnd" in {
      Output.printTurnEnd(1) should be (Console.BLUE + "     Player " + 2 + "`s turn ends!\n")
    }
    "have a printEnd" in {
      Output.printEnd() should be (Console.BLACK +
        """
    ╔═══════════════════════════════════════════ Game End ════════════════════════════════════════════════╗
      |
      |                         Press e to END the Game and get the Score!
      |                         Press q to QUIT the Game!
      |
    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝
    """.stripMargin)
    }
    "have a printScore" ignore {
      Output.printScore(map) should be (
        "    ╔═══════════════════════════════════════════ Score ═══════════════════════════════════════════════════╗\n \n                 Luca:2 \n\n    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝\n \n\n    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝\n \n")
    }
  }

}
