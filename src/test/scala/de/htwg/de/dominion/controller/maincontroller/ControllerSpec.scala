package de.htwg.de.dominion.controller.maincontroller

import de.htwg.se.dominion.controller.maincontroller.{Controller, EndState, NameSetupState, PlayerCountState, RoundManager, playingState}
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.gameComponent.gameTurnComponent.GameTurn
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player
import org.scalatest._

class ControllerSpec extends WordSpec with Matchers {
  var names: List[String] = List("Luca","Luis")
  var names1: List[String] = List("Luca1","Luis")

  var hand: List[Cards] = List(Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.village)
  var hand1: List[Cards] = List(Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper)
  var Luca = Player("Luca",0,Cards.startDeck,Cards.stacker,Cards.hand,Nil,1,1,0,0)
  var Luca1 = Player("Luca",0,Cards.startDeck,Cards.stacker,hand,Nil,1,1,0,0)
  var Luca2 = Player("Luca",0,Cards.startDeck,Cards.stacker,hand,Nil,1,1,0,0)
  var Luis = Player("Luis",0,Cards.startDeck,Cards.stacker,Cards.hand,Nil,1,1,0,0)
  var players: List[Player] = List(Luca,Luis)
  var players1: List[Player] = List(Luca1,Luis)
  var players2: List[Player] = List(Luca2,Luis)
  val roundManager = RoundManager(players,names,2,0,Nil,GameTurn.playingDecks,false)
  val roundManager1 = RoundManager(players1,names1,2,0,Nil,GameTurn.playingDecks,false,0,true)
  val roundManager2 = RoundManager(players1,names1,2,0,Nil,GameTurn.playingDecks,true,0,false)
  val controller = new Controller(roundManager)
  val controller1 = new Controller(roundManager1)
  val controller2 = new Controller(roundManager2)
  val defscore: List[(Int, String)] = List()


  "A Controller" should{

  }
  "switches to the next state correctly" in {
    controller.controllerState = playingState(controller)
    controller.nextState()
    controller.controllerState should be(EndState(controller))
  }
  "returns the current controller state as string representation" in {
    controller.controllerState = playingState(controller)
    controller.controllerStateAsString should be("PlayingState")
    controller.controllerState = PlayerCountState(controller)
    controller.controllerStateAsString should be("PlayerCountState")
    controller.controllerState = NameSetupState(controller)
    controller.controllerStateAsString should be("NameSetupState")
    controller.controllerState = EndState(controller)
    controller.controllerStateAsString should be("EndState")
  }
  "have a numberofPlayer" in {
    controller.roundManager = controller.roundManager.copy(numberOfPlayer = 2)
    controller.roundManager.numberOfPlayer should be (2)
  }
  "have a getCurrentPhaseAsString method" in {
    controller.getCurrentPhaseAsString(false) should be ("Buy Phase")
  }
  "A playingState" should {
    "does nothing when theres no input" in {
      controller.controllerState = playingState(controller)
      val oldRM = controller.roundManager
      controller.controllerState.evaluate("")
      controller.roundManager should be(oldRM)
    }
    "when in actionphase" in {
      controller2.controllerState = playingState(controller2)
      controller2.controllerState.evaluate("k")
      controller2.roundManager.players(controller2.roundManager.playerturn).stringValue should be (0)
      controller2.controllerState = playingState(controller2)
      controller2.controllerState.evaluate("k")
      controller2.roundManager.players(controller2.roundManager.playerturn).stringValue should be (0)

    }


    "when in buyphase" in {
      controller.controllerState = playingState(controller)
      controller.roundManager = controller.roundManager.copy(players = players1)
      controller.controllerState.evaluate("k")
      controller.roundManager.players(controller.roundManager.playerturn).stringValue should be (25)
      controller.controllerState.evaluate("Y")
      controller.roundManager.players(controller.roundManager.playerturn).stringValue should be (30)
      controller.controllerState.evaluate("asd")
      controller.roundManager.players(controller.roundManager.playerturn).stringValue should be (48)
      controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 25),
        playingDecks = GameTurn.playingDecks)
      controller.controllerState.evaluate("N")
      controller.roundManager.action should be (true)
      controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 24),
        playingDecks = GameTurn.playingDecks)
      controller.controllerState.evaluate("")
      controller.roundManager.players(controller.roundManager.playerturn).stringValue should be (24)
      /*controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 48),
        playingDecks = GameTurn.playingDecks)
      controller.controllerState.evaluate("0")
      controller.roundManager.players(controller.roundManager.playerturn).stringValue should be (31)*/
      controller.roundManager = controller.roundManager.copy(players = players1)
      controller.roundManager = controller.roundManager.copy(players = controller.roundManager.editStringValue(controller.roundManager, 30),
        playingDecks = GameTurn.playingDecks)
      controller.controllerState.evaluate("30")
      controller.roundManager.players(controller.roundManager.playerturn).stringValue should be (30)
      /*controller.roundManager = controller.roundManager.copy(players = players2)
      controller.controllerState.evaluate("k")
      controller.roundManager.players(controller.roundManager.playerturn).stringValue should be (34)*/

      /*controller1.controllerState = playingState(controller1)
      controller1.controllerState.evaluate("k")
      controller1.controllerState.evaluate("Y")
      controller1.controllerState.evaluate("0")
      controller1.roundManager.players(controller.roundManager.playerturn).stringValue should be (31)*/
      controller1.controllerState = playingState(controller1)
      controller1.gameStatus should not be ("IDLE")
      controller1.roundManager.players(controller1.roundManager.playerturn).stringValue should be (0)
      controller1.roundManager.score should  be (defscore)
    }
  }
}
