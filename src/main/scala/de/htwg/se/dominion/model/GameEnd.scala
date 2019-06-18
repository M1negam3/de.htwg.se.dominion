package de.htwg.se.dominion.model

import scala.collection.mutable.ListBuffer

object GameEnd {

  def end(list: List[Player]): List[Player] = {
    var copiedPlayerList = list
    var copiedDeck = new ListBuffer[Cards]
    var copiedPlayerl = new ListBuffer[Player]
    val emptyStacker: List[Cards] = Nil
    for (i <- 0 until copiedPlayerList.length) {
      for (f <- 0 until copiedPlayerList(i).deck.length) {
        copiedDeck += copiedPlayerList(i).deck(f)
      }
      for (g <- 0 until copiedPlayerList(i).stacker.length) {
        if (copiedPlayerList(i).stacker.isEmpty) {
          //TODO LEL
        }
        copiedDeck += copiedPlayerList(i).stacker(g)
      }
      val updatedDeck: List[Cards] = copiedDeck.toList
      copiedPlayerl(i) = new Player(copiedPlayerList(i).name, copiedPlayerList(i).value, updatedDeck, emptyStacker, copiedPlayerList(i).hand)
    }
    val updatedPlayerList: List[Player] = copiedPlayerl.toList
    updatedPlayerList
  }

  def score(list: List[Player]): Unit = {
  }
}