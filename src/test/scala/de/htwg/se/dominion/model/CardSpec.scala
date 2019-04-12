package de.htwg.se.dominion.model

import org.scalatest._

class CardSpec extends WordSpec with Matchers {
  "A Card" when {
    "new" should {
      val MoneyCard = Card(0, 1, 0, 0, 0, 0, 0, "None", "Copper")
      "have a BuyValue" in {
          MoneyCard.BuyValue should be(0)
      }
      "have a MoneyValue" in {
        MoneyCard.MoneyValue should be(1)
      }
      "have a WpValue" in {
        MoneyCard.WpValue should be(0)
      }
      "have a ActionValue" in {
        MoneyCard.ActionValue should be(0)
      }
      "have a BuyadditionValue" in {
        MoneyCard.BuyadditionValue should be(0)
      }
      "have a BonusMoneyValue" in {
        MoneyCard.BonusMoneyValue should be(0)
      }
      "have a DrawingValue" in {
        MoneyCard.DrawingValue should be(0)
      }
      "have a EffectValue" in {
        MoneyCard.EffectValue should be("None")
      }
      "have a CardName" in {
        MoneyCard.CardName should be("Copper")
      }
    }
  }

}
