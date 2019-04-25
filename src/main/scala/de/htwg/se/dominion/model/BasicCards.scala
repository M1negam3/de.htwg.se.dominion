package de.htwg.se.dominion.model

case class BasicCards(CostValue : Int, MoneyValue : Int, WpValue : Int, ActionValue : Int, BuyAdditionValue : Int,
                BonusMoneyValue : Int, DrawingValue : Int, EffectValue : String, CardName : String) {
}
object BasicCards{
  val copper = BasicCards(0,1,0,0,0,0,0,"nothing","copper")
  val mansion = BasicCards(2,0,1,0,0,0,0,"nothing","mansion")
}