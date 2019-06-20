package de.htwg.se.dominion.model

trait DeckInterface {

  def createDeck: List[Cards]

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

trait createMansionDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(12)(Cards.mansion)
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
    val deck = List.fill(10)(Cards.village)
    deck
  }
}

trait createFestivalDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.festival)
    deck
  }
}

trait createCellarDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.cellar)
    deck
  }
}

trait createMineDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.mine)
    deck
  }
}

trait createSmithyDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.smithy)
    deck
  }
}

trait createRemodelDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.remodel)
    deck
  }
}

trait createMerchantDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.merchant)
    deck
  }
}

trait createWorkshopDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.workshop)
    deck
  }
}

trait createGardenDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.gardens)
    deck
  }
}

trait createMarketDeck extends DeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.market)
    deck
  }
}