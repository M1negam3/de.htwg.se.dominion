package de.htwg.se.dominion.model.playerComponent

import de.htwg.se.dominion.model.deckComponent.Cards
import de.htwg.se.dominion.model.playerComponent.Player.{copiedPlayer, copyList, isEmpty}
import org.scalatest._

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.List

class PlayerSpec extends WordSpec with Matchers {
  val pCount = 2
  val names: List[String] = List("Luca","Luis")
  val hand: List[Cards] = List(Cards.copper,Cards.copper,Cards.copper,Cards.copper,Cards.copper)
  val silver: List[Cards] = List(Cards.silver,Cards.silver,Cards.silver,Cards.silver,Cards.silver)
  var Luca = new Player("Luca",0,Cards.startDeck,Cards.stacker,Cards.hand)
  var Luis = new Player("Luis",0,Cards.startDeck,Cards.stacker,hand)
  var list: List[Player] = List(Luca,Luis)
  var l = new ListBuffer[Cards]
  var d = new ListBuffer[Cards]
  "A Player" when {
    "new" should {
      val player = Player("Your Name",0,Cards.startDeck,Cards.stacker,Cards.hand)
      "have a name" in {
        player.name should be("Your Name")
      }
      "have a nice String representation" in {
        player.toString should be("Your Name")
      }
    }
  }
      "A player" should {
        "have a createPlayer method" in {
          Player.createPlayer(2,names) should not be (list.head == Luis)
        }
        "have a getHand method" in {
          Player.getHand(Luca) should not be (Cards.copper,Cards.copper,Cards.copper,Cards.copper)
          var copiedPlayer = Luca
          var copyList = Luca.deck
          copyList.length match {
            case 0 => copiedPlayer = isEmpty(copiedPlayer)
              copyList = copiedPlayer.deck
              for (i <- 0 until 5) {
                l += copyList(i)
              } should not be (copyList)
            case 1 => l += copyList.head
              copiedPlayer = isEmpty(copiedPlayer)
              copyList = copiedPlayer.deck
              for (i <- 0 until 4) {
                l += copyList(i)
              } should not be (copyList)
            case 2 => l += copyList.head
              l += copyList(1)
              copiedPlayer = isEmpty(copiedPlayer)
              copyList = copiedPlayer.deck
              for (i <- 0 until 3) {
                l += copyList(i)
              } should not be (copyList)
            case 3 => for (i <- 0 until copyList.length) {
              l += copyList(i)
            }
              copiedPlayer = isEmpty(copiedPlayer)
              copyList = copiedPlayer.deck
              for (i <- 0 until 2) {
                l += copyList(i)
              } should not be (copyList)
            case 4 =>for (i <- 0 until copyList.length) {
              l += copyList(i)
            }
              copiedPlayer = isEmpty(copiedPlayer)
              copyList = copiedPlayer.deck
              l += copyList.head
              l should not be (copyList)
            case _ =>
              for (i <- 0 until 5) {
                l += copyList(i)
              } should not be (copyList)
          }

        }
        "have a getMoney method" in {
          Player.getMoney(Luis) should not be be (5)
        }
        "have a updatePlayer method " in {
          Player.updatePlayer(list, Player.draw(Luca, 2)) should not be (Luca.hand.length == 4)
        }
        "have a draw method" in {
          Player.draw(Luca, 5) should not be (list.head.hand.length == 5)
        }
        "have a upgrading method" in {
          Player.upgrading(Luis, 0, silver) should not be (Luis.hand.head == Cards.copper)
        }
        "have a isEmpty method " in {
          Player.isEmpty(Luca) should not be (Luca.stacker.length == 1)
        }
      }
}
