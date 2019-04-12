package de.htwg.se.dominion.model

import org.scalatest._

class CardsSpec extends WordSpec with Matchers {
  "A Card" when {
    "new" should {
      val copper = Cards (0, 1, 0, 0, 0, 0, 0, "None", "Copper")
      val gold = Cards (6, 3, 0, 0, 0, 0, 0, "None", "Gold")
      val silver = Cards (3, 2, 0, 0, 0, 0, 0, "None", "Silver")
      val mansion = Cards (2, 0, 1, 0, 0, 0, 0, "None", "Mansion")
      val duchy = Cards (5, 0, 3, 0, 0, 0, 0, "None", "Duchy")
      val province = Cards (8, 0, 6, 0, 0, 0, 0, "None", "Province")
      "have a BuyValue" in {
        copper.BuyValue should be(0)
        silver.BuyValue should be(3)
        gold.BuyValue should be(6)
        mansion.BuyValue should be(2)
        duchy.BuyValue should be(5)
        province.BuyValue should be(8)
      }
      "have a MoneyValue" in {
        copper.MoneyValue should be(1)
        silver.MoneyValue should be(2)
        gold.MoneyValue should be(3)
        mansion.MoneyValue should be(0)
        duchy.MoneyValue should be(0)
        province.MoneyValue should be(0)
      }
      "have a WpValue" in {
        copper.WpValue should be(0)
        silver.WpValue should be(0)
        gold.WpValue should be(0)
        mansion.WpValue should be(1)
        duchy.WpValue should be(3)
        province.WpValue should be(6)
      }
      "have a ActionValue" in {
        copper.ActionValue should be(0)
        silver.ActionValue should be(0)
        gold.ActionValue should be(0)
        mansion.ActionValue should be(0)
        duchy.ActionValue should be(0)
        province.ActionValue should be(0)
      }
      "have a BuyadditionValue" in {
        copper.BuyadditionValue should be(0)
        silver.BuyadditionValue should be(0)
        gold.BuyadditionValue should be(0)
        mansion.BuyadditionValue should be(0)
        duchy.BuyadditionValue should be(0)
        province.BuyadditionValue should be(0)
      }
      "have a BonusMoneyValue" in {
        copper.BonusMoneyValue should be(0)
        silver.BonusMoneyValue should be(0)
        gold.BonusMoneyValue should be(0)
        mansion.BonusMoneyValue should be(0)
        duchy.BonusMoneyValue should be(0)
        province.BonusMoneyValue should be(0)
      }
      "have a DrawingValue" in {
        copper.DrawingValue should be(0)
        silver.DrawingValue should be(0)
        gold.DrawingValue should be(0)
        mansion.DrawingValue should be(0)
        duchy.DrawingValue should be(0)
        province.DrawingValue should be(0)
      }
      "have a EffectValue" in {
        copper.EffectValue should be("None")
        silver.EffectValue should be("None")
        gold.EffectValue should be("None")
        mansion.EffectValue should be("None")
        duchy.EffectValue should be("None")
        province.EffectValue should be("None")
      }
      "have a CardName" in {
        copper.CardName should be("Copper")
        silver.CardName should be("Silver")
        gold.CardName should be("Gold")
        mansion.CardName should be("Mansion")
        duchy.CardName should be("Duchy")
        province.CardName should be("Province")
      }
    }
  }
}