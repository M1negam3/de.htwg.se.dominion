package de.htwg.se.dominion

case class Cards (BuyValue : Int, MoneyValue : Int,WpValue : Int, ActionValue : Int, BuyAdditionValue : Int,BonusMoneyValue : Int,DrawingValue :Int,EffectValue : String){
  val MoneyCard1 =  Cards(0,1,0,0,0,0,0,"Nothing")
  val MoneyCard2 =  Cards(3,2,0,0,0,0,0,"Nothing")
  val MoneyCard3 =  Cards(6,3,0,0,0,0,0,"Nothing")

  val WinningPointCard1 =  Cards(2,0,1,0,0,0,0,"Nothing")
  val WinningPointCard2 =  Cards(5,0,3,0,0,0,0,"Nothing")
  val WinningPointCard3 =  Cards(8,0,6,0,0,0,0,"Nothing")

  val FunfairCard =  Cards(5,0,0,2,1,2,0,"Nothing")
  val VillageCard = Cards(4,0,0,2,0,0,1,"Nothing")





}
