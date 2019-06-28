package de.htwg.se.dominion.model.gameComponent.gameTurnComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.{goldHeadDeck, silverHeadDeck}
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.playerInterface
import de.htwg.se.dominion.model.playerComponent.{PlayerInterface, StaticPlayerInterface}
import de.htwg.se.dominion.model.gameComponent.GameTurnInterface
import de.htwg.se.dominion.model.gameComponent.GameInitInterface
import de.htwg.se.dominion.model.gameComponent.GameEndInterface
import de.htwg.se.dominion.model.gameComponent.GameTurnInterface

case class StrategyPatternForActionPhase(playerInterface: PlayerInterface,staticPlayerInterface: StaticPlayerInterface,gameTurnInterface: GameTurnInterface) {

  var discardAmount = 0
  var discardCardValue = 0

  def getCardname(list:List[PlayerInterface], playerTurn: Int, input: Int): List[PlayerInterface] = {
    val l = list
    l(playerTurn).getPlayingCards.head.CardName match {
      case "Cellar" => cellar(l, playerTurn, input)
      case "Mine" => mine(l, playerTurn, input)
      case "Remodel" => remodel(l, playerTurn, input)
      case "Workshop" => workshop(l, playerTurn, input)
      case "Merchant" => merchant(l , playerTurn)
      case _ => l
    }
  }

  def getCardName2(list: List[PlayerInterface], playerTurn: Int, input: String): List [PlayerInterface] = {
    val l = list
    l(playerTurn).getPlayingCards.head.CardName match {
      case "Cellar" => cellar2(l, playerTurn, input)
      case "Remodel" => remodel2(l, playerTurn, input.toInt)
    }
  }

  def cellar(list: List[PlayerInterface], idx: Int, input: Int): List[PlayerInterface] = {
    var l = list
    if (input <= l(idx).getHand.length) {
      if (input > 0) {
        discardAmount = input
        l = staticPlayerInterface.updatePlayer(l, playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 8, l(idx).getGetMoney))
      } else {
        l = staticPlayerInterface.updatePlayer(l, playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 41, l(idx).getGetMoney))
      }
    } else {
      l = staticPlayerInterface.updatePlayer(l, playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 13, l(idx).getGetMoney))
    }
    l
  }

  def cellar2(list: List[PlayerInterface], idx: Int, strInput: String): List[PlayerInterface] = {
    var l = list
    var test = strInput.split(",")
    var same = false
    var draw = 0
    if (test.length > 1) {
      same = false
      for (i <- 0 until test.length - 1) {
        for (j <- 1 until test.length) {
          if (test(i) == test(j)) {
            same = true
          }
        }
      }
    }
    if (!same) {
      if ( test.length == discardAmount) {
        for (i <- 0 until discardAmount) {
          if (test(i).toInt < l(idx).getHand.length) {
            l = staticPlayerInterface.updatePlayer(l, gameTurnInterface.updateStacker(l(idx), l(idx).getHand(test(i).toInt)))
            l = staticPlayerInterface.updatePlayer(l, gameTurnInterface.removeHandcard(test(i).toInt, l(idx)))
            draw += 1
          } else {
            l = staticPlayerInterface.updatePlayer(l,  playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 9, l(idx).getGetMoney))
          }
        }
        l = staticPlayerInterface.updatePlayer(l, staticPlayerInterface.draw(l(idx), draw))
        l = staticPlayerInterface.updatePlayer(l,  playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 39, l(idx).getGetMoney))
      } else {
        l = staticPlayerInterface.updatePlayer(l,  playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 10, l(idx).getGetMoney))
      }
    } else {
      l = staticPlayerInterface.updatePlayer(l,  playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 11, l(idx).getGetMoney))
    }
    l
  }

  def mine(list: List[playerInterface], idx: Int, input: Int): List[playerInterface] = {
    var l = list
      if (input < l(idx).getHand.length) {
        if (l(idx).getHand(input).Type == "Money") {
          if (input < l(idx).getHand.length) {
            if (l(idx).getHand(input).CardName == "Copper") {
              l = staticPlayerInterface.updatePlayer(l, staticPlayerInterface.upgrading(l(idx), input, silverHeadDeck.silverDeck))
              GameTurn().playingDecks = GameTurn().updateDeck(GameTurn().playingDecks, GameTurn().copyList(GameTurn().playingDecks(1)), 1)
              l = Player().updatePlayer(l, new playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 35, l(idx).getGetMoney))
            } else if (l(idx).getHand(input).CardName == "Silver" || l(idx).getHand(input).CardName == "Gold") {
              l = Player().updatePlayer(l, Player().upgrading(l(idx), input, goldHeadDeck.goldDeck))
              GameTurn().playingDecks = GameTurn().updateDeck(GameTurn().playingDecks, GameTurn().copyList(GameTurn().playingDecks(2)), 2)
              l = Player().updatePlayer(l, new playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 36, l(idx).getGetMoney))
            }
          } else {
            l = Player().updatePlayer(l, new playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 15, l(idx).getGetMoney))
          }
        } else {
          l = Player().updatePlayer(l, new playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 37, l(idx).getGetMoney))
        }
      } else {
        l = Player().updatePlayer(l, new playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 37, l(idx).getGetMoney))
      }
    l
  }

  def remodel(list: List[playerInterface], idx: Int, input: Int): List[playerInterface] = {
    var l = list
    discardCardValue = 0
    if (input >= 0 && input < l(idx).getHand.length) {
      discardCardValue = l(idx).getHand(input).CostValue
      discardCardValue += 2
      l = Player().updatePlayer(l, GameTurn().removeHandcard(input, l(idx)))
      l = Player().updatePlayer(l, new playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 18, l(idx).getGetMoney))
    } else {
      l = Player().updatePlayer(l, new playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 19, l(idx).getGetMoney))
    }
    l
  }

  def remodel2(list: List[playerInterface], idx: Int, input: Int): List[playerInterface] = {
    var l = list
    val cards = GameTurn().getCardsWC()
    if (cards.contains(input)) {
      l = Player().updatePlayer(l, GameTurn().addCardToHand(l(idx), input))
      GameTurn().playingDecks = GameTurn().updateDeck(GameTurn().playingDecks, GameTurn().copyList(GameTurn().playingDecks(input)), input)
      l = Player().updatePlayer(l, new playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 40, l(idx).getGetMoney))
    } else {
      l = Player().updatePlayer(l, new playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 23, l(idx).getGetMoney))
    }
    l
  }

  def workshop(list: List[playerInterface], idx: Int, input: Int): List[playerInterface] = {
    var l = list
    val cards = GameTurn().getCardsWCost4()
    if (cards.contains(input)) {
      l = Player().updatePlayer(l, GameTurn().updateStacker(l(idx), GameTurn().playingDecks(input).head))
      GameTurn().playingDecks = GameTurn().updateDeck(GameTurn().playingDecks, GameTurn().copyList(GameTurn().playingDecks(input)), input)
      l = Player().updatePlayer(l, new playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 40, l(idx).getGetMoney))
    } else {
      l = Player().updatePlayer(l, new playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 21, l(idx).getGetMoney))
    }
    l
  }

  def merchant(list: List[playerInterface], idx: Int): List[playerInterface] = {
    var l = list
    for (i <- 0 until l(idx).getHand.length) {
      if (l(idx).getHand(i).CardName.equals("Silver")) {
        l = Player().updatePlayer(l, new playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand,
          l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 20, (l(idx).getGetMoney + 2)))
        return l
      } else {
        l = Player().updatePlayer(l, new playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 22, l(idx).getGetMoney))
      }
    }
    l
  }
}