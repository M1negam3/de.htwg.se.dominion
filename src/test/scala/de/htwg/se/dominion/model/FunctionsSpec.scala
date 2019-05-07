package de.htwg.se.dominion.model

import org.scalatest._

import scala.collection.immutable.List

class FunctionsSpec extends WordSpec with Matchers {
  val basicDeck: List[BasicCards] = List(BasicCards.copper, BasicCards.copper, BasicCards.copper, BasicCards.copper,
    BasicCards.copper, BasicCards.copper, BasicCards.mansion, BasicCards.mansion, BasicCards.mansion)
  val names: List[String] = List("luca")
  val pCount = 1
  val fertig = new Player(1,"luca",basicDeck,basicDeck)

  "A Function" should {
    "have a handmoneyvalue" in {
      Functions.handMoney(basicDeck) should be (5)
    }
    "have a handactions" in {
      Functions.handActions(basicDeck) should be (0)
    }
    "have a handadditionalbuys" in {
      Functions.handAdditionalBuys(basicDeck) should be (0)
    }
    "have a Player method" in {
      Functions.Player(pCount,names,basicDeck,basicDeck) should be (fertig)
    }

  }
}
