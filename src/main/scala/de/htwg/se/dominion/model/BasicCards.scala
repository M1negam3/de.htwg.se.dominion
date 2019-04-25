package de.htwg.se.dominion.model

case class BasicCards(CostValue : Int, MoneyValue : Int, WpValue : Int, ActionValue : Int, BuyAdditionValue : Int,
                      BonusMoneyValue : Int, DrawingValue : Int, EffectValue : String, CardName : String) {
}

object BasicCards{
  // Moneycards
  val copper = new BasicCards(0,1,0,0,0,0,
    0,"None","Copper")
  val silver = new BasicCards(0,1,0,0,0,0,
    0,"None","Silver")
  val gold = new BasicCards(0,1,0,0,0,0,
    0,"None","Gold")

  //Winning Points Cards
  val mansion = new BasicCards(2,0,1,0,0,0,
    0,"None","Mansion")
  val duchy = new BasicCards(5,0,3,0,0,0,
    0,"None", "Duchy")
  val province = new BasicCards(8,0,6,0,0,0,
    0,"None", "Province")
}