package de.htwg.se.dominion

import de.htwg.se.dominion.model.{BasicCards, Player}
import scala.util.Random
import scala.collection.generic._
import scala.collection.immutable._

object Dominion {
  var name = "Dominion"
  def main(args: Array[String]): Unit = {
    println(name + " wurde von " + Player("Luis & Luca") + " erstellt")
    println("Wie viele Spieler seid ihr?")
    println("Bitte eine Zahl zwischen 2 & 5 eingeben")
    val sanzahl = scala.io.StdIn.readInt()


    println("Spiel wird mit " + sanzahl + " Spieler gespielt...")



    val random = new Random
    val emptyhand: List[String] = List.empty[String]
    val emptyhand2: List[String] = List.empty[String]
    val stackhand: List[String] = "copper" :: "copper" :: "copper" :: "copper" :: "copper"  :: "mansion" :: "mansion" :: "mansion":: emptyhand
    val deck: List[String] = random.shuffle(List("copper", "copper", "copper", "copper", "copper","mansion","mansion","mansion"))
    val hand: List[String] = deck.head :: deck(1) :: deck(2) :: deck(3) :: deck(4) :: emptyhand2
    println(s"$hand")

  }
}
