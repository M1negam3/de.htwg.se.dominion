package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.deckComponent.{Cards, goldDeck, silverDeck}
import de.htwg.se.dominion.model.playerComponent.Player
import de.htwg.se.dominion.model.stringComponent.Output

object StrategyPatternForActionPhase {

  var discardAmount = 0
  var discardCardValue = 0

  def getCardname(list:List[Player], playerTurn: Int, input: Int): List[Player] = {
    val l = list
    l(playerTurn).playingCards.head.CardName match {
      case "Cellar" => cellar(l, playerTurn, input)
      case "Mine" => mine(l, playerTurn, input)
      case "Remodel" => remodel(l, playerTurn, input)
      case "Workshop" => workshop(l, playerTurn, input)
      case "Merchant" => merchant(l , playerTurn)
      case _ => l
    }
  }

  def getCardName2(list: List[Player], playerTurn: Int, input: String): List [Player] = {
    val l = list
    l(playerTurn).playingCards.head.CardName match {
      case "Cellar" => cellar2(l, playerTurn, input)
      case "Remodel" => remodel2(l, playerTurn, input.toInt)
    }
  }

  def cellar(list: List[Player], idx: Int, input: Int): List[Player] = {
    var l = list
    if (input <= l(idx).hand.length) {
      if (input > 0) {
        discardAmount = input
        l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 8, l(idx).money))
      } else {
        l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 41, l(idx).money))
      }
    } else {
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 13, l(idx).money))
    }
    l
  }

  def cellar2(list: List[Player], idx: Int, strInput: String): List[Player] = {
    var l = list
    var test = strInput.split(" ")
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
          if (test(i).toInt < l(idx).hand.length) {
            l = Player.updatePlayer(l, GameTurn.updateStacker(l(idx), l(idx).hand(test(i).toInt)))
            l = Player.updatePlayer(l, GameTurn.removeHandcard(test(i).toInt, l(idx)))
            draw += 1
          } else {
            l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 9, l(idx).money))
          }
        }
        l = Player.updatePlayer(l, Player.draw(l(idx), draw))
        l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 39, l(idx).money))
      } else {
        l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 10, l(idx).money))
      }
    } else {
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 11, l(idx).money))
    }
    l
  }

  def mine(list: List[Player], idx: Int, input: Int): List[Player] = {
    var l = list
      if (input < l(idx).hand.length) {
        if (l(idx).hand(input).Type == "Money") {
          if (input < l(idx).hand.length) {
            if (l(idx).hand(input).CardName == "Copper") {
              l = Player.updatePlayer(l, Player.upgrading(l(idx), input, silverDeck.silverDeck))
              GameTurn.playingDecks = GameTurn.updateDeck(GameTurn.playingDecks, GameTurn.copyList(GameTurn.playingDecks(1)), 1)
              l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 35, l(idx).money))
            } else if (l(idx).hand(input).CardName == "Silver" || l(idx).hand(input).CardName == "Gold") {
              l = Player.updatePlayer(l, Player.upgrading(l(idx), input, goldDeck.goldDeck))
              GameTurn.playingDecks = GameTurn.updateDeck(GameTurn.playingDecks, GameTurn.copyList(GameTurn.playingDecks(2)), 2)
              l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 36, l(idx).money))
            }
          } else {
            l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 15, l(idx).money))
          }
        } else {
          l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 37, l(idx).money))
        }
      } else {
        l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 37, l(idx).money))
      }
    l
  }

  def remodel(list: List[Player], idx: Int, input: Int): List[Player] = {
    var l = list
    discardCardValue = 0
    if (input >= 0 && input < l(idx).hand.length) {
      discardCardValue = l(idx).hand(input).CostValue
      discardCardValue += 2
      l = Player.updatePlayer(l, GameTurn.removeHandcard(input, l(idx)))
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 18, l(idx).money))
    } else {
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 19, l(idx).money))
    }
    l
  }

  def remodel2(list: List[Player], idx: Int, input: Int): List[Player] = {
    var l = list
    val cards = GameTurn.getCardsWC()
    if (cards.contains(input)) {
      l = Player.updatePlayer(l, GameTurn.addCardToHand(l(idx), input))
      GameTurn.playingDecks = GameTurn.updateDeck(GameTurn.playingDecks, GameTurn.copyList(GameTurn.playingDecks(input)), input)
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 40, l(idx).money))
    } else {
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 23, l(idx).money))
    }
    l
  }

  def workshop(list: List[Player], idx: Int, input: Int): List[Player] = {
    var l = list
    val cards = GameTurn.getCardsWCost4()
    if (cards.contains(input)) {
      l = Player.updatePlayer(l, GameTurn.updateStacker(l(idx), GameTurn.playingDecks(input).head))
      GameTurn.playingDecks = GameTurn.updateDeck(GameTurn.playingDecks, GameTurn.copyList(GameTurn.playingDecks(input)), input)
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 40, l(idx).money))
    } else {
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 21, l(idx).money))
    }
    l
  }

  def merchant(list: List[Player], idx: Int): List[Player] = {
    var l = list
    for (i <- 0 until l(idx).hand.length) {
      if (l(idx).hand(i).CardName.equals("Silver")) {
        l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 20, (l(idx).money + 2)))
        this
      } else {
        l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 22, l(idx).money))
      }
    }
    l
  }

}
