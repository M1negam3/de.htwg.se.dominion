package de.htwg.se.dominion.model.gameComponent

import scala.collection.mutable.ListBuffer

object GameInitRe {
  def getPlayerName(oldNames: List[String], evalInput: String): List[String] = {
    var l = new ListBuffer[String]
    for (i <- 0 until oldNames.length) {
        l += oldNames(i)
    }
    l += evalInput
    val names: List[String] = l.toList
    names
  }
}
