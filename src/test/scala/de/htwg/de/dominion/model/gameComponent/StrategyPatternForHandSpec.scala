package de.htwg.de.dominion.model.gameComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.Cards
import de.htwg.se.dominion.model.gameComponent.{GameTurn, StrategyPatternForHand}
import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.dominion.model.gameComponent.StrategyPatternForHand._
import de.htwg.se.dominion.model.playerComponent.Player

import scala.collection.mutable.ListBuffer

class StrategyPatternForHandSpec extends  WordSpec with Matchers{
  var l = new ListBuffer[Cards]

  "A stragey " should {
    "have a strategy " ignore {
      var strategy: ListBuffer[Cards] = Player.deckLength match {
        case 0 => StrategyPatternForHand.strategy0(Player.copiedPlayer, Player.copyList)
        case 1 => StrategyPatternForHand.strategy1(Player.copiedPlayer, Player.copyList)
        case 2 => StrategyPatternForHand.strategy2(Player.copiedPlayer, Player.copyList)
        case 3 => StrategyPatternForHand.strategy3(Player.copiedPlayer, Player.copyList)
        case 4 => StrategyPatternForHand.strategy4(Player.copiedPlayer, Player.copyList)
        case _ => StrategyPatternForHand.strategy_(Player.copiedPlayer, Player.copyList)

      }
    }

  }
}
