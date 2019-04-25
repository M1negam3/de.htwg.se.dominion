package de.htwg.se.dominion.model

object InputOutput {
  def gamestart(): Integer = {
    println("Wie viele Spieler seid ihr?")
    while(true)
      try {
        val sAnzahl:Integer = scala.io.StdIn.readInt()
        if (sAnzahl > 1 && sAnzahl < 6)
          return sAnzahl
        else
          println("Bitte eine Zahl zwischen 2 & 5 angeben!")
      } catch {
        case exception: NumberFormatException => println("Bitte eine Zahl eingeben!")
      }
    return -1
    }
}
