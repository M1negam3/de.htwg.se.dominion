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
  "A Hand" when {
    "is not full of cards" should {
      val handful = Value(0)
      "have a value 0" in {
        handful.charAt(0) should be ('0')
      }
      "not " in {
        handful.isEmpty should be (false)
      }

    }
  }
  "A MoneyCard" when {
    "is not allowed " should {
      val MoneyCard1
        "have a value " in {
          MoneyCard1.MoneyValue should be (3)
        }
      "not be" in {
        MoneyCard1.BuyValue one should be < 3
      }
    }
   }



}
