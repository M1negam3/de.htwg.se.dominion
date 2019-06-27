package de.htwg.se.dominion.model.deckComponent.cardComponent

import de.htwg.se.dominion.model.deckComponent._

import scala.util.Random

case class Cards(CostValue : Int = 0, MoneyValue : Int = 0, WpValue : Int = 0, ActionValue : Int = 0, BuyAdditionValue : Int = 0,
                 BonusMoneyValue : Int = 0, DrawingValue : Int = 0, EffectValue : String = "", CardName : String = "", Type : String = "") {
}

object Cards {
  //Moneycards
  val copper = new Cards(0,1,0,0,0,0,
    0,"1 Money","Copper", "Money")
  val silver = new Cards(3,2,0,0,0,0,
    0,"2 Money","Silver", "Money")
  val gold = new Cards(6,3,0,0,0,0,
    0,"3 Money","Gold", "Money")

  //Winning Points Cards
  val estate = new Cards(2,0,1,0,0,0,
    0,"1 Winning Point","Estate", "WinningPoint")
  val duchy = new Cards(5,0,3,0,0,0,
    0,"3 Winning Point", "Duchy","WinningPoint")
  val province = new Cards(8,0,6,0,0,0,
    0,"5 Winning Point", "Province","WinningPoint")

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

  // Playing Deck
  val playingDeck: List[List[Cards]] = List(copperHeadDeck.copperDeck, silverHeadDeck.silverDeck, goldHeadDeck.goldDeck,
    estateHeadDeck.estateDeck, duchyHeadDeck.duchyDeck, provinceHeadDeck.provinceDeck, cellarHeadDeck.cellarDeck,
    villageHeadDeck.villageDeck, merchantHeadDeck.merchantDeck, workshopHeadDeck.workshopDeck, smithyHeadDeck.smithyDeck,
    remodelHeadDeck.remodelDeck, gardensHeadDeck.gardensDeck, festivalHeadDeck.festivalDeck, mineHeadDeck.mineDeck,
    marketHeadDeck.marketDeck)

  // Starting Decks
  val startDeck : List[Cards] = List(silver, silver, silver, silver, silver, silver, silver, merchant, merchant, merchant)
  val hand : List[Cards] = List()
  val stacker : List[Cards] = Nil


  def shuffle(list: List[Cards]): List[Cards] = {
    val random = new Random
    val shuffledList: List[Cards] = random.shuffle(list)
    shuffledList
  }
}

object copperHeadDeck extends createCopperHeadDeck {

  val copperDeck: List[Cards] = createDeck
}

object silverHeadDeck extends createSilverHeadDeck {

  val silverDeck: List[Cards] = createDeck
}

object goldHeadDeck extends createGoldHeadDeck {

  val goldDeck: List[Cards] = createDeck
}

object estateHeadDeck extends createEstateHeadDeck {

  val estateDeck: List[Cards] = createDeck
}

object duchyHeadDeck extends createDuchyHeadDeck {

  val duchyDeck: List[Cards] = createDeck

}

object provinceHeadDeck extends createProvinceHeadDeck {

  val provinceDeck: List[Cards] = createDeck

}

object villageHeadDeck extends createVillageHeadDeck {

  val villageDeck: List[Cards] = createDeck

}

object festivalHeadDeck extends createFestivalHeadDeck {

  val festivalDeck: List[Cards] = createDeck

}

object cellarHeadDeck extends createCellarHeadDeck {

  val cellarDeck: List[Cards] = createDeck

}

object mineHeadDeck extends createMineHeadDeck {

  val mineDeck: List[Cards] = createDeck

}

object smithyHeadDeck extends createSmithyHeadDeck {

  val smithyDeck: List[Cards] = createDeck

}

object remodelHeadDeck extends createRemodelHeadDeck {

  val remodelDeck: List[Cards] = createDeck

}

object merchantHeadDeck extends createMerchantHeadDeck {

  val merchantDeck: List[Cards] = createDeck

}

object workshopHeadDeck extends createWorkshopHeadDeck {

  val workshopDeck: List[Cards] = createDeck

}

object gardensHeadDeck extends createGardenHeadDeck {

  val gardensDeck: List[Cards] = createDeck

}

object marketHeadDeck extends createMarketHeadDeck {

  val marketDeck: List[Cards] = createDeck

}