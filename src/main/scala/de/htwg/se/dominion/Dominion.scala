package de.htwg.se.dominion

import de.htwg.se.dominion.model._
import scala.util.Random
import scala.collection.immutable._

object Dominion {
  var name = "Dominion"
  def main(args: Array[String]): Unit = {
    println(name + " wurde von " + Player("Luis & Luca") + " erstellt")
    println("╔════════════════════════════════════════Dominion════════════════════════════════════════╗")

    val pAmount = model.Interaction.gamestart()

    println("╚════════════════════════════════════════════════════════════════════════════════════════╝")


    val random = new Random
    val emptyhand: List[BasicCards] = List()
    val emptyhand2: List[BasicCards] = List()
    val deckhand: List[BasicCards] = BasicCards.copper :: BasicCards.copper :: BasicCards.copper :: BasicCards.copper :: BasicCards.copper :: BasicCards.copper :: BasicCards.copper :: BasicCards.mansion :: BasicCards.mansion :: BasicCards.mansion :: emptyhand

    for (x <- 0 until sanzahl) {
      val y : List[BasicCards] = random.shuffle(deckhand)
      val x : List[BasicCards] = y.head :: y(1) :: y(2) :: y(3) :: y(4) :: emptyhand2
      println(x)
    }
  }
}
