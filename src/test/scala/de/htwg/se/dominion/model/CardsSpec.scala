package de.htwg.se.dominion.model

import org.scalatest._

class CardsSpec extends WordSpec with Matchers {
  "A Card" when {
    "new" should {
      val copper = Cards (0, 1, 0, 0, 0, 0, 0, "None", "Copper")
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
      val silver = Cards (3, 2, 0, 0, 0, 0, 0, "None", "Silver")
      "have a BuyValue" in {
        silver.BuyValue should be(3)
      }
      "have a MoneyValue" in {
        silver.MoneyValue should be(2)
      }
      "have a WpValue" in {
        silver.WpValue should be(0)
      }
      "have a ActionValue" in {
        silver.ActionValue should be(0)
      }
      "have a BuyadditionValue" in {
        silver.BuyadditionValue should be(0)
      }
      "have a BonusMoneyValue" in {
        silver.BonusMoneyValue should be(0)
      }
      "have a DrawingValue" in {
        silver.DrawingValue should be(0)
      }
      "have a EffectValue" in {
        silver.EffectValue should be("None")
      }
      "have a CardName" in {
        silver.CardName should be("Silver")
      }
      val gold = Cards (6, 3, 0, 0, 0, 0, 0, "None", "Gold")
      "have a BuyValue" in {
        gold.BuyValue should be(6)
      }
      "have a MoneyValue" in {
        gold.MoneyValue should be(3)
      }
      "have a WpValue" in {
        gold.WpValue should be(0)
      }
      "have a ActionValue" in {
        gold.ActionValue should be(0)
      }
      "have a BuyadditionValue" in {
        gold.BuyadditionValue should be(0)
      }
      "have a BonusMoneyValue" in {
        gold.BonusMoneyValue should be(0)
      }
      "have a DrawingValue" in {
        gold.DrawingValue should be(0)
      }
      "have a EffectValue" in {
        gold.EffectValue should be("None")
      }
      "have a CardName" in {
        gold.CardName should be("Gold")
      }
      val mansion = Cards (2, 0, 1, 0, 0, 0, 0, "None", "Mansion")
      "have a BuyValue" in {
        mansion.BuyValue should be(2)
      }
      "have a MoneyValue" in {
        mansion.MoneyValue should be(0)
      }
      "have a WpValue" in {
        mansion.WpValue should be(1)
      }
      "have a ActionValue" in {
        mansion.ActionValue should be(0)
      }
      "have a BuyadditionValue" in {
        mansion.BuyadditionValue should be(0)
      }
      "have a BonusMoneyValue" in {
        mansion.BonusMoneyValue should be(0)
      }
      "have a DrawingValue" in {
        mansion.DrawingValue should be(0)
      }
      "have a EffectValue" in {
        mansion.EffectValue should be("None")
      }
      "have a CardName" in {
        mansion.CardName should be("Mansion")
      }
      val duchy = Cards (5, 0, 3, 0, 0, 0, 0, "None", "Duchy")
      "have a BuyValue" in {
        duchy.BuyValue should be(5)
      }
      "have a MoneyValue" in {
        duchy.MoneyValue should be(0)
      }
      "have a WpValue" in {
        duchy.WpValue should be(3)
      }
      "have a ActionValue" in {
        duchy.ActionValue should be(0)
      }
      "have a BuyadditionValue" in {
        duchy.BuyadditionValue should be(0)
      }
      "have a BonusMoneyValue" in {
        duchy.BonusMoneyValue should be(0)
      }
      "have a DrawingValue" in {
        duchy.DrawingValue should be(0)
      }
      "have a EffectValue" in {
        duchy.EffectValue should be("None")
      }
      "have a CardName" in {
        duchy.CardName should be("Duchy")
      }
      val province = Cards (8, 0, 6, 0, 0, 0, 0, "None", "Province")
      "have a BuyValue" in {
        province.BuyValue should be(8)
      }
      "have a MoneyValue" in {
        province.MoneyValue should be(0)
      }
      "have a WpValue" in {
        province.WpValue should be(6)
      }
      "have a ActionValue" in {
        province.ActionValue should be(0)
      }
      "have a BuyadditionValue" in {
        province.BuyadditionValue should be(0)
      }
      "have a BonusMoneyValue" in {
        province.BonusMoneyValue should be(0)
      }
      "have a DrawingValue" in {
        province.DrawingValue should be(0)
      }
      "have a EffectValue" in {
        province.EffectValue should be("None")
      }
      "have a CardName" in {
        province.CardName should be("Province")
      }
    }
  }

}
