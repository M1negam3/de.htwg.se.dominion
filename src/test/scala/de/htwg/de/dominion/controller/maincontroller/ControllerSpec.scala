package de.htwg.de.dominion.controller.maincontroller

import de.htwg.se.dominion.controller.maincontroller.{Controller, EndState, NameSetupState, PlayerCountState, RoundManager, playingState}
import de.htwg.se.dominion.model.deckComponent.cardComponent.Cards
import de.htwg.se.dominion.model.gameComponent.{GameEnd, GameTurn}
import de.htwg.se.dominion.model.playerComponent.Player
import org.scalatest._

class ControllerSpec extends WordSpec with Matchers {
 val roundManager = RoundManager()
  val controller = new Controller(roundManager)
  var names: List[String] = List("Luca","Luis")
  var names1: List[String] = List("Luca1","Luis")
  var hand: List[Cards] = List(Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.village)
  var hand1: List[Cards] = List(Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper)
  var Luca = new Player("Luca",0,Cards.startDeck,Cards.stacker,Cards.hand,Nil,1,1,0,0)
  var Luca1 = new Player("Luca",0,Cards.startDeck,Cards.stacker,hand,Nil,1,1,0,0)
  var Luca2 = new Player("Luca",0,Cards.startDeck,Cards.stacker,hand,Nil,1,1,0,0)
  var Luis = new Player("Luis",0,Cards.startDeck,Cards.stacker,Cards.hand,Nil,1,1,0,0)
  var players: List[Player] = List(Luca,Luis)
  var players1: List[Player] = List(Luca1,Luis)
  var players2: List[Player] = List(Luca2,Luis)


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
      controller.controllerState = playingState(controller)
      controller.roundManager = controller.roundManager.copy(players = players1)
      controller.controllerState.evaluate("k")
      controller.roundManager.players(controller.roundManager.playerturn).stringValue should be (1)
      controller.roundManager = controller.roundManager.copy(players = players2)
      controller.controllerState.evaluate("k")
      controller.roundManager.players(controller.roundManager.playerturn).stringValue should be (3)

    }
  }
}
