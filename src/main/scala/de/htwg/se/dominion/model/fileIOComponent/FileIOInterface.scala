package de.htwg.se.dominion.model.fileIOComponent

import de.htwg.se.dominion.controller.maincontroller.{Controller, ControllerState, RoundManager}
import de.htwg.se.dominion.model.ModelInterface

trait FileIOInterface {

  def load(modelInterface: ModelInterface): (String, RoundManager)
  def save(controllerState: String ,modelInterface: ModelInterface): Unit

}