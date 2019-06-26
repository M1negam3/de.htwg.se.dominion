package de.htwg.de.dominion.aview.gui

import de.htwg.se.dominion.aview.gui._
import de.htwg.se.dominion.controller.maincontroller.{Controller, EndState, NameSetupState, PlayerCountState, RoundManager, playingState}
import de.htwg.se.dominion.model.playerComponent.Player
import org.scalatest._

class SwingGuiSpec extends WordSpec with Matchers {

  val controller = new Controller(new RoundManager())

  "A SwingGuiSpec" should {
    "load the correct Panel" when {
      "Controller is in preSetupState" in {
        controller.controllerState = PlayerCountState(controller)
        SwingGui.getPanel(controller).isInstanceOf[WelcomePanel] should be(true)
      }

      "Controller is in SetupState" in {
        controller.controllerState = NameSetupState(controller)
        SwingGui.getPanel(controller).isInstanceOf[NameInitPanel] should be(true)
      }

      "Controller is in InGameState" in {
        controller.controllerState = playingState(controller)
        SwingGui.getPanel(controller).isInstanceOf[PlayingPanel] should be(true)
      }

      "Controller is in GameOverState" in {
        controller.controllerState = EndState(controller)
        SwingGui.getPanel(controller).isInstanceOf[GameEndPanel] should be(true)
      }
    }
  }
}
