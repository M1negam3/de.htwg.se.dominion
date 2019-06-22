package de.htwg.se.dominion.model.gameComponent

import scala.collection.mutable.ListBuffer

object GameInitRe {

  def getPlayerCount(evalInput: String): Integer = {
    try {
      val sAnzahl = evalInput.toInt
       if (sAnzahl > 1 && sAnzahl < 6) {
         return sAnzahl
       } else {
         println(Console.RED + "     Please enter a Number between 2 & 5!")
       }
      } catch {
        case exception: NumberFormatException => println(Console.RED + "     Enter a Number!")
      }
    -1
  }

  def getPlayerName(i : Integer, evalInput: String): List[String] = {
    var l = new ListBuffer[String]
    for (i <- 0 until i) {
      printf(Console.BLUE + "     Player " + (i + 1) + " enter your name: ")
      try {
        val input = evalInput
        l += input
      } catch {
        case exception: StringContext => println(Console.RED + "     Enter a Number!")
      }
      println()
    }
    val names: List[String] = l.toList
    println(names)
    names
  }

}
