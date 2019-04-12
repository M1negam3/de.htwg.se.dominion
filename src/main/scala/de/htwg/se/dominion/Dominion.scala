package de.htwg.se.dominion

import de.htwg.se.dominion.model.Player

object Dominion {
  var name = "Dominion"
  def main(args: Array[String]): Unit = {
    println(name + " wurde von " + Player("Luis & Luca") + " erstellt")
    println("Wie viele Spieler seid ihr?")
    println("Bitte eine Zahl zwischen 2 & 5 eingeben")
    val sanzahl = scala.io.StdIn.readInt()
    println("Spiel wird mit " + sanzahl + " Spieler gespielt...")
  }
}