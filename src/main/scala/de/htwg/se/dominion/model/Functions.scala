package de.htwg.se.dominion.model

object Functions {

  def HandMoney(l: List[BasicCards]): Integer = {
    return l.head.MoneyValue + l(1).MoneyValue + l(2).MoneyValue + l(3).MoneyValue + l(4).MoneyValue +
          l.head.BonusMoneyValue + l(1).BonusMoneyValue + l(2).BonusMoneyValue + l(3).BonusMoneyValue +
          l(4).BonusMoneyValue
  }

  def HandActions(l: List[BasicCards]): Integer = {
    return l.head.ActionValue + l(1).ActionValue + l(2).ActionValue + l(3).ActionValue + l(4).ActionValue
  }

  def HandAdditionalBuys(l: List[BasicCards]): Integer = {
    return l.head.BuyAdditionValue + l(1).BuyAdditionValue + l(2).BuyAdditionValue + l(3).BuyAdditionValue +
          l(4).BuyAdditionValue
  }

  def Player (pCount: Integer, names: List[String], deck: List[BasicCards], hand: List[BasicCards]) : Player = {
      val player = new Player(pCount, names(pCount - 1), deck, hand)
    player
  }
}