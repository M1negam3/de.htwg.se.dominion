package de.htwg.se.dominion.controller
import de.htwg.se.dominion.model.{InputOutput, Player, Cards}
import de.htwg.se.dominion.util.Observable

class Controller() extends Observable {
  var pCount = 0
  var players: List[Player] = Nil

  def newGame(): Unit = {
    pCount = InputOutput.getPlayerCount()
    val names = InputOutput.getPlayerName(pCount)
    players = Player.createPlayer(pCount, names)
    players = Player.updatePlayer(players, Player.getHand(players.head))
    print(players.head.hand)
    val players = Player.createPlayer(pCount, names)
    for (i <- 0 until pCount) {
      Player.getMoney(Player.getHand(players(i)))
    }
    var hand : List[Cards] = List(Cards.copper,Cards.mansion,Cards.mansion,Cards.mansion,Cards.mansion)
    val test = new Player("Luca",1,Cards.startDeck,Cards.stacker,hand)
    Player.turn(test)
    notifyObservers
  }
}