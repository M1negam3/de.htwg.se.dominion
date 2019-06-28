package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.playerComponent.PlayerInterface
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.playerInterface

trait GameEndInterface {

  def end(list: List[PlayerInterface]): List[PlayerInterface]

  def score(list: List[PlayerInterface]): List[(Int, String)]

}
