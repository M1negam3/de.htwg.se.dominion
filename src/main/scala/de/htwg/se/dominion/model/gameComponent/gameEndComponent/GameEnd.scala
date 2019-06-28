package de.htwg.se.dominion.model.gameComponent.gameEndComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Card
import de.htwg.se.dominion.model.gameComponent.GameEndInterface
import de.htwg.se.dominion.model.playerComponent.{PlayerInterface, StaticPlayerInterface}
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.playerInterface

import scala.collection.mutable.ListBuffer

case class GameEnd(staticPlayerInterface: StaticPlayerInterface, playerInterface: PlayerInterface) extends GameEndInterface {

  override def end(list: List[PlayerInterface]): List[PlayerInterface] = {
    var copiedPlayerList = list
    var copiedDeck = new ListBuffer[Card]
    var copiedPlayerl = new ListBuffer[PlayerInterface]
    val emptyStacker: List[Card] = Nil
    for (i <- 0 until copiedPlayerList.length) {
      if (copiedPlayerList(i).getDeck.nonEmpty) {
        for (f <- 0 until copiedPlayerList(i).getDeck.length) {
          copiedDeck += copiedPlayerList(i).getDeck(f)
        }
      }

      if (copiedPlayerList(i).getStacker.nonEmpty) {
        for (g <- 0 until copiedPlayerList(i).getStacker.length) {
          copiedDeck += copiedPlayerList(i).getStacker(g)
        }
      }

      val updatedDeck: List[Card] = copiedDeck.toList
      copiedDeck = ListBuffer[Card]()
      copiedPlayerl += playerInterface(copiedPlayerList(i).getName, copiedPlayerList(i).getValue, updatedDeck, emptyStacker, copiedPlayerList(i).getHand, copiedPlayerList(i).getPlayingCards, copiedPlayerList(i).getActions, copiedPlayerList(i).getValue, copiedPlayerList(i).getStringValue, copiedPlayerList(i)getMmoney)
    }
    val updatedPlayerList: List[PlayerInterface] = copiedPlayerl.toList
    updatedPlayerList
  }

  override def score(list: List[PlayerInterface]): List[(Int, String)] = {
    val copiedPlayerList = list
    val pCount = copiedPlayerList.length
    var wp = 0
    var garden = 0
    var data: ListBuffer[(Int, String)] = new ListBuffer()
    var sortedData: ListBuffer[(Int, String)] = new ListBuffer()

    for (i <- 0 until pCount) {
      for (f <- 0 until copiedPlayerList(i).getDeck.length) {
        wp += copiedPlayerList(i).getDeck(f).WpValue
        if (copiedPlayerList(i).getDeck(f).CardName.equals("Gardens")) {
          garden += 1
        }
      }
      wp += garden * (copiedPlayerList(i).getDeck.length / 10)
      data += ((wp, copiedPlayerList(i).getName))
      wp = 0
      garden = 0
    }

    for (i <- 0 until data.length - 1) {
      for (f <- 1 until data.length) {
        if (data(i)._1 == data(f)._1) {
          sortedData += data(i)
          data -= data(i)
        } else {
          if (data(i)._1 > data(f)._1) {
            sortedData += data(i)
            data -= data(i)
          } else {
            sortedData += data(f)
            data -= data(f)
          }
        }
      }
    }
    if (data.length == 1) {
      sortedData += data.head
      data -= data.head
    }

    val score: List[(Int, String)] = sortedData.toList
    score
  }
}
