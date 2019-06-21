package de.htwg.se.dominion.model.gameComponent

object StrategyPatternForActionPhase {

  var strategy = GameTurn.playingCards.head.CardName match {
    case "Cellar" => GameTurn.cellar(GameTurn.l, GameTurn.index)
    case "Mine" => GameTurn.mine(GameTurn.l, GameTurn.index)
    case "Remodel" => GameTurn.remodel(GameTurn.l, GameTurn.index)
    case "Workshop" => GameTurn.workshop(GameTurn.l, GameTurn.index)
    case "Merchant" =>
      GameTurn.money = GameTurn.money + GameTurn.merchant(GameTurn.l, GameTurn.index)
      GameTurn.l
    case _ => GameTurn.l
  }

}
