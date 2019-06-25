package de.htwg.se.dominion.model.stringComponent

import de.htwg.se.dominion.controller.maincontroller.RoundManager
import de.htwg.se.dominion.model.deckComponent.cardComponent.Cards
import de.htwg.se.dominion.model.playerComponent.Player
import org.scalatest._

import scala.collection.immutable.List

class OutputSpec extends WordSpec with Matchers {
  var map: Map[Int, String]=Map(2 -> "Luca")
  var players: List[Player] = Nil
  var numberOfRounds: Int = 0
  var numberOfPlayers: Int = 0
  var names: List[String] = Nil
  var score: Map[Int, String] = Map()
  var idx: Int = 0
  val hand: List[Cards] = List(Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper)
  var Luca = new Player("Luca",0,Cards.startDeck,Cards.stacker,hand,hand)
  var Luis = new Player("Luis",0,Cards.startDeck,Cards.stacker,hand)
  var list: List[Player] = List(Luca,Luis)
  val y = list(0).hand.length - 1
  var s = ""
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
    "have a getPlayingStateString" in {
      Output.getPlayingStateString(list,0,0) should be ("")
      //Output.getPlayingStateString(list,0,1) should not be ("")
      //Output.getPlayingStateString(list,0,3) should not be ("")
      Output.getPlayingStateString(list,0,4) should be (Console.BLACK + "     Enter a number to choose a card, which you want to play")
      //Output.getPlayingStateString(list,0,5) should be ("")
      //Output.getPlayingStateString(list,0,6) should be ("")
      //Output.getPlayingStateString(list,0,7) should be ("")
      Output.getPlayingStateString(list,0,8) should be (Console.BLUE + "     Choose some Card(s), separate them with a blank")
      Output.getPlayingStateString(list,0,9) should be (Console.RED + "     Please enter a Card from your hand between 0 and " + y)
      Output.getPlayingStateString(list,0,10) should be (Console.RED + "     Please enter the correct amount of Cards to discard")
      Output.getPlayingStateString(list,0,11) should be (Console.RED + "     Dont enter the same number twice")
      Output.getPlayingStateString(list,0,12) should be (Console.RED + "     Please enter a correct number!")
      Output.getPlayingStateString(list,0,13) should be (Console.RED + "     Needs to be smaller or equal to your Hand Card length!")
     // Output.getPlayingStateString(list,0,13) should be ("")
      //Output.getPlayingStateString(list,0,14) should be ("")
      Output.getPlayingStateString(list,0,15) should be (Console.RED + "     Choose a valid Card from your hand")
      //Output.getPlayingStateString(list,0,16) should be ("")
      //Output.getPlayingStateString(list,0,17) should be (Console.BLUE + "     You choose: " + Console.BLACK + list(0).hand(2).CardName)
      //Output.getPlayingStateString(list,0,18) should be ("")
      Output.getPlayingStateString(list,0,19) should be (Console.RED + "     Invalid Input, try again")
      Output.getPlayingStateString(list,0,20) should be (Console.BLUE + "     You have a Silver on your Hand, +1 Gold")
      Output.getPlayingStateString(list,0,21) should be (Console.RED + "     Please choose a Card which is listed above!\n")
      Output.getPlayingStateString(list,0,22) should be (Console.RED + "     You dont have a Silver on your Hand, Gold stays the same")
      Output.getPlayingStateString(list,0,23) should be (Console.RED + "     You cant add that, please enter a valid number")
      Output.getPlayingStateString(list,0,24) should be (Console.RED + "     Try Y or N!")
      //Output.getPlayingStateString(list,0,25) should be ("")
      Output.getPlayingStateString(list,0,26) should be (Console.BLUE + "     Your Buy actions are: " + list(0).buys)
      Output.getPlayingStateString(list,0,27) should be (Console.BLUE + "     You can buy these: " + Console.CYAN + "{Quantity}" + Console.MAGENTA + "[Cost]" + Console.BLACK + "(PRESS)\n")
      //Output.getPlayingStateString(list,0,28) should be ("")
      Output.getPlayingStateString(list,0,29) should be (Console.YELLOW + "\n     Do you want to buy a Card? (Y/N)\n")
      Output.getPlayingStateString(list,0,30) should be (Console.YELLOW + "     Which Card do you want to buy?\n")
      Output.getPlayingStateString(list,0,31) should be (Console.BLUE + "\n     The Card  was bought and added to your stacker\n \n")
      Output.getPlayingStateString(list,0,32) should be (Console.RED + "     You cant buy that, please enter a valid number")
      //Output.getPlayingStateString(list,0,33) should be ("")
      Output.getPlayingStateString(list,0,34) should be (Console.BLUE + "\n     You don´t have any buys")
      Output.getPlayingStateString(list,0,35) should be (Console.BLUE + "     A Silver Card was added to your Stacker")
      Output.getPlayingStateString(list,0,36) should be (Console.BLUE + "     A Gold Card was added to your Stacker")
      Output.getPlayingStateString(list,0,37) should be (Console.RED + "      Please choose a Money Card!")
      //Output.getPlayingStateString(list,0,38) should be (Console.BLUE + "     Your card effect is: " + Console.BLACK + list(0).playingCards.head.EffectValue + "\n\n" +
      //  s + "\n")
      Output.getPlayingStateString(list,0,39) should be (Console.BLUE + "     Press a button to continue")
      Output.getPlayingStateString(list,0,40) should be (Console.BLUE + "     Card gained")
      Output.getPlayingStateString(list,0,41) should be (Console.RED + "      Needs to be larger than 0!")
      Output.getPlayingStateString(list,0,42) should be (Console.RED + "\n     You don´t have any actions left and/or you dont have any Actioncards to play\n")
      Output.getPlayingStateString(list,0,43) should be (Console.RED + "\n     Please choose a Action card from your hand")
      Output.getPlayingStateString(list,0,44) should be (Console.BLUE + "\n     You successfully played a Card")
      Output.getPlayingStateString(list,0,45) should be (Console.RED + "      Enter numbers HUAN!\n")
      Output.getPlayingStateString(list,0,46) should be (Console.RED + "      Enter numbers HUAN!\n")
      Output.getPlayingStateString(list,0,47) should be (Console.RED + "      Enter numbers HUAN!\n")
      Output.getPlayingStateString(list,0,48) should be (Console.RED + "      Enter numbers HUAN!\n")
      //Output.getPlayingStateString(list,0,49) should be ("")
      //Output.getPlayingStateString(list,0,_) should be ("MÖP")


    }
  }

}
