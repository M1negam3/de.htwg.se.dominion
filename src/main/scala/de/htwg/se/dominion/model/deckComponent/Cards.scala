package de.htwg.se.dominion.model.deckComponent

import de.htwg.se.dominion.model.DeckInterface

import scala.util.Random

case class Cards(CostValue : Int, MoneyValue : Int, WpValue : Int, ActionValue : Int, BuyAdditionValue : Int,
                 BonusMoneyValue : Int, DrawingValue : Int, EffectValue : String, CardName : String, Type : String) {
}

trait createCopperDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(100)(Cards.copper)
    deck
  }
}

trait createSilverDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(100)(Cards.silver)
    deck
  }
}

trait createGoldDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(100)(Cards.gold)
    deck
  }
}

trait createEstateDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(12)(Cards.estate)
    deck
  }
}

trait createDuchyDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(12)(Cards.duchy)
    deck
  }
}

trait createProvinceDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(12)(Cards.province)
    deck
  }
}


trait createVillageDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(1)(Cards.village)
    deck
  }
}

trait createFestivalDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(1)(Cards.festival)
    deck
  }
}

trait createCellarDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(1)(Cards.cellar)
    deck
  }
}

trait createMineDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(1)(Cards.mine)
    deck
  }
}

trait createSmithyDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(1)(Cards.smithy)
    deck
  }
}

trait createRemodelDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(1)(Cards.remodel)
    deck
  }
}

trait createMerchantDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(1)(Cards.merchant)
    deck
  }
}

trait createWorkshopDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(1)(Cards.workshop)
    deck
  }
}

trait createGardenDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(1)(Cards.gardens)
    deck
  }
}

trait createMarketDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(1)(Cards.market)
    deck
  }
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
  val playingDeck: List[List[Cards]] = List(copperDeck.copperDeck, silverDeck.silverDeck, goldDeck.goldDeck,
    estateDeck.estateDeck, provinceDeck.provinceDeck, duchyDeck.duchyDeck, villageDeck.villageDeck, festivalDeck.festivalDeck,
    cellarDeck.cellarDeck, mineDeck.mineDeck, smithyDeck.createDeck, remodelDeck.remodelDeck, merchantDeck.merchantDeck,
    workshopDeck.workshopDeck, gardensDeck.gardensDeck, marketDeck.marketDeck)

  // Starting Decks
  val startDeck : List[Cards] = List(copper, copper, copper, copper, copper, copper, copper, estate, estate, estate)
  val hand : List[Cards] = Nil
  val stacker : List[Cards] = Nil


  def shuffle(list: List[Cards]): List[Cards] = {
    val random = new Random
    val shuffledList: List[Cards] = random.shuffle(list)
    shuffledList
  }
}

object copperDeck extends createCopperDeck {
  val copperDeck: List[Cards] = createDeck
}

object silverDeck extends createSilverDeck {
  val silverDeck: List[Cards] = createDeck
}

object goldDeck extends createGoldDeck {
  val goldDeck: List[Cards] = createDeck
}

object estateDeck extends createEstateDeck {
  val estateDeck: List[Cards] = createDeck
}

object duchyDeck extends createDuchyDeck {

  val duchyDeck: List[Cards] = createDeck

}

object provinceDeck extends createProvinceDeck {

  val provinceDeck: List[Cards] = createDeck

}

object villageDeck extends createVillageDeck {

  val villageDeck: List[Cards] = createDeck

}

object festivalDeck extends createFestivalDeck {

  val festivalDeck: List[Cards] = createDeck

}

object cellarDeck extends createCellarDeck {

  val cellarDeck: List[Cards] = createDeck

}

object mineDeck extends createMineDeck {

  val mineDeck: List[Cards] = createDeck

}

object smithyDeck extends createSmithyDeck {

  val smithyDeck: List[Cards] = createDeck

}

object remodelDeck extends createRemodelDeck {

  val remodelDeck: List[Cards] = createDeck

}

object merchantDeck extends createMerchantDeck {

  val merchantDeck: List[Cards] = createDeck

}

object workshopDeck extends createWorkshopDeck {

  val workshopDeck: List[Cards] = createDeck

}

object gardensDeck extends createGardenDeck {

  val gardensDeck: List[Cards] = createDeck

}

object marketDeck extends createMarketDeck {

  val marketDeck: List[Cards] = createDeck

}