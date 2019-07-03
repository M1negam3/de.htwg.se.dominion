package de.htwg.se.dominion

import org.scalatest._

class DominionSpec extends WordSpec with Matchers {

  "The Dominion main class" ignore {
    "accept text input as argument without readline loop, to test it from command line" in {
      Dominion.main(Array[String]("s"))
    }
  }
}