package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.model.gameComponent.GameInitRe
import de.htwg.se.dominion.model.playerComponent.Player

case class RoundManagerRe(players: List[Player] = List(),
                          names: List[String] = List(),
                          numberOfPlayer: Int = 0,
                          playerturn: Int = 0,
                          score: Map[Int, String] = Map()) {

  def getNames(r: RoundManagerRe, name: String): RoundManagerRe = {
    val copiedRoundManagerRe = r
    val names = GameInitRe.getPlayerName(copiedRoundManagerRe.names, name)
    RoundManagerRe(copiedRoundManagerRe.players, names, copiedRoundManagerRe.numberOfPlayer, copiedRoundManagerRe.playerturn, copiedRoundManagerRe.score)
  }

  def getNameSetupStrings(): String = {
    "Player " + (playerturn + 1) + ", please enter your name:"
  }

  def nextPlayer(): Int = {
    if (playerturn < numberOfPlayer - 1) {
      playerturn + 1
    } else {
      0
    }
  }

  def createPlayer(r: RoundManagerRe): List[Player] = {
    val copiedRoundManagerRe = r
    val p = Player.createPlayer(copiedRoundManagerRe.numberOfPlayer, copiedRoundManagerRe.names)
    p
  }


}
