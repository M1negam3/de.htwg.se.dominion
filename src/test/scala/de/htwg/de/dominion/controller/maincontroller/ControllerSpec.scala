package de.htwg.de.dominion.controller.maincontroller

import de.htwg.se.dominion.controller.maincontroller.{Controller, EndState, NameSetupState, PlayerCountState, RoundManager, playingState}
import de.htwg.se.dominion.model.gameComponent.GameEnd
import org.scalatest._

class ControllerSpec extends WordSpec with Matchers {
 val roundManager = RoundManager()
  val controller = new Controller(roundManager)


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
  "A playingState" when {
    val state = controller.controllerState
    "does nothing when theres no input" in {
      val oldRM = controller.roundManager
      state.evaluate("")
      controller.roundManager should be(oldRM)
    }
    "when in actionphase" in {

    }
  }
}
