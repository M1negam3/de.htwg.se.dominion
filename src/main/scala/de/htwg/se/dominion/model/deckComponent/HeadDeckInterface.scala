package de.htwg.se.dominion.model.deckComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards

trait HeadDeckInterface {

  def createDeck: List[Cards]

}
