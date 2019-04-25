package de.htwg.se.dominion.model

case class BasicCards(CostValue : Int, MoneyValue : Int, WpValue : Int, ActionValue : Int, BuyAdditionValue : Int,
                BonusMoneyValue : Int, DrawingValue : Int, EffectValue : String, CardName : String) {
}
object BasicCards{
  // Moneycards
  val copper = BasicCards(0,1,0,0,0,0,0,"nothing","Copper")
  val silver = BasicCards(0,1,0,0,0,0,0,"nothing","Silver")
  val gold = BasicCards(0,1,0,0,0,0,0,"nothing","Gold")

  //Winning Points Cards
  val mansion = BasicCards(2,0,1,0,0,0,0,"nothing","Mansion")
  val Duchy = BasicCards(5,0,3,0,0,0,0,"None", "Duchy")
  val Province =  BasicCards(8,0,6,0,0,0,0,"None", "Province")
}