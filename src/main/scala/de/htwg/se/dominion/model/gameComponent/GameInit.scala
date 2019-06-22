package de.htwg.se.dominion.model.gameComponent

import scala.collection.mutable.ListBuffer

object GameInit {
  def getPlayerCount(): Integer = {
    while(true)
      try {
        val sAnzahl:Integer = scala.io.StdIn.readInt()
        if (sAnzahl > 1 && sAnzahl < 6)
          return sAnzahl
        else
          println(Console.RED + "     Bitte eine Zahl zwischen 2 & 5 angeben!")
      } catch {
        case exception: NumberFormatException => println(Console.RED + "     Bitte eine Zahl eingeben!")
      }
    -1
    }

  def getPlayerName(i : Integer): List[String] = {
    var l = new ListBuffer[String]
    for (i <- 1 until i + 1) {
      printf(Console.BLUE + "     Spieler " + i + " gib name: ")
        var input = scala.io.StdIn.readLine()
        l += input
        println()
    }
    val names: List[String] = l.toList
    names
  }
}
