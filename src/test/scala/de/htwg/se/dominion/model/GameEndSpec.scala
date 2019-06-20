package de.htwg.se.dominion.model
import org.scalatest.{Matchers, WordSpec}
class GameEndSpec extends WordSpec with Matchers{
  var stacker: List[Cards] =List(Cards.copper,Cards.copper)
  var Luca = new Player("Luca",0,Cards.startDeck,stacker,Cards.hand)
  var Luis = new Player("Luis",0,Cards.startDeck,stacker,Cards.hand)
  var list: List[Player] = List(Luca,Luis)
  "A GameEnd" should {
    "have a end method" in {
      GameEnd.end(list).head.stacker.length should  be (0)

    }
    "have a score method" in {
      GameEnd.score(list).head should be ("Luis",0)
    }
  }
}
