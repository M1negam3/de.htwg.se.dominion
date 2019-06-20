package de.htwg.se.dominion.model

import scala.collection.mutable.ListBuffer
import scala.util.Random

case class Cards(CostValue : Int, MoneyValue : Int, WpValue : Int, ActionValue : Int, BuyAdditionValue : Int,
                 BonusMoneyValue : Int, DrawingValue : Int, EffectValue : String, CardName : String, Type : String) {
}

object Cards{
  //Moneycards
  val copper = new Cards(0,1,0,0,0,0,
    0,"1 Money","Copper", "Money")
  val silver = new Cards(3,2,0,0,0,0,
    0,"2 Money","Silver", "Money")
  val gold = new Cards(6,3,0,0,0,0,
    0,"3 Money","Gold", "Money")

  //Winning Points Cards
  val mansion = new Cards(2,0,1,0,0,0,
    0,"1 Winning Point","Mansion", "Money")
  val duchy = new Cards(5,0,3,0,0,0,
    0,"3 Winning Point", "Duchy","Money")
  val province = new Cards(8,0,6,0,0,0,
    0,"5 Winning Point", "Province","Money")

  //Deck
  val village = new Cards(3,0,0,2,0,0,
    1,"+1 Card, +2 Actions","Village","Action")
  val festival = new Cards(5,0,0,2,1,2,
    0,"+2 Actions, +1 Buy, +2 Money","Festival", "Action")
  val cellar = new Cards(2,0,0,1,0,0,
    0,"+1 Action, Discard any number of cards, then draw that many", "Cellar","Action")
  val mine = new Cards(5,0,0,0,0,0,
    0,"You may trash a Treasure from your hand. Gain a Treasure to your hand costing up to 3 more than it.",
    "Mine", "Action")
  val smithy = new Cards(4,0,0,0,0,0,
    3,"+3 Cards","Smithy", "Action")
  val remodel = new Cards(4,0,0,0,0,0,
    0,"Trash a card from your hand. Gain a card costing up to 2 more than it.","Remodel", "Action")
  val merchant = new Cards(3,0,0,1,0,0,
    1,"+1 Card, +1 Action, The first time you play a Silver this turn, +1 Money", "Merchant", "Action")
  val workshop = new Cards(3,0,0,0,0,0,
    0,"Gain a card costing up to 4.", "Workshop", "Action")
  val gardens = new Cards(4,0,0,0,0,0,
    0,"Worth 1 WinningPoint per 10 cards you have(round down)","Gardens", "Action")
  val market = new Cards(5,0,0,1,1,1,
    1,"+1 Card, +1 Action, +1 Buy, +1 Money","Market","Action")

  val copperDeck: List[Cards] = List.fill(100)(copper)
  val silverDeck: List[Cards] = List.fill(100)(silver)
  val goldDeck: List[Cards] = List.fill(100)(gold)
  val mansionDeck: List[Cards] = List.fill(12)(mansion)
  val duchyDeck: List[Cards] = List.fill(12)(duchy)
  val provinceDeck: List[Cards] = List.fill(12)(province)
  val villageDeck: List[Cards] = List.fill(1)(village)
  val festivalDeck: List[Cards] = List.fill(1)(festival)
  val cellarDeck: List[Cards] = List.fill(1)(cellar)
  val mineDeck: List[Cards] = List.fill(1)(mine)
  val smithyDeck: List[Cards] = List.fill(1)(smithy)
  val remodelDeck: List[Cards] = List.fill(1)(remodel)
  val merchantDeck: List[Cards] = List.fill(1)(merchant)
  val workshopDeck: List[Cards] = List.fill(1)(workshop)
  val gardensDeck: List[Cards] = List.fill(1)(gardens)
  val marketDeck: List[Cards] = List.fill(1)(market)
  val playingDeck: List[List[Cards]] = List(copperDeck, silverDeck, goldDeck, provinceDeck, mansionDeck, duchyDeck,
    villageDeck, festivalDeck, cellarDeck, mineDeck, smithyDeck, remodelDeck, merchantDeck, workshopDeck, gardensDeck,
    marketDeck)

  // Starting Decks
  val startDeck : List[Cards] = List(copper, copper, copper, copper, copper, copper, copper, cellar, cellar, cellar)
  //var startDeck : List[Cards] = List(village, village, copper, copper, copper)
  var hand : List[Cards] = Nil
  var stacker : List[Cards] = Nil

  def shuffle(list: List[Cards]): List[Cards] = {
    val random = new Random
    val shuffledList: List[Cards] = random.shuffle(list)
    shuffledList
  }
}