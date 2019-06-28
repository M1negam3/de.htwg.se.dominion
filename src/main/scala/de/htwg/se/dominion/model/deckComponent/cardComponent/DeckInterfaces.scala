package de.htwg.se.dominion.model.deckComponent.cardComponent

import de.htwg.se.dominion.model.deckComponent.HeadDeckInterface
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Card

trait createCopperHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Card] = {
    val deck = List.fill(100)(Card.copper)
    deck
  }
}

trait createSilverHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Card] = {
    val deck = List.fill(100)(Card.silver)
    deck
  }
}

trait createGoldHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Card] = {
    val deck = List.fill(100)(Card.gold)
    deck
  }
}

trait createEstateHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Card] = {
    val deck = List.fill(12)(Card.estate)
    deck
  }
}

trait createDuchyHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Card] = {
    val deck = List.fill(12)(Card.duchy)
    deck
  }
}

trait createProvinceHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Card] = {
    val deck = List.fill(12)(Card.province)
    deck
  }
}


trait createVillageHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Card] = {
    val deck = List.fill(10)(Card.village)
    deck
  }
}

trait createFestivalHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Card] = {
    val deck = List.fill(10)(Card.festival)
    deck
  }
}

trait createCellarHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Card] = {
    val deck = List.fill(10)(Card.cellar)
    deck
  }
}

trait createMineHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Card] = {
    val deck = List.fill(10)(Card.mine)
    deck
  }
}

trait createSmithyHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Card] = {
    val deck = List.fill(10)(Card.smithy)
    deck
  }
}

trait createRemodelHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Card] = {
    val deck = List.fill(10)(Card.remodel)
    deck
  }
}

trait createMerchantHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Card] = {
    val deck = List.fill(10)(Card.merchant)
    deck
  }
}

trait createWorkshopHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Card] = {
    val deck = List.fill(10)(Card.workshop)
    deck
  }
}

trait createGardenHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Card] = {
    val deck = List.fill(10)(Card.gardens)
    deck
  }
}

trait createMarketHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Card] = {
    val deck = List.fill(10)(Card.market)
    deck
  }
}