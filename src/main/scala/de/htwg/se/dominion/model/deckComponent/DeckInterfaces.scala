package de.htwg.se.dominion.model.deckComponent

import de.htwg.se.dominion.model.HeadDeckInterface
import de.htwg.se.dominion.model.deckComponent.cardComponent.Cards

trait createCopperHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(100)(Cards.copper)
    deck
  }
}

trait createSilverHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(100)(Cards.silver)
    deck
  }
}

trait createGoldHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(100)(Cards.gold)
    deck
  }
}

trait createEstateHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(12)(Cards.estate)
    deck
  }
}

trait createDuchyHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(12)(Cards.duchy)
    deck
  }
}

trait createProvinceHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(12)(Cards.province)
    deck
  }
}


trait createVillageHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.village)
    deck
  }
}

trait createFestivalHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.festival)
    deck
  }
}

trait createCellarHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.cellar)
    deck
  }
}

trait createMineHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.mine)
    deck
  }
}

trait createSmithyHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.smithy)
    deck
  }
}

trait createRemodelHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.remodel)
    deck
  }
}

trait createMerchantHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.merchant)
    deck
  }
}

trait createWorkshopHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.workshop)
    deck
  }
}

trait createGardenHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.gardens)
    deck
  }
}

trait createMarketHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(10)(Cards.market)
    deck
  }
}

trait createFillHeadDeck extends HeadDeckInterface {

  override def createDeck: List[Cards] = {
    val deck = List.fill(1)(Cards.fill)
    deck
  }
}