package de.htwg.se.dominion.model

import de.htwg.se.dominion.model.deckComponent.Cards

trait DeckInterface {

  def createDeck: List[Cards]

}