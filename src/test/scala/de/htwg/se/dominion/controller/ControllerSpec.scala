package de.htwg.se.dominion.controller

import de.htwg.se.dominion.controller.maincontroller.Controller
import de.htwg.se.dominion.util.Observer
import org.scalatest._

class ControllerSpec extends WordSpec with Matchers {
// TUT NOCH NICHTS DA CONTROLLER NIX GUT
  "A Controller" when {
    "observed by an Observer" should {
      val controller = new Controller()
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Boolean = {updated = true; updated}
      }
      controller.add(observer)
    }
  }
}