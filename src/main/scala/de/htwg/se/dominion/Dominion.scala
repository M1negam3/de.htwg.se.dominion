package de.htwg.se.dominion

import de.htwg.se.dominion.model.Player

object Dominion {
  var name = "Dominion"
  def main(args: Array[String]): Unit = {
    println(name + " wurde von " + Player("Luis & Luca") + " erstellt")
  }
}