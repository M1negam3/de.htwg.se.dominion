package de.htwg.se.dominion.model.gameComponent

trait GameInitInterface {

  def getPlayerName(oldNames: List[String], evalInput: String): List[String]

}
