package de.htwg.se.dominion.model.gameComponent.gameTurnComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Card
import de.htwg.se.dominion.model.gameComponent.{GameTurnInterface, StaticGameTurnInterface}
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player
import de.htwg.se.dominion.model.stringComponent.baseOutputComponent.Output
import de.htwg.se.dominion.model.playerComponent.{PlayerInterface, StaticPlayerInterface}

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.{break, breakable}

case class GameTurn(staticPlayerInterface: StaticPlayerInterface,playerInterface: PlayerInterface, staticGameTurnInterface: StaticGameTurnInterface) extends GameTurnInterface {

  var l: List[PlayerInterface] = Nil
  var playingDecks: List[List[Card]] = Card.playingDeck
  var draw = 0


  override def actionPhase(list: List[PlayerInterface], index: Int): List[PlayerInterface] = {
    l = list
    var actionumber = l(index).getActions
    var z = 0

    if (actionumber == 0) {
      actionumber = 0

      l = staticPlayerInterface.updatePlayer(l, playerInterface(l(index).getName, l(index).getValue, l(index).getDeck, l(index).getStacker, l(index).getHand,
        l(index).getPlayingCards, actionumber, 1, 1, l(index).getMoney))
      return l
    }

    for (f <- 0 until l(index).getHand.length) {
      if (l(index).getHand(f).Type.equals("Action")) {
        actionumber = 1
      }
    }

    if (actionumber > 0) {
      breakable {
        for (i <- 0 until l(index).getHand.length) {
          if (l(index).getHand(i).Type == "Money" || l(index).getHand(i).Type == "WinningPoint") {
            z += 1
          }
        }
        if (z.equals(l(index).getHand.length)) {
          l = staticPlayerInterface.updatePlayer(l, playerInterface(l(index).getName, l(index).getValue, l(index).getDeck, l(index).getStacker,
            l(index).getHand, l(index).getPlayingCards, actionumber, 1, 1, l(index).getMoney))
          break
        }
        z = 0
        l = staticPlayerInterface.updatePlayer(l, playerInterface(l(index).getName, l(index).getValue, l(index).getDeck, l(index).getStacker,
          l(index).getHand, l(index).getPlayingCards, actionumber, 1, 3, l(index).getMoney))
      }
    }
    l
  }

  override def actionPhase2(list: List[PlayerInterface], index: Int, cardnumber: Int): List[PlayerInterface] = {
    l = list
    var playingCards = l(index).getPlayingCards
    var money = l(index).getMoney
    var buys = l(index).getBuys
    var actions = l(index).getActions

    if (cardnumber < l(index).getHand.length && l(index).getHand(cardnumber).Type == "Action") {
      playingCards = l(index).getHand(cardnumber) :: Nil
      l = staticPlayerInterface.updatePlayer(l, updateStacker(l(index), l(index).getHand(cardnumber)))
      l = staticPlayerInterface.updatePlayer(l, removeHandcard(cardnumber, l(index)))
      money += playingCards.head.BonusMoneyValue
      buys += playingCards.head.BuyAdditionValue
      draw += playingCards.head.DrawingValue
      // new
      l = staticPlayerInterface.updatePlayer(l, Player().draw(l(index), draw))
      actions += playingCards.head.ActionValue
      playingCards.head.CardName match {
        case "Cellar" => l = staticPlayerInterface.updatePlayer(l,  playerInterface(l(index).getName, l(index).getValue, l(index).getDeck, l(index).getStacker, l(index).getHand, playingCards, actions, buys, 7, money))
        case "Mine" => {
          var count = 0
          for (i <- 0 until l(index).getHand.length) {
            if (l(index).getHand(i).Type == "Money") {
              count += 1
            }
          }
          if (count > 0) {
            l = staticPlayerInterface.updatePlayer(l, playerInterface(l(index).getName, l(index).getValue, l(index).getDeck, l(index).getStacker, l(index).getHand, playingCards, actions, buys, 14, money))
          } else {
            l = staticPlayerInterface.updatePlayer(l, playerInterface(l(index).getName, l(index).getValue, l(index).getDeck, l(index).getStacker, l(index).getHand, playingCards, actions, buys, 49, money))
          }
        }
        case "Remodel" => l = staticPlayerInterface.updatePlayer(l, playerInterface(l(index).getName, l(index).getValue, l(index).getDeck, l(index).getStacker, l(index).getHand, playingCards, actions, buys, 16, money))
        case "Workshop" => l = staticPlayerInterface.updatePlayer(l, playerInterface(l(index).getName, l(index).getValue, l(index).getDeck, l(index).getStacker, l(index).getHand, playingCards, actions, buys, 33, money))
        case "Merchant" => {
          l = staticPlayerInterface.updatePlayer(l, playerInterface(l(index).getName, l(index).getValue, l(index).getDeck, l(index).getStacker, l(index).getHand, playingCards, actions, buys, l(index).getStringValue, money))
          l = StrategyPatternForActionPhase.merchant(l, index)
        }
        case _ => l = staticPlayerInterface.updatePlayer(l, playerInterface(l(index).getName, l(index).getValue, l(index).getDeck, l(index).getStacker, l(index).getHand, playingCards, actions, buys, 5, money))
      }
    } else {
      l = staticPlayerInterface.updatePlayer(l, playerInterface(l(index).getName, l(index).getValue, l(index).getDeck, l(index).getStacker, l(index).getHand, playingCards, actions, buys, 9, money))
    }
    l
  }

  override def buyPhase(list: List[PlayerInterface], index: Int, input: Int): List[PlayerInterface] = {
    var l = list
    var test = l(index).getMoney
    var copiedlist = l
    var copiedCard = playingDecks(input).head
    l = staticPlayerInterface.updatePlayer(copiedlist, updateStacker(copiedlist(index), copiedCard))
    l = staticPlayerInterface.updatePlayer(l, staticPlayerInterface.updateMoney(l(index), copiedCard.CostValue))
    playingDecks = updateDeck(playingDecks, copyList(playingDecks(input)), input)
    l
  }

  override def updateStacker(p: PlayerInterface, c: Card): PlayerInterface = {
    var copiedPlayer = p
    val copiedCard = c
    var copiedStacker = new ListBuffer[Card]
    for (i <- 0 until copiedPlayer.getStacker.length) {
      copiedStacker += copiedPlayer.getStacker(i)
    }
    copiedStacker += copiedCard
    val updatedStacker: List[Card] = copiedStacker.toList
    playerInterface(copiedPlayer.getName, copiedPlayer.getValue, copiedPlayer.getDeck, updatedStacker, copiedPlayer.getHand, copiedPlayer.getPlayingCards, copiedPlayer.getActions, copiedPlayer.getBuys, copiedPlayer.getStringValue, copiedPlayer.getMoney)
  }

  override def addCardToHand(p : PlayerInterface, idx: Int): PlayerInterface = {
    val copiedPlayer = p
    var listBuffer: ListBuffer[Card] = ListBuffer()
    for (i <- 0 until copiedPlayer.getHand.length) {
      listBuffer += copiedPlayer.getHand(i)
    }
    listBuffer += playingDecks(idx).head
    val updatedHand = listBuffer.toList
    playerInterface(copiedPlayer.getName, copiedPlayer.getValue, copiedPlayer.getDeck, copiedPlayer.getStacker, updatedHand, copiedPlayer.getPlayingCards, copiedPlayer.getActions, copiedPlayer.getBuys, copiedPlayer.getStringValue, copiedPlayer.getMoney)
  }

  override def removeHandcard(i: Int, player: PlayerInterface): PlayerInterface = {
    val copiedPlayer = player
    var listBuffer1: ListBuffer[Card] = ListBuffer()
    for (j <- 0 until player.getHand.length) {
      listBuffer1 += player.getHand(j)
    }
    var z: List[Card] = Nil
    listBuffer1 -= player.getHand(i)
    z = listBuffer1.toList
    playerInterface(copiedPlayer.getName, copiedPlayer.getValue, copiedPlayer.getDeck, copiedPlayer.getStacker, z, copiedPlayer.getPlayingCards, copiedPlayer.getActions, copiedPlayer.getBuys, copiedPlayer.getStringValue, copiedPlayer.getMoney)
  }

  override def copyList(cards: List[Card]): List[Card] = {
    var l = new ListBuffer[Card]

    for (j <- 1 until cards.length) {
      l += cards(j)
    }
    val copiedList: List[Card] = l.toList
    copiedList
  }

  override def updateDeck(l: List[List[Card]], o: List[Card], i: Int): List[List[Card]] = {
    var copiedPlayingDecks = l
    var changedList = o
    val idx = i
    var updatedPlayingDeck: ListBuffer[List[Card]] = ListBuffer()
    for (i <- 0 until copiedPlayingDecks.length) {
      if (i == idx) {
        updatedPlayingDeck += changedList
      } else {
        updatedPlayingDeck += copiedPlayingDecks(i)
      }
    }
    val updatedList: List[List[Card]] = updatedPlayingDeck.toList
    updatedList
  }

  override def updatePlayingDecks(l: List[List[Card]], idx: Int): List[List[Card]] = {
    val copiedPD = l
    var updatedPD = new ListBuffer[List[Card]]
    for (i <- 0 until copiedPD.length) {
      updatedPD += copiedPD(i)
      if (i == idx) {
        updatedPD -= updatedPD(i)
      }
    }
    val updatedList: List[List[Card]] = updatedPD.toList
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

  override def getMoney(player: PlayerInterface): Int = {
    val copiedPlayer = player
    var m = 0
    for (i <- 0 until copiedPlayer.getHand.length) {
      m += copiedPlayer.getHand(i).MoneyValue
    }
    m
  }

  override def clearHand(list: List[PlayerInterface], idx : Int): List[PlayerInterface] = {
    var l = list
    for (e <- 0 until l(idx).getHand.length) {
      l = staticPlayerInterface.updatePlayer(l, updateStacker(l(idx), l(idx).getHand(e)))
    }
    l = staticPlayerInterface.updatePlayer(l, playerInterface(l(idx).getName, l(idx).getValue, l(idx).getDeck, l(idx).getStacker, List[Card](),l(idx).getPlayingCards,l(idx).getActions,1,l(idx).getStringValue,0))
    l
  }

  override def getCardsWCost4(): List[Int] = {
    var l: ListBuffer[Int] = new ListBuffer[Int]
    for (i <- 0 until playingDecks.length) {
      if (playingDecks(i).head.CostValue <= 4) {
        l += i
      }
    }
    val o: List[Int] = l.toList
    o
  }

  override def getCardsWC(): List[Int] = {
    var l: ListBuffer[Int] = new ListBuffer[Int]
    for (i <- 0 until playingDecks.length) {
      if (StrategyPatternForActionPhase.discardCardValue >= playingDecks(i).head.CostValue) {
        l += i
      }
    }
    val o: List[Int] = l.toList
    o
  }
  override def createGetPlayingDeck(): StaticGameTurnInterface = {
    staticGameTurnInterface(Card.playingDeck)
  }
}
case class StaticGameTurn(playingDecks: List[List[Card]]) extends StaticGameTurnInterface {

  override def getPlayingDecks: List[List[Card]] = { playingDecks}

}
