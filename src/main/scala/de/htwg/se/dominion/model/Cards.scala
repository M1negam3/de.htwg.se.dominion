package de.htwg.se.dominion.model

import scala.collection.mutable.ListBuffer
import scala.util.Random

case class Cards(CostValue : Int, MoneyValue : Int, WpValue : Int, ActionValue : Int, BuyAdditionValue : Int,
                 BonusMoneyValue : Int, DrawingValue : Int, EffectValue : String, CardName : String, Type : String) {
}

object Cards{
  // Moneycards
  val copper = new Cards(0,1,0,0,0,0,
    0,"nothing","Copper", "Money")
  val silver = new Cards(3,2,0,0,0,0,
    0,"nothing","Silver", "Money")
  val gold = new Cards(6,3,0,0,0,0,
    0,"nothing","Gold", "Money")

  //Winning Points Cards
  val mansion = new Cards(2,0,1,0,0,0,
    0,"nothing","Mansion", "Money")
  val duchy = new Cards(5,0,3,0,0,0,
    0,"None", "Duchy","Money")
  val province = new Cards(8,0,6,0,0,0,
    0,"None", "Province","Money")

  //Deck
  val village = new Cards(3,0,0,2,0,0,
    1,"None","Village","Action")
  val festival = new Cards(5,0,0,2,1,2,
    0,"None","Festival", "Action")
  val cellar = new Cards(2,0,0,1,0,0,
    0,"Discard any number of cards, then draw that many", "Cellar","Action")
  val mine = new Cards(5,0,0,0,0,0,
    0,"You may trash a Treasure from your hand. Gain a Treasure to your hand costing up to 3 more than it.",
    "Mine", "Action")
  val smithy = new Cards(4,0,0,0,0,0,
    3,"None","Smithy", "Action")
  val remodel = new Cards(4,0,0,0,0,0,
    0,"Trash a card from your hand. Gain a card costing up to 2 more than it.","Remodel", "Action")
  val merchant = new Cards(3,0,0,1,0,0,
    1,"The first time you play a Silver this turn, +1", "Merchant", "Action")
  val workshop = new Cards(3,0,0,0,0,0,
    0,"Gain a card costing up to 4.", "Workshop", "Action")
  val gardens = new Cards(4,0,0,0,0,0,
    0,"Worth 1 WinningPonit per 10 cards you have(round down)","Gardens", "Action")
  val market = new Cards(5,0,0,1,1,1,
    1,"None","Market","Action")

  // Starting Decks
  var startDeck : List[Cards] = List(village,village,copper,copper,copper)
  var hand : List[Cards] = Nil
  var stacker : List[Cards] = Nil

  val copperDeck: List[Cards] = List.fill(100)(copper)
  val silverDeck: List[Cards] = List.fill(100)(silver)
  val goldDeck: List[Cards] = List.fill(100)(gold)

  val mansionDeck: List[Cards] = List.fill(12)(mansion)
  val duchyDeck: List[Cards] = List.fill(12)(duchy)
  val provinceDeck: List[Cards] = List.fill(12)(province)

  val villageDeck: List[Cards] = List.fill(1)(village)
  val festivalDeck: List[Cards] = List.fill(10)(festival)
  val cellarDeck: List[Cards] = List.fill(10)(cellar)
  val mineDeck: List[Cards] = List.fill(10)(mine)
  val smithyDeck: List[Cards] = List.fill(10)(smithy)
  val remodelDeck: List[Cards] = List.fill(10)(remodel)
  val merchantDeck: List[Cards] = List.fill(10)(merchant)
  val workshopDeck: List[Cards] = List.fill(10)(workshop)
  val gardensDeck: List[Cards] = List.fill(10)(gardens)
  val marketDeck: List[Cards] = List.fill(10)(market)

  val playingDeck: List[List[Cards]] = List(copperDeck, silverDeck, goldDeck, mansionDeck, duchyDeck, provinceDeck,
    villageDeck, festivalDeck, cellarDeck, mineDeck, smithyDeck, remodelDeck, merchantDeck, workshopDeck, gardensDeck,
    marketDeck)

  def shuffle(list: List[Cards]): List[Cards] = {
    val random = new Random
    val shuffledList: List[Cards] = random.shuffle(list)
    shuffledList
  }
}