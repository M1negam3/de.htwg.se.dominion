package de.htwg.se.dominion
import de.htwg.se.dominion.Gamevariables._
import org.scalatest._

class Tests extends WordSpec with Matchers {
  "A Card" when {
    "not set to any value" should {
      val emptyCard = Value(0)
      "have a value 0" in {
        emptyCard.charAt(0) should be ('0')
      }
      "not be set" in {
        emptyCard.isEmpty should be (false)
      }
    }
  }




}
