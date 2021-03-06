package de.htwg.de.dominion.model.gameComponent.gameInitComponent

import de.htwg.se.dominion.model.gameComponent.gameInitComponent.GameInit
import org.scalatest.{Matchers, WordSpec}

class GameInitSpec extends WordSpec with Matchers{
   var old: List[String] = List("Luca","Luis")
  var newer= "Test"
  var resu: List[String] = List("Luca","Luis","Test")
  "A GameIni" should {
      "have a getPlayerName method" in {
        GameInit().getPlayerName(old,newer) should be (resu)
      }
    }
}
