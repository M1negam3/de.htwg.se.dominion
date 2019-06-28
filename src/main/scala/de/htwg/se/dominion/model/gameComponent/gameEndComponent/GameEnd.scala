package de.htwg.se.dominion.model.gameComponent.gameEndComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.gameComponent.GameEndInterface
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player

import scala.collection.mutable.ListBuffer

case class GameEnd() extends GameEndInterface {

  override def end(list: List[Player]): List[Player] = {
    var copiedPlayerList = list
    var copiedDeck = new ListBuffer[Cards]
    var copiedPlayerl = new ListBuffer[Player]
    val emptyStacker: List[Cards] = Nil
    for (i <- 0 until copiedPlayerList.length) {
      if (copiedPlayerList(i).deck.nonEmpty) {
        for (f <- 0 until copiedPlayerList(i).deck.length) {
          copiedDeck += copiedPlayerList(i).deck(f)
        }
      }

      if (copiedPlayerList(i).stacker.nonEmpty) {
        for (g <- 0 until copiedPlayerList(i).stacker.length) {
          copiedDeck += copiedPlayerList(i).stacker(g)
        }
      }

      val updatedDeck: List[Cards] = copiedDeck.toList
      copiedDeck = ListBuffer[Cards]()
      copiedPlayerl += new Player(copiedPlayerList(i).name, copiedPlayerList(i).value, updatedDeck, emptyStacker, copiedPlayerList(i).hand, copiedPlayerList(i).playingCards, copiedPlayerList(i).actions, copiedPlayerList(i).value, copiedPlayerList(i).stringValue, copiedPlayerList(i).money)
    }
    val updatedPlayerList: List[Player] = copiedPlayerl.toList
    updatedPlayerList
  }

  override def score(list: List[Player]): List[(Int, String)] = {
    val copiedPlayerList = list
    val pCount = copiedPlayerList.length
    var wp = 0
    var garden = 0
    var data: ListBuffer[(Int, String)] = new ListBuffer()
    var sortedData: ListBuffer[(Int, String)] = new ListBuffer()

    for (i <- 0 until pCount) {
      for (f <- 0 until copiedPlayerList(i).deck.length) {
        wp += copiedPlayerList(i).deck(f).WpValue
        if (copiedPlayerList(i).deck(f).CardName.equals("Gardens")) {
          garden += 1
        }
      }
      wp += garden * (copiedPlayerList(i).deck.length / 10)
      data += ((wp, copiedPlayerList(i).name))
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
