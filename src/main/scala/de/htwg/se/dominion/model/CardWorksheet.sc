import java.util

// Card related
var CostValue = Array (0, 1, 2, 3, 4, 5, 6, 7, 8)
var MoneyValue = Array(1, 2, 3)
var WpValue = Array(1, 3, 6)
var ActionValue = Array(0, 1, 2)
var BuyadditionValue = Array(0, 1, 2)
var BonusMoneyValue = Array(0, 1, 2)
var CardName = Array("Copper", "Silver", "Gold", "Mansion", "Duchy", "Province")
var DrawingValue = Array(0, 1, 2, 3)
var EffectValue = Array("Action", "Buy", "Card", "None")


case class Card(CostValue : Int, MoneyValue : Int, WpValue : Int, ActionValue : Int, BuyAdditionValue : Int,
                BonusMoneyValue : Int, DrawingValue : Int, EffectValue : String, CardName : String) {
}

val MoneyCard1 = Card(0, 1, 0, 0, 0, 0, 0, "None", "Copper")
val MoneyCard2 = Card(3, 2, 0, 0, 0, 0, 0, "None", "Silver")
val MoneyCard3 = Card(6, 3, 0, 0, 0, 0, 0, "None", "Gold")

MoneyCard1.CostValue
MoneyCard1.MoneyValue

val WinningPointCard1 =  Card(2,0,1,0,0,0,0,"None", "Mansion")
val WinningPointCard2 =  Card(5,0,3,0,0,0,0,"None", "Duchy")
val WinningPointCard3 =  Card(8,0,6,0,0,0,0,"None", "Province")

WinningPointCard1.WpValue
//--------------------------------------------------------------------------------------------------------------
// Deck related
val Deck = new util.Stack()

//--------------------------------------------------------------------------------------------------------------