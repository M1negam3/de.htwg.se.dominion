package de.htwg.se.dominion.controller


import de.htwg.se.dominion.controller.maincontroller.GameStatus.GameStatus
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.util.{Observable, UndoManager}

trait ControllerInterface extends Observable{

  def eval(input: String): Unit

  def undo(): Unit

  def redo(): Unit

  def getCurrentStateAsString: String

  def controllerStateAsString: String

  def getCurrentPlayerturn: Int

  def getCurrentActions: Int

  def getCurrentBuys: Int

  def getCurrentMoney: Int

  def getPlayerName: String

  def getCurrentDeck: List[Cards]

  def getCurrentStacker: List[Cards]

  def getCurrentHand: List[Cards]

  def getCurrentPlayingDecks: List[List[Cards]]

  def getCurrentPhase: Boolean

  def getCurrentPhaseAsString(b: Boolean): String

  def getCurrentStringValue: Int

  def getCurrentScore: List[(Int, String)]
}