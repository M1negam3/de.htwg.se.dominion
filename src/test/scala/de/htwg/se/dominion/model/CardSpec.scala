package de.htwg.se.dominion.model

import org.scalatest._

class CardSpec extends WordSpec with Matchers {
  "A Card" when {
    "new" should {
      val copper = Card (0, 1, 0, 0, 0, 0, 0, "None", "Copper")
      "have a BuyValue" in {
        copper.BuyValue should be(0)
      }
      "have a MoneyValue" in {
        copper.MoneyValue should be(1)
      }
      "have a WpValue" in {
        copper.WpValue should be(0)
      }
      "have a ActionValue" in {
        copper.ActionValue should be(0)
      }
      "have a BuyadditionValue" in {
        copper.BuyadditionValue should be(0)
      }
      "have a BonusMoneyValue" in {
        copper.BonusMoneyValue should be(0)
      }
      "have a DrawingValue" in {
        copper.DrawingValue should be(0)
      }
      "have a EffectValue" in {
        copper.EffectValue should be("None")
      }
      "have a CardName" in {
        copper.CardName should be("Copper")
      }
    }
  }

}
