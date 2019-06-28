package de.htwg.se.dominion.model.playerComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player

trait PlayerInterface {

  def createPlayer(pCount: Int, names: List[String]): List[Player]

  def getHand(player: Player): Player

  def getMoney(player: Player): Int

  def updatePlayer(list: List[Player], player: Player): List[Player]

  def draw(player: Player, n: Integer): Player

  def upgrading(player: Player, i : Integer, z: List[Cards]): Player

  def isEmpty(player: Player): Player

  def updateMoney(player: Player,i: Int): Player

  def updateAction(player: Player,i: Int): Player

}
