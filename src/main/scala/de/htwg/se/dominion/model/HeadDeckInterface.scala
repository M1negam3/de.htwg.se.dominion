package de.htwg.se.dominion.model

import de.htwg.se.dominion.model.deckComponent.cardComponent.Cards

trait HeadDeckInterface {

  def createDeck: List[Cards]

}