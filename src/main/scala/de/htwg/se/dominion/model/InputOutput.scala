package de.htwg.se.dominion.model

import scala.collection.mutable.ListBuffer

object InputOutput {
  def getPlayerCount(): Integer = {
    println(Console.BLUE + "Wie viele Spieler seid ihr?")
    while(true)
      try {
        val sAnzahl:Integer = scala.io.StdIn.readInt()
        if (sAnzahl > 1 && sAnzahl < 6)
          return sAnzahl
        else
          println(Console.RED + "Bitte eine Zahl zwischen 2 & 5 angeben!")
      } catch {
        case exception: NumberFormatException => println(Console.RED + "Bitte eine Zahl eingeben!")
      }
    -1
    }

  def getPlayerName(i : Integer): List[String] = {
    var l = new ListBuffer[String]
    for (i <- 1 until i + 1) {
      printf("Spieler " + i + " gib name: ")
        var input = scala.io.StdIn.readLine()
        l += input
        println()
    }
    val names: List[String] = l.toList
    names
  }

  def deckCreation(a:Integer): String = {
    var s = ""
    for (i <- 1 until a + 1)
      s += "==> Deck " + i + " is created!" + "\n"
    s
  }

  def HandCardCreation(l:List[BasicCards], a:Integer): String = {
    var s = "Player " + a + "`s Hand Cards are: "
    for (i <- 0 until 5) {
      s += l(i).CardName + ", "
    }
    s
  }
}
