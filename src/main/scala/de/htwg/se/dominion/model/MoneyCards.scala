package de.htwg.se.dominion.model

case class Card(BuyValue : Int, MoneyValue : Int, WpValue : Int, ActionValue : Int, BuyadditionValue : Int,
                  BonusMoneyValue : Int, DrawingValue :Int, EffectValue : String, CardName : String) {
  override def toString:String = EffectValue
}
