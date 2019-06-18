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
        if (copiedPlayerList(i).stacker.nonEmpty) {
          copiedDeck += copiedPlayerList(i).stacker(g)
        }
      }

      val updatedDeck: List[Cards] = copiedDeck.toList
      copiedPlayerl(i) = new Player(copiedPlayerList(i).name, copiedPlayerList(i).value, updatedDeck, emptyStacker, copiedPlayerList(i).hand)
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

    for (i <- 0 until pCount) {
      for (f <- 0 until copiedPlayerList(i).deck.length) {
        wp = copiedPlayerList(i).deck(f).WpValue
      }
      mutableScore += (copiedPlayerList(i).name -> wp)
      wp = 0
    }
    for ((k, v) <- mutableScore) {
      sortedScore += mutableScore.max
      mutableScore -= mutableScore.max._1
    }

    val score: Map[String, Int] = sortedScore.toMap
    score
  }
}