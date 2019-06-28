package de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent

import de.htwg.se.dominion.model.deckComponent._
import de.htwg.se.dominion.model.deckComponent.cardComponent.{createCellarHeadDeck, createCopperHeadDeck, createDuchyHeadDeck, createEstateHeadDeck, createFestivalHeadDeck, createGardenHeadDeck, createGoldHeadDeck, createMarketHeadDeck, createMerchantHeadDeck, createMineHeadDeck, createProvinceHeadDeck, createRemodelHeadDeck, createSilverHeadDeck, createSmithyHeadDeck, createVillageHeadDeck, createWorkshopHeadDeck}

import scala.util.Random

case class Card(CostValue : Int = 0, MoneyValue : Int = 0, WpValue : Int = 0, ActionValue : Int = 0,
                BuyAdditionValue : Int = 0, BonusMoneyValue : Int = 0, DrawingValue : Int = 0, EffectValue : String = "",
                CardName : String = "", Type : String = "") {
}

object Card {
  //Moneycards
  val copper = new Card(0,1,0,0,0,0,
    0,"1 Money","Copper", "Money")
  val silver = new Card(3,2,0,0,0,0,
    0,"2 Money","Silver", "Money")
  val gold = new Card(6,3,0,0,0,0,
    0,"3 Money","Gold", "Money")

  //Winning Points Card
  val estate = new Card(2,0,1,0,0,0,
    0,"1 Winning Point","Estate", "WinningPoint")
  val duchy = new Card(5,0,3,0,0,0,
    0,"3 Winning Point", "Duchy","WinningPoint")
  val province = new Card(8,0,6,0,0,0,
    0,"5 Winning Point", "Province","WinningPoint")

  //Deck
  val village = new Card(3,0,0,2,0,0,
    1,"+1 Card, +2 Actions","Village","Action")
  val festival = new Card(5,0,0,2,1,2,
    0,"+2 Actions, +1 Buy, +2 Money","Festival", "Action")
  val cellar = new Card(2,0,0,1,0,0,
    0,"+1 Action, Discard any number of cards, then draw that many", "Cellar","Action")
  val mine = new Card(5,0,0,0,0,0,
    0,"You may trash a Treasure from your hand. Gain a Treasure to your hand costing up to 3 more than it.",
    "Mine", "Action")
  val smithy = new Card(4,0,0,0,0,0,
    3,"+3 Card","Smithy", "Action")
  val remodel = new Card(4,0,0,0,0,0,
    0,"Trash a card from your hand. Gain a card costing up to 2 more than it.","Remodel", "Action")
  val merchant = new Card(3,0,0,1,0,0,
    1,"+1 Card, +1 Action, The first time you play a Silver this turn, +1 Money", "Merchant", "Action")
  val workshop = new Card(3,0,0,0,0,0,
    0,"Gain a card costing up to 4.", "Workshop", "Action")
  val gardens = new Card(4,0,0,0,0,0,
    0,"Worth 1 WinningPoint per 10 cards you have(round down)","Gardens", "Action")
  val market = new Card(5,0,0,1,1,1,
    1,"+1 Card, +1 Action, +1 Buy, +1 Money","Market","Action")

  // Playing Deck
  val playingDeck: List[List[Card]] = List(copperHeadDeck.copperDeck, silverHeadDeck.silverDeck, goldHeadDeck.goldDeck,
    estateHeadDeck.estateDeck, duchyHeadDeck.duchyDeck, provinceHeadDeck.provinceDeck, cellarHeadDeck.cellarDeck,
    villageHeadDeck.villageDeck, merchantHeadDeck.merchantDeck, workshopHeadDeck.workshopDeck, smithyHeadDeck.smithyDeck,
    remodelHeadDeck.remodelDeck, gardensHeadDeck.gardensDeck, festivalHeadDeck.festivalDeck, mineHeadDeck.mineDeck,
    marketHeadDeck.marketDeck)

  // Starting Decks
  val startDeck : List[Card] = List(copper, copper, copper, copper, copper, copper, copper, estate, estate, estate)
  val hand : List[Card] = List()
  val stacker : List[Card] = Nil


  def shuffle(list: List[Card]): List[Card] = {
    val random = new Random
    val shuffledList: List[Card] = random.shuffle(list)
    shuffledList
  }
}

object copperHeadDeck extends createCopperHeadDeck {

  val copperDeck: List[Card] = createDeck
}

object silverHeadDeck extends createSilverHeadDeck {

  val silverDeck: List[Card] = createDeck
}

object goldHeadDeck extends createGoldHeadDeck {

  val goldDeck: List[Card] = createDeck
}

object estateHeadDeck extends createEstateHeadDeck {

  val estateDeck: List[Card] = createDeck
}

object duchyHeadDeck extends createDuchyHeadDeck {

  val duchyDeck: List[Card] = createDeck

}

object provinceHeadDeck extends createProvinceHeadDeck {

  val provinceDeck: List[Card] = createDeck

}

object villageHeadDeck extends createVillageHeadDeck {

  val villageDeck: List[Card] = createDeck

}

object festivalHeadDeck extends createFestivalHeadDeck {

  val festivalDeck: List[Card] = createDeck

}

object cellarHeadDeck extends createCellarHeadDeck {

  val cellarDeck: List[Card] = createDeck

}

object mineHeadDeck extends createMineHeadDeck {

  val mineDeck: List[Card] = createDeck

}

object smithyHeadDeck extends createSmithyHeadDeck {

  val smithyDeck: List[Card] = createDeck

}

object remodelHeadDeck extends createRemodelHeadDeck {

  val remodelDeck: List[Card] = createDeck

}

object merchantHeadDeck extends createMerchantHeadDeck {

  val merchantDeck: List[Card] = createDeck

}

object workshopHeadDeck extends createWorkshopHeadDeck {

  val workshopDeck: List[Card] = createDeck

}

object gardensHeadDeck extends createGardenHeadDeck {

  val gardensDeck: List[Card] = createDeck

}

object marketHeadDeck extends createMarketHeadDeck {

  val marketDeck: List[Card] = createDeck

}