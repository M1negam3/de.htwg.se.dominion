package de.htwg.se.dominion.model.deckComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Card

trait HeadDeckInterface {

  def createDeck: List[Card]

}
