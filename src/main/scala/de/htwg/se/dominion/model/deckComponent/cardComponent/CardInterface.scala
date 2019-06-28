package de.htwg.se.dominion.model.deckComponent.cardComponent

trait CardInterface {

  def getCostValue: Int

  def getMoneyValue: Int

  def getWpValue: Int

  def getActionValue: Int

  def getBuyAdditionValue: Int

  def getBonusMoneyValue: Int

  def getDrawingValue: Int

  def getEffectValue: String

  def getCardName: String

  def getType: String

}

trait StaticCardInterface {

  def shuffle(list: List[CardInterface]): List[CardInterface]

}