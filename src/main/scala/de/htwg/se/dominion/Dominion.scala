package de.htwg.se.dominion

import java.io.BufferedReader

import de.htwg.se.dominion.model.{Deck, InputOutput, Player, _}
import de.htwg.se.dominion.aview.Tui
import de.htwg.se.dominion.controller.Controller

object Dominion {

  val controller = new Controller()
  val tui = new Tui(controller)
  controller.notifyObservers()

  def main(args: Array[String]): Unit = {
    println("Commands:")
    println("q = quit")
    println("n = new Game")
    println("t = next Trun")

    while(true) {
      tui.processInputLine(scala.io.StdIn.readLine())
    }
  }
}