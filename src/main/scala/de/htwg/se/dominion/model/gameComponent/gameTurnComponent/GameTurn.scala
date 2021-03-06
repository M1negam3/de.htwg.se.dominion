package de.htwg.se.dominion.model.gameComponent.gameTurnComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.gameComponent.GameTurnInterface
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player
import de.htwg.se.dominion.model.stringComponent.baseOutputComponent.Output

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.{break, breakable}

case class GameTurn() extends GameTurnInterface {

  var l: List[Player] = Nil

  var draw = 0


  override def actionPhase(list: List[Player], index: Int): List[Player] = {
    l = list
    var actionumber = l(index).actions
    var z = 0

    if (actionumber == 0) {
      actionumber = 0
      l = Player().updatePlayer(l, new Player(l(index).name, l(index).value, l(index).deck, l(index).stacker, l(index).hand,
        l(index).playingCards, actionumber, 1, 1, l(index).money))
      return l
    }

    for (f <- 0 until l(index).hand.length) {
      if (l(index).hand(f).Type.equals("Action")) {
        actionumber = 1
      }
    }

    if (actionumber > 0) {
      breakable {
        for (i <- 0 until l(index).hand.length) {
          if (l(index).hand(i).Type == "Money" || l(index).hand(i).Type == "WinningPoint") {
            z += 1
          }
        }
        if (z.equals(l(index).hand.length)) {
          l = Player().updatePlayer(l, new Player(l(index).name, l(index).value, l(index).deck, l(index).stacker,
            l(index).hand, l(index).playingCards, actionumber, 1, 1, l(index).money))
          break
        }
        z = 0
        l = Player().updatePlayer(l, new Player(l(index).name, l(index).value, l(index).deck, l(index).stacker,
          l(index).hand, l(index).playingCards, actionumber, 1, 3, l(index).money))
      }
    }
    l
  }

  override def actionPhase2(list: List[Player], index: Int, cardnumber: Int): List[Player] = {
    l = list
    var playingCards = l(index).playingCards
    var money = l(index).money
    var buys = l(index).buys
    var actions = l(index).actions

    if (cardnumber < l(index).hand.length && l(index).hand(cardnumber).Type == "Action") {
      playingCards = l(index).hand(cardnumber) :: Nil
      l = Player().updatePlayer(l, updateStacker(l(index), l(index).hand(cardnumber)))
      l = Player().updatePlayer(l, removeHandcard(cardnumber, l(index)))
      money += playingCards.head.BonusMoneyValue
      buys += playingCards.head.BuyAdditionValue
      draw += playingCards.head.DrawingValue
      // new
      l = Player().updatePlayer(l, Player().draw(l(index), draw))
      actions += playingCards.head.ActionValue
      playingCards.head.CardName match {
        case "Cellar" => l = Player().updatePlayer(l, new Player(l(index).name, l(index).value, l(index).deck, l(index).stacker, l(index).hand, playingCards, actions, buys, 7, money))
        case "Mine" => {
          var count = 0
          for (i <- 0 until l(index).hand.length) {
            if (l(index).hand(i).Type == "Money") {
              count += 1
            }
          }
          if (count > 0) {
            l = Player().updatePlayer(l, new Player(l(index).name, l(index).value, l(index).deck, l(index).stacker, l(index).hand, playingCards, actions, buys, 14, money))
          } else {
            l = Player().updatePlayer(l, new Player(l(index).name, l(index).value, l(index).deck, l(index).stacker, l(index).hand, playingCards, actions, buys, 49, money))
          }
        }
        case "Remodel" => l = Player().updatePlayer(l, new Player(l(index).name, l(index).value, l(index).deck, l(index).stacker, l(index).hand, playingCards, actions, buys, 16, money))
        case "Workshop" => l = Player().updatePlayer(l, new Player(l(index).name, l(index).value, l(index).deck, l(index).stacker, l(index).hand, playingCards, actions, buys, 33, money))
        case "Merchant" => {
          l = Player().updatePlayer(l, new Player(l(index).name, l(index).value, l(index).deck, l(index).stacker, l(index).hand, playingCards, actions, buys, l(index).stringValue, money))
          l = StrategyPatternForActionPhase.merchant(l, index)
        }
        case _ => l = Player().updatePlayer(l, new Player(l(index).name, l(index).value, l(index).deck, l(index).stacker, l(index).hand, playingCards, actions, buys, 5, money))
      }
    } else {
      l = Player().updatePlayer(l, new Player(l(index).name, l(index).value, l(index).deck, l(index).stacker, l(index).hand, playingCards, actions, buys, 9, money))
    }
    l
  }

  override def buyPhase(list: List[Player], index: Int, input: Int): List[Player] = {
    var l = list
    var test = l(index).money
    var copiedlist = l
    var copiedCard = GameTurn.playingDecks(input).head
    l = Player().updatePlayer(copiedlist, updateStacker(copiedlist(index), copiedCard))
    l = Player().updatePlayer(l, Player().updateMoney(l(index), copiedCard.CostValue))
    GameTurn.playingDecks = updateDeck(GameTurn.playingDecks, copyList(GameTurn.playingDecks(input)), input)
    l
  }

  override def updateStacker(p: Player, c: Cards): Player = {
    var copiedPlayer = p
    val copiedCard = c
    var copiedStacker = new ListBuffer[Cards]
    for (i <- 0 until copiedPlayer.stacker.length) {
      copiedStacker += copiedPlayer.stacker(i)
    }
    copiedStacker += copiedCard
    val updatedStacker: List[Cards] = copiedStacker.toList
    new Player(copiedPlayer.name, copiedPlayer.value, copiedPlayer.deck, updatedStacker, copiedPlayer.hand, copiedPlayer.playingCards, copiedPlayer.actions, copiedPlayer.buys, copiedPlayer.stringValue, copiedPlayer.money)
  }

  override def addCardToHand(p : Player, idx: Int): Player = {
    val copiedPlayer = p
    var listBuffer: ListBuffer[Cards] = ListBuffer()
    for (i <- 0 until copiedPlayer.hand.length) {
      listBuffer += copiedPlayer.hand(i)
    }
    listBuffer += GameTurn.playingDecks(idx).head
    val updatedHand = listBuffer.toList
    new Player(copiedPlayer.name, copiedPlayer.value, copiedPlayer.deck, copiedPlayer.stacker, updatedHand, copiedPlayer.playingCards, copiedPlayer.actions, copiedPlayer.buys, copiedPlayer.stringValue, copiedPlayer.money)
  }

  override def removeHandcard(i: Int, player: Player): Player = {
    val copiedPlayer = player
    var listBuffer1: ListBuffer[Cards] = ListBuffer()
    for (j <- 0 until player.hand.length) {
      listBuffer1 += player.hand(j)
    }
    var z: List[Cards] = Nil
    listBuffer1 -= player.hand(i)
    z = listBuffer1.toList
    Player(copiedPlayer.name, copiedPlayer.value, copiedPlayer.deck, copiedPlayer.stacker, z, copiedPlayer.playingCards, copiedPlayer.actions, copiedPlayer.buys, copiedPlayer.stringValue, copiedPlayer.money)
  }

  override def copyList(cards: List[Cards]): List[Cards] = {
    var l = new ListBuffer[Cards]

    for (j <- 1 until cards.length) {
      l += cards(j)
    }
    val copiedList: List[Cards] = l.toList
    copiedList
  }

  override def updateDeck(l: List[List[Cards]], o: List[Cards], i: Int): List[List[Cards]] = {
    var copiedPlayingDecks = l
    var changedList = o
    val idx = i
    var updatedPlayingDeck: ListBuffer[List[Cards]] = ListBuffer()
    for (i <- 0 until copiedPlayingDecks.length) {
      if (i == idx) {
        updatedPlayingDeck += changedList
      } else {
        updatedPlayingDeck += copiedPlayingDecks(i)
      }
    }
    val updatedList: List[List[Cards]] = updatedPlayingDeck.toList
    updatedList
  }

  override def updatePlayingDecks(l: List[List[Cards]], idx: Int): List[List[Cards]] = {
    val copiedPD = l
    var updatedPD = new ListBuffer[List[Cards]]
    for (i <- 0 until copiedPD.length) {
      updatedPD += copiedPD(i)
      if (i == idx) {
        updatedPD -= updatedPD(i)
      }
    }
    val updatedList: List[List[Cards]] = updatedPD.toList
    updatedList
  }

  override def round(pCount: Int, playerTurn: Int): Int = {
    val playerCount = pCount - 1
    var turn = playerTurn
    if (playerTurn > playerCount) {
      turn = 0
    }
    turn
  }

  override def endCheck(end: Boolean): String = {
    var s =
      Console.BLACK +
        """
          |     Press t to START the next Turn!
          |     Press q to QUIT the Game!
        """.stripMargin
    if (end) {
      s = Output().printEnd()
    }
    s
  }

  override def getMoney(player: Player): Int = {
    val copiedPlayer = player
    var m = 0
    for (i <- 0 until copiedPlayer.hand.length) {
      m += copiedPlayer.hand(i).MoneyValue
    }
    m
  }

  override def clearHand(list: List[Player], idx : Int): List[Player] = {
    var l = list
    for (e <- 0 until l(idx).hand.length) {
      l = Player().updatePlayer(l, updateStacker(l(idx), l(idx).hand(e)))
    }
    l = Player().updatePlayer(l, new Player(l(idx).name, l(idx).value, l(idx).deck, l(idx).stacker, List[Cards](),l(idx).playingCards,l(idx).actions,1,l(idx).stringValue,0))
    l
  }

  override def getCardsWCost4(): List[Int] = {
    var l: ListBuffer[Int] = new ListBuffer[Int]
    for (i <- 0 until GameTurn.playingDecks.length) {
      if (GameTurn.playingDecks(i).head.CostValue <= 4) {
        l += i
      }
    }
    val o: List[Int] = l.toList
    o
  }

  override def getCardsWC(): List[Int] = {
    var l: ListBuffer[Int] = new ListBuffer[Int]
    for (i <- 0 until GameTurn.playingDecks.length) {
      if (StrategyPatternForActionPhase.discardCardValue >= GameTurn.playingDecks(i).head.CostValue) {
        l += i
      }
    }
    val o: List[Int] = l.toList
    o
  }

}

object GameTurn {
  var playingDecks: List[List[Cards]] = Cards.playingDeck
}
