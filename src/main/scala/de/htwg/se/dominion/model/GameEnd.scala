package de.htwg.se.dominion.model

import scala.collection.mutable.ListBuffer

object GameEnd {

  def end(list: List[Player]): List[Player] = {
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
      copiedPlayerl += new Player(copiedPlayerList(i).name, copiedPlayerList(i).value, updatedDeck, emptyStacker, copiedPlayerList(i).hand)
    }
    val updatedPlayerList: List[Player] = copiedPlayerl.toList
    updatedPlayerList
  }

  def score(list: List[Player]): Map[String, Int] = {
    val copiedPlayerList = list
    val pCount = copiedPlayerList.length
    var wp = 0
    var mutableScore: Map[String, Int] = Map()
    var sortedScore: Map[String, Int] = Map()
    var garden = 0

    for (i <- 0 until pCount) {
      for (f <- 0 until copiedPlayerList(i).deck.length) {
        wp += copiedPlayerList(i).deck(f).WpValue
        if (copiedPlayerList(i).deck(f).CardName.equals("Gardens")) {
          garden += 1
        }
      }
      wp += garden * (copiedPlayerList(i).deck.length / 10)
      mutableScore += (copiedPlayerList(i).name -> wp)
      wp = 0
      garden = 0
    }
    for ((k, v) <- mutableScore) {
      sortedScore += mutableScore.max
      mutableScore -= mutableScore.max._1
    }
    val score: Map[String, Int] = sortedScore.toMap
    score
  }
}