package de.htwg.se.dominion.model.playerComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player
trait PlayerInterface {
  def getName: String
  def getValue: Int
  def getDeck: List[Cards]
  def getStacker: List[Cards]
  def getHand: List[Cards]
  def getPlayingCards: List[Cards]
  def getActions: Int
  def getBuys: Int
  def getStringValue: Int
  def getMoney: Int
}
trait StaticPlayerInterface {

  def createPlayer(pCount: Int, names: List[String]): List[PlayerInterface]

  def getHand(player: PlayerInterface): PlayerInterface

  def getMoney(player: PlayerInterface): Int

  def updatePlayer(list: List[PlayerInterface], player: PlayerInterface): List[PlayerInterface]

  def draw(player: PlayerInterface, n: Integer): PlayerInterface

  def upgrading(player: PlayerInterface, i : Integer, z: List[Cards]): PlayerInterface

  def isEmpty(player: PlayerInterface): PlayerInterface

  def updateMoney(player: PlayerInterface,i: Int): PlayerInterface

  def updateAction(player: PlayerInterface,i: Int): PlayerInterface

}
