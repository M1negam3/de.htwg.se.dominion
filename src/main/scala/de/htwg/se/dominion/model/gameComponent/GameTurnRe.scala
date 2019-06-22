package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.gameComponent.GameTurn.{index, l}
import de.htwg.se.dominion.model.playerComponent.Player

object GameTurnRe {

  var l: List[Player] = Nil

  def actionPhase(list: List[Player], index: Int): List[Player] = {
    l = list
    var actionumber = l(index).actions
    var playingCards = l(index).playingCards
    var money = 0

    l = Player.updatePlayer(l, Player.getHand(l(index)))

    for (f <- 0 until 5) {
      if (l(index).hand(f).Type.equals("Action")) {
        actionumber = 1
      }
    }

  }

}
