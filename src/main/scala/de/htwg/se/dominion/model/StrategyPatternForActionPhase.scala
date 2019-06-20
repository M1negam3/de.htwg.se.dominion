package de.htwg.se.dominion.model

object StrategyPatternForActionPhase {

  var strategy = GameTurn.playingCards.head.CardName match {
    case "Cellar" =>
    case "Mine" =>
    case "Remodel" =>
    case "Merchant" =>
    case "Workshop" =>
    case _ =>
  }

  def cellarStrategy(): List[Player] = {

  }

}
