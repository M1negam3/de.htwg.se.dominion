package de.htwg.se.dominion.model.stringComponent

import de.htwg.se.dominion.controller.maincontroller.RoundManager
import de.htwg.se.dominion.model.playerComponent.Player
import org.scalatest._

class OutputSpec extends WordSpec with Matchers {
  var map: Map[Int, String]=Map(2 -> "Luca")
  var players: List[Player] = Nil
  var numberOfRounds: Int = 0
  var numberOfPlayers: Int = 0
  var names: List[String] = Nil
  var score: Map[Int, String] = Map()
  var idx: Int = 0
  //var r = RoundManager(players,numberOfRounds,numberOfPlayers,names,score,idx)
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
    "have a printBuyPhase" in {
      Output.printBuyPhase() should be(Console.BLACK +
        """
    ═══════════════════════════════════════════ Buy Phase ═════════════════════════════════════════════════
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
      Console.BLACK +
        """
    ╔═══════════════════════════════════════════ Game End ════════════════════════════════════════════════╗

                                          Press q to QUIT the Game!

    ╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝
    """.stripMargin
    }
    /*"have a printScore" in {
      Output.printScore(map) should not be ("")
    }*/
    "have a printPlayerQuestion" in {
      Output.printPlayerQuestion() should be (Console.BLUE + "\n     How many Player´s are you?(between 2 and 5)\n")
    }
  }

}
