package de.htwg.se.dominion.model.gameComponent

import de.htwg.se.dominion.model.deckComponent.{Cards, goldDeck, silverDeck}
import de.htwg.se.dominion.model.playerComponent.Player
import de.htwg.se.dominion.model.stringComponent.Output

object StrategyPatternForActionPhase {

  // TODO EFFEKTE UMSCHREIBEN

  var discardAmount = 0
  var discardCardValue = 0

  def getCardname(list:List[Player], playerTurn: Int, input: Int): List[Player] = {
    val l = list
    l(playerTurn).playingCards.head.CardName match {
      case "Cellar" => cellar(l, playerTurn, input)
      case "Mine" => mine(l, playerTurn, input)
      case "Remodel" => remodel(l, playerTurn, input)
      case "Workshop" => workshop(l, playerTurn, input)
      case "Merchant" => merchant(l , playerTurn, input)
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
      discardAmount = input
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 8, l(idx).money))
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
            l = Player.updatePlayer(l, GameTurnRe.updateStacker(l(idx), l(idx).hand(test(i).toInt)))
            l = Player.updatePlayer(l, GameTurnRe.removeHandcard(test(i).toInt, l(idx)))
            draw += 1
          } else {
            l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 9, l(idx).money))
          }
        }
        l = Player.updatePlayer(l, Player.draw(l(idx), draw))
        l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 100, l(idx).money))
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
            GameTurnRe.playingDecks = GameTurnRe.updateDeck(GameTurnRe.playingDecks, GameTurnRe.copyList(GameTurnRe.playingDecks(1)), 1)
            l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 35, l(idx).money))
          } else if (l(idx).hand(input).CardName == "Silver" || l(idx).hand(input).CardName == "Gold") {
            l = Player.updatePlayer(l, Player.upgrading(l(idx), input, goldDeck.goldDeck))
            GameTurnRe.playingDecks = GameTurnRe.updateDeck(GameTurnRe.playingDecks, GameTurnRe.copyList(GameTurnRe.playingDecks(2)), 2)
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
      println(discardCardValue)
      l = Player.updatePlayer(l, GameTurnRe.removeHandcard(input, l(idx)))
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 18, l(idx).money))
    } else {
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 19, l(idx).money))
    }
    l
  }

  def remodel2(list: List[Player], idx: Int, input: Int): List[Player] = {
    var l = list
    if (Output.availableCards.contains(input)) {
      l = Player.updatePlayer(l, GameTurnRe.addCardToHand(l(idx), input))
      GameTurnRe.playingDecks = GameTurnRe.updateDeck(GameTurnRe.playingDecks, GameTurnRe.copyList(GameTurnRe.playingDecks(input)), input)
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 100, l(idx).money))
    } else {
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 23, l(idx).money))
    }
    l
  }

  def workshop(list: List[Player], idx: Int, input: Int): List[Player] = {
    var l = list
    if (Output.availableCards.contains(input)) {
      l = Player.updatePlayer(l, GameTurnRe.updateStacker(l(idx), GameTurnRe.playingDecks(input).head))
      GameTurnRe.playingDecks = GameTurnRe.updateDeck(GameTurnRe.playingDecks, GameTurnRe.copyList(GameTurnRe.playingDecks(input)), input)
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 100, l(idx).money))
    } else {
      l = Player.updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, l(idx).hand, l(idx).playingCards, l(idx).actions, l(idx).buys, 21, l(idx).money))
    }
    l
  }

  def merchant(list: List[Player], idx: Int, input: Int): List[Player] = {
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
