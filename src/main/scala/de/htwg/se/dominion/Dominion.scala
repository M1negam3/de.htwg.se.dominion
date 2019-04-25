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



    val copper = BasicCards(0,1,0,0,0,0,0,"nothing","copper")
    val mansion = BasicCards(2,0,1,0,0,0,0,"nothing","mansion")
    val random = new Random
    val emptyhand: List[BasicCards] = List()
    val emptyhand2: List[BasicCards] = List()
    val deckhand: List[BasicCards] = copper :: copper :: copper :: copper :: copper :: copper :: copper :: mansion :: mansion :: mansion :: emptyhand
    val deckp1: List[BasicCards] = random.shuffle(deckhand)
    val handp1: List[BasicCards] = deckp1.head :: deckp1(1) :: deckp1(2) :: deckp1(3) :: deckp1(4) :: emptyhand2
    println(s"$handp1")
    for (x <- 1 until sanzahl) {
      val y : List[BasicCards] = random.shuffle(deckhand)
      val x : List[BasicCards] = y.head :: y(1) :: y(2) :: y(3) :: y(4) :: emptyhand2
      println(s"$x")
    }

  }
}
