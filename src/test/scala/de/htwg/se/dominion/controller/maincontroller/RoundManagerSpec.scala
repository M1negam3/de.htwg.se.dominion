package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.model.playerComponent.Player
import de.htwg.se.dominion.controller.maincontroller._
import org.scalatest._

class RoundManagerSpec extends WordSpec with Matchers {
  var players: List[Player] = Nil
  var numberOfRounds: Int = 0
  var numberOfPlayers: Int = 0
  var names: List[String] = Nil
  var score: Map[String, Int] = Map()
  var idx: Int = 0
  var r = RoundManager(players,numberOfRounds,numberOfPlayers,names,score,idx)


  "A Round Manager" when {
    "new" should {
      "have this values" in {
        r.players should be(Nil)
        r.numberOfRounds should be(0)
        r.numberOfPlayers should be(0)
        r.names should be(Nil)
        r.score should be(score)
        r.idx should be(0)
      }
      "have a getNUmberOfPlayers method" ignore {

      }
      "have a getNames method" ignore {
      }
      "have a createPlayer method" in {
        r.createPlayer(r) should be (r)
      }
      "have a actionPhase method" ignore {

      }
      "have a buyPhase method" ignore {

      }
      "have a score method " in {
        r.score(r) should be (r)
      }
      "have a playerTurn method" in {
        r.playerTurn(r) should be (r)
      }
      "have a roundNumber method" in {
        r.roundNumber(r) should be (r)
      }
      "have a turn method" ignore {

      }
      "have a end method" in {
        r.end(r) should be (r)
      }
    }
  }
}
