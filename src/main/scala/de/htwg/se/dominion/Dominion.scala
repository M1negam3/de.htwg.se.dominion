package de.htwg.se.dominion

import java.io.BufferedReader

import de.htwg.se.dominion.aview.Tui
import de.htwg.se.dominion.controller.Controller

object Dominion {

  val controller = new Controller()
  val tui = new Tui(controller)
  controller.notifyObservers()

  def main(args: Array[String]): Unit = {
    while(true) {
      tui.processInputLine(scala.io.StdIn.readLine())
    }
  }
}