package de.htwg.se.dominion.model

import org.scalatest._

class FunctionsSpec extends WordSpec with Matchers {
  "A function" should {
    "be" in {
      Functions.test() should be("test")
    }
  }
}
