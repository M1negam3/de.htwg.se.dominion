var BuyValues = Array (0, 1, 2, 3, 4, 5, 6, 7, 8)
var MoneyValues = Array(1, 2, 3)
var WpValues = Array(1, 3, 6)
var ActionValues = Array (0, 1, 2)
var BuyAdditionValues = Array (0, 1, 2)
var BonusMoneyValues = Array (0, 1, 2)
var EffectValue = Array ("Still to add")
var DrawingValues = Array(0, 1, 2, 3)
var Effect = Array ("Action", "Buy", "Card", "None")
var Hand = new Array[String](5)
var handful = Array("0", "1", "2", "3", "4", "5")

case class Card(BuyValue : Int, MoneyValue : Int, WpValue : Int, ActionValue : Int, BuyAdditionValue : Int,
                BonusMoneyValue : Int, DrawingValue :Int, EffectValue : String) {
}

val MoneyCard1 = Card(0, 1, 0, 0, 0, 0, 0, "None")
val MoneyCard2 = Card(3, 2, 0, 0, 0, 0, 0, "None")
val MoneyCard3 = Card(6, 3, 0, 0, 0, 0, 0, "None")

MoneyCard1.BuyValue
MoneyCard1.MoneyValue

val WinningPointCard1 =  Card(2,0,1,0,0,0,0,"Nothing")
val WinningPointCard2 =  Card(5,0,3,0,0,0,0,"Nothing")
val WinningPointCard3 =  Card(8,0,6,0,0,0,0,"Nothing")

WinningPointCard1.WpValue