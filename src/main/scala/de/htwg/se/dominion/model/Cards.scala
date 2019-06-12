package de.htwg.se.dominion.model

import scala.collection.mutable.ListBuffer

case class Cards(CostValue : Int, MoneyValue : Int, WpValue : Int, ActionValue : Int, BuyAdditionValue : Int,
                 BonusMoneyValue : Int, DrawingValue : Int, EffectValue : String, CardName : String) {
}

object Cards{
  // Moneycards
  val copper = new Cards(0,1,0,0,0,0,
    0,"nothing","Copper")
  val silver = new Cards(0,1,0,0,0,0,
    0,"nothing","Silver")
  val gold = new Cards(0,1,0,0,0,0,
    0,"nothing","Gold")

  //Winning Points Cards
  val mansion = new Cards(2,0,1,0,0,0,
    0,"nothing","Mansion")
  val duchy = new Cards(5,0,3,0,0,0,
    0,"None", "Duchy")
  val province = new Cards(8,0,6,0,0,0,
    0,"None", "Province")

  //Deck
  val village = new Cards(3,0,0,2,0,0,
    1,"None","Village")
  val festival = new Cards(5,0,0,2,1,2,
    0,"None","Festival")
  val cellar = new Cards(2,0,0,1,0,0,
    0,"Discard any number of cards, then draw that many", "Cellar")
  val mine = new Cards(5,0,0,0,0,0,
    0,"You may trash a Treasure from your hand. Gain a Treasure to your hand costing up to 3 more than it.",
    "Mine")
  val smithy = new Cards(4,0,0,0,0,0,
    3,"None","Smithy")
  val remodel = new Cards(4,0,0,0,0,0,
    0,"Trash a card from your hand. Gain a card costing up to 2 more than it.","Remodel")
  val merchant = new Cards(3,0,0,1,0,0,
    1,"The first time you play a Silver this turn, +1", "Merchant")
  val workshop = new Cards(3,0,0,0,0,0,
    0,"Gain a card costing up to 4.", "Workshop")
  val gardens = new Cards(4,0,0,0,0,0,
    0,"Worth 1 WinningPonit per 10 cards you have(round down)","Gardens")
  val market = new Cards(5,0,0,1,1,1,
    1,"None","Market")

  // Starting Decks
  var startDeck : List[Cards] = List(copper, copper, copper, copper, copper, copper, copper, mansion, mansion, mansion)
  var hand : List[Cards] = Nil
  var stacker : List[Cards] = Nil

  def deckclone(list: List[Cards]): List[Cards] = {
    var l = new ListBuffer[Cards]
    for (i <- 0 until list.length) {
      l += list(i)
    }
    val clonedList: List[Cards] = l.toList
    clonedList
  }
}