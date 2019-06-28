package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player

trait GameEndInterface {

  def end(list: List[Player]): List[Player]

  def score(list: List[Player]): List[(Int, String)]

}
