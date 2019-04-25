package de.htwg.se.dominion

import de.htwg.se.dominion.model.{BasicCards, Player}
import scala.util.Random
import scala.collection.generic._
import scala.collection.immutable._
import java.io.IOException


import scala.util.{Try,Success,Failure}



object Dominion {
  var name = "Dominion"
  def main(args: Array[String]): Unit = {
    println(name + " wurde von " + Player("Luis & Luca") + " erstellt")
    println("Wie viele Spieler seid ihr?")
    println("Bitte eine Zahl zwischen 2 & 5 eingeben")
    try {
      val sanzahl = scala.io.StdIn.readInt()
    } catch {
      case exception: NumberFormatException => println("Bitte eine Zahl eingeben")
    }
    val sanzahl = scala.io.StdIn.readInt()
    if (sanzahl > 1 && sanzahl < 6) {
      println("Spiel wird mit " + sanzahl + " Spieler gespielt...")
    } else {
      println("Bitte eine Zahl zwischen 2 und 5 eingeben")
      val sanzahl = scala.io.StdIn.readInt()
    }




    val random = new Random
    val emptyhand: List[BasicCards] = List()
    val emptyhand2: List[BasicCards] = List()
    val deckhand: List[BasicCards] = BasicCards.copper :: BasicCards.copper :: BasicCards.copper :: BasicCards.copper :: BasicCards.copper :: BasicCards.copper :: BasicCards.copper :: BasicCards.mansion :: BasicCards.mansion :: BasicCards.mansion :: emptyhand

    for (x <- 0 until sanzahl) {
      val y : List[BasicCards] = random.shuffle(deckhand)
      val x : List[BasicCards] = y.head :: y(1) :: y(2) :: y(3) :: y(4) :: emptyhand2
      println(s"$x")
    }

  }
}
