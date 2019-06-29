package de.htwg.se.dominion

import com.google.inject.{Guice, Injector}
import de.htwg.se.dominion.aview.Tui
import de.htwg.se.dominion.aview.gui.SwingGui
import de.htwg.se.dominion.controller.ControllerInterface
import de.htwg.se.dominion.controller.maincontroller.{Controller, RoundManager}

object Dominion {

  val injector: Injector = Guice.createInjector(new DominionModule)
  val controller: Controller = injector.getInstance(classOf[Controller])
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {
    var input: String = ""
    if (args.length > 0) input = args(0)
    if (!input.isEmpty) tui.processInputLine(input)
    else do {
      input = scala.io.StdIn.readLine()
      tui.processInputLine(input)
    } while (input != "q")
  }
}