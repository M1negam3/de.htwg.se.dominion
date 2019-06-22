package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.playerComponent.Player

object GameTurnRe {




  def actionPhase(list: List[Player], idx: Int): List[Player] = {
    var l = list
    var index = idx
    for (f <- 0 until 5) {
      if (l(index).hand(f).Type.equals("Action") && !check) {
        l(index).actions = 1
      }
    }





















  }


}
