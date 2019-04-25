package de.htwg.se.dominion.model

object InputOutput {
  def gamestart(): Integer = {
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
    return -1
    }

  def deckCreation(a:Integer): Unit = {
    println(Console.BLUE + "==> Deck " + a + " is created!")
  }

  def HandCardCreation(l:List[BasicCards], a:Integer): Unit = {
    println(Console.BLUE + "Player " + a + "`s Hand Cards are: " + l.head.CardName + ", " + l(1).CardName +
      ", " + l(2).CardName + ", " + l(3).CardName+ ", " + l(4).CardName)
  }
  def playersTurn(a:Integer) : Unit = {
    println(Console.GREEN + "Player " + a + "'s Turn")
  }
}
