package de.htwg.se.dominion.model.fileIOComponent

import de.htwg.se.dominion.controller.maincontroller.RoundManager

trait FileIOInterface {

  def load: RoundManager
  def save(roundManager: RoundManager): Unit

}