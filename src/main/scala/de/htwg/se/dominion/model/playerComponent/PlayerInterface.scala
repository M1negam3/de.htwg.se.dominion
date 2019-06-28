package de.htwg.se.dominion.model.playerComponent

import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.{Card}


trait PlayerInterface {

  def getName: String

  def getValue: Int

  def getDeck: List[Card]

  def getStacker: List[Card]

  def getHand: List[Card]

  def getPlayingCards: List[Card]

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

  def upgrading(player: PlayerInterface, i : Integer, z: List[Card]): PlayerInterface

  def isEmpty(player: PlayerInterface): PlayerInterface

  def updateMoney(player: PlayerInterface,i: Int): PlayerInterface

  def updateAction(player: PlayerInterface,i: Int): PlayerInterface

}