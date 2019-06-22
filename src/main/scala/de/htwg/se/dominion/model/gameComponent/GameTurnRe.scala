package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.playerComponent.Player
import scala.util.control.Breaks.{break, breakable}

object GameTurnRe {

  var l: List[Player] = Nil

  def actionPhase(list: List[Player], index: Int): List[Player] = {
    l = list
    var actionumber = l(index).actions
    var playingCards = l(index).playingCards
    var money = 0
    var z = 0

    l = Player.updatePlayer(l, Player.getHand(l(index)))

    for (f <- 0 until 5) {
      if (l(index).hand(f).Type.equals("Action")) {
        actionumber = 1
      }
      if (actionumber == 0) {
        actionumber = 0
        l = Player.updatePlayer(l, Player(l(index).name, l(index).value, l(index).deck, l(index).stacker, l(index).hand, l(index).playingCards, 0, 1, 1))
      }
    }

    if (actionumber > 0) {
      breakable {
        for (i <- 0 until l(index).hand.length) {
          if (l(index).hand(i).Type == "Money") {
            z += 1
          }
        }
        if (z.equals(l(index).hand.length)) {
          l = Player.updatePlayer(l, Player(l(index).name, l(index).value, l(index).deck, l(index).stacker, l(index).hand, l(index).playingCards, 0, 1, 2))
          break
        }
        z = 0
        l = Player.updatePlayer(l, Player(l(index).name, l(index).value, l(index).deck, l(index).stacker, l(index).hand, l(index).playingCards, 0, 1, 3))
      }

    }

    l
  }

}
