package de.htwg.se.dominion
import org.scalactic._
import org.scalatest._
import Gamevariables._

class Tests extends WordSpec with Matchers {
  "A Card" when {
    "not set to any value" should {
      val emptyCard = Value(0)
      "have a value 0" in {
        emptyCard.value should be (0)
      }
      "not be set" in {
        emptyCard.isSet should be (false)
      }
    }
  }




}
