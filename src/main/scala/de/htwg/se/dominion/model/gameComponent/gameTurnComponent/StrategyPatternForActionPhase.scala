package de.htwg.se.dominion.model.gameComponent.gameTurnComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.{Card, goldHeadDeck, silverHeadDeck}
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player
import de.htwg.se.dominion.model.playerComponent.{PlayerInterface, StaticPlayerInterface}
import de.htwg.se.dominion.model.gameComponent.GameTurnInterface
import de.htwg.se.dominion.model.gameComponent.GameInitInterface
import de.htwg.se.dominion.model.gameComponent.GameEndInterface
import de.htwg.se.dominion.model.gameComponent.{GameTurnInterface,StaticGameTurnInterface}

case class StrategyPatternForActionPhase(playerInterface: PlayerInterface,staticPlayerInterface: StaticPlayerInterface,gameTurnInterface: GameTurnInterface,staticGameTurnInterface: StaticGameTurnInterface) {

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
        l = staticPlayerInterface.updatePlayer(l, playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 8, l(idx).getMoney))
      } else {
        l = staticPlayerInterface.updatePlayer(l, playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 41, l(idx).getMoney))
      }
    } else {
      l = staticPlayerInterface.updatePlayer(l, playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 13, l(idx).getMoney))
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
            l = staticPlayerInterface.updatePlayer(l,  playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 9, l(idx).getMoney))
          }
        }
        l = staticPlayerInterface.updatePlayer(l, staticPlayerInterface.draw(l(idx), draw))
        l = staticPlayerInterface.updatePlayer(l,  playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 39, l(idx).getMoney))
      } else {
        l = staticPlayerInterface.updatePlayer(l,  playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 10, l(idx).getMoney))
      }
    } else {
      l = staticPlayerInterface.updatePlayer(l,  playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 11, l(idx).getMoney))
    }
    l
  }

  def mine(list: List[PlayerInterface], idx: Int, input: Int): List[PlayerInterface] = {
    var l = list
      if (input < l(idx).getHand.length) {
        if (l(idx).getHand(input).Type == "Money") {
          if (input < l(idx).getHand.length) {
            if (l(idx).getHand(input).CardName == "Copper") {
              l = staticPlayerInterface.updatePlayer(l, staticPlayerInterface.upgrading(l(idx), input, silverHeadDeck.silverDeck))
              //staticGameTurnInterface.getPlayingDecks = gameTurnInterface.updateDeck(staticGameTurnInterface.getPlayingDecks, gameTurnInterface.copyList(staticGameTurnInterface.getPlayingDecks(1)), 1)
              l = staticPlayerInterface.updatePlayer(l,  playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 35, l(idx).getMoney))
            } else if (l(idx).getHand(input).CardName == "Silver" || l(idx).getHand(input).CardName == "Gold") {
              l = staticPlayerInterface.updatePlayer(l, staticPlayerInterface.upgrading(l(idx), input, goldHeadDeck.goldDeck))
              //staticGameTurnInterface.getPlayingDecks = gameTurnInterface.updateDeck(staticGameTurnInterface.getPlayingDecks, gameTurnInterface.copyList(GameTurn().playingDecks(2)), 2)
              l = staticPlayerInterface.updatePlayer(l,  playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 36, l(idx).getMoney))
            }
          } else {
            l = staticPlayerInterface.updatePlayer(l, playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 15, l(idx).getMoney))
          }
        } else {
          l = staticPlayerInterface.updatePlayer(l, playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 37, l(idx).getMoney))
        }
      } else {
        l = staticPlayerInterface.updatePlayer(l, playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 37, l(idx).getMoney))
      }
    l
  }

  def remodel(list: List[PlayerInterface], idx: Int, input: Int): List[PlayerInterface] = {
    var l = list
    discardCardValue = 0
    if (input >= 0 && input < l(idx).getHand.length) {
      discardCardValue = l(idx).getHand(input).CostValue
      discardCardValue += 2
      l = staticPlayerInterface.updatePlayer(l, gameTurnInterface.removeHandcard(input, l(idx)))
      l = staticPlayerInterface.updatePlayer(l, playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 18, l(idx).getMoney))
    } else {
      l = staticPlayerInterface.updatePlayer(l, playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 19, l(idx).getMoney))
    }
    l
  }

  def remodel2(list: List[PlayerInterface], idx: Int, input: Int): List[PlayerInterface] = {
    var l = list
    val cards = gameTurnInterface.getCardsWC()
    if (cards.contains(input)) {
      l = staticPlayerInterface.updatePlayer(l, gameTurnInterface.addCardToHand(l(idx), input))
      //staticGameTurnInterface.getPlayingDecks = gameTurnInterface.updateDeck(staticGameTurnInterface.getPlayingDecks, gameTurnInterface.copyList(staticGameTurnInterface.getPlayingDecks(input)), input)
      l = staticPlayerInterface.updatePlayer(l, playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 40, l(idx).getMoney))
    } else {
      l = staticPlayerInterface.updatePlayer(l, playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 23, l(idx).getMoney))
    }
    l
  }

  def workshop(list: List[PlayerInterface], idx: Int, input: Int): List[PlayerInterface] = {
    var l = list
    val cards = gameTurnInterface.getCardsWCost4()
    if (cards.contains(input)) {
      l = staticPlayerInterface.updatePlayer(l, gameTurnInterface.updateStacker(l(idx), staticGameTurnInterface.getPlayingDecks(input).head))
      //staticGameTurnInterface.getPlayingDecks = gameTurnInterface.updateDeck(staticGameTurnInterface.getPlayingDecks, gameTurnInterface.copyList(GameTurn().playingDecks(input)), input)
      l = staticPlayerInterface.updatePlayer(l,  playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 40, l(idx).getMoney))
    } else {
      l = staticPlayerInterface.updatePlayer(l,  playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 21, l(idx).getMoney))
    }
    l
  }

  def merchant(list: List[PlayerInterface], idx: Int): List[PlayerInterface] = {
    var l = list
    for (i <- 0 until l(idx).getHand.length) {
      if (l(idx).getHand(i).CardName.equals("Silver")) {
        l = staticPlayerInterface.updatePlayer(l,  playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand,
          l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 20, (l(idx).getMoney + 2)))
        return l
      } else {
        l = staticPlayerInterface.updatePlayer(l, playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, l(idx).getHand, l(idx).getPlayingCards, l(idx).getActions, l(idx).getBuys, 22, l(idx).getMoney))
      }
    }
    l
  }
}