package de.htwg.se.dominion

import de.htwg.se.dominion.aview.InputOutput
import de.htwg.se.dominion.model._

import scala.util.Random
import scala.collection.immutable._

object Dominion {
  var name = "Dominion"
  def main(args: Array[String]): Unit = {
    println(name + " wurde von " + Player("Luis & Luca") + " erstellt")
    println(Console.WHITE + "╔════════════════════════════════════════ Dominion-TUI ════════════════════════════════════╗")

    val pAmount = InputOutput.gamestart()
    GameStart.createDeck(pAmount)
    println(Console.WHITE + "╚══════════════════════════════════════════════════════════════════════════════════════════╝")
  }
}
