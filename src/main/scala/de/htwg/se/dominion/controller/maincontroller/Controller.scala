package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.controller.ControllerInterface
import de.htwg.se.dominion.model._
import de.htwg.se.dominion.util._

class Controller(r: RoundManager) extends ControllerInterface {
  var pCount = 0
  var playerTurn = 0
  var players: List[Player] = Nil
  var cPlayers: List[Player] = Nil
  var phaseString = Output.printHeader()
  var state = "Init"
  val undoManager = new UndoManager
  var roundmanager = r

  override def newGame(): Unit = {
    roundmanager = roundmanager.getNumberOfPlayers(roundmanager)
    roundmanager = roundmanager.getNames(roundmanager)
    roundmanager = roundmanager.createPlayer(roundmanager)
    phaseString = Output.printPrep()
    state = "turn"
    notifyObservers
  }

  /*def newGame(): Unit = {
    pCount = GameInit.getPlayerCount()
    val names = GameInit.getPlayerName(pCount)
    players = Player.createPlayer(pCount, names)
    phaseString = Output.printPrep()
    cPlayers = players
    state = "turn"
    notifyObservers
  }*/

  /*override def turn(): Unit = {
    roundmanager = roundmanager.playerTurn(roundmanager)
    undoManager.doStep(new turnCommand(roundmanager, roundmanager.idx,this))
    notifyObservers
    roundmanager = roundmanager.playerTurn(RoundManager(roundmanager.players, roundmanager.numberOfRounds, roundmanager.numberOfPlayers, roundmanager.names, roundmanager.score, roundmanager.idx + 1))
    roundmanager = roundmanager.roundNumber(roundmanager)
  }*/

  override def turn(): Unit = {
  playerTurn = GameTurn.round(pCount, playerTurn)
  phaseString = Output.printActionPhase() + Output.printTurn(playerTurn)
  notifyObservers
  cPlayers = GameTurn.actionPhase(cPlayers, playerTurn)
  phaseString = Output.printBuyPhase()
  notifyObservers
  cPlayers = GameTurn.buyPhase(cPlayers, playerTurn)
  phaseString = Output.printTurnEnd(playerTurn) + GameTurn.endCheck(GameTurn.end)
  playerTurn += 1
  if (GameTurn.end) {
    state = "end"
  }
  notifyObservers
 }

  /*override def turn(): Unit = {
    roundmanager = roundmanager.playerTurn(roundmanager)
    println("TEST " + roundmanager.players(roundmanager.idx).deck)
    println("TEST " + roundmanager.players(roundmanager.idx).stacker)
    println("TEST " + roundmanager.players(roundmanager.idx).hand)
    roundmanager = roundmanager.actionPhase(roundmanager)
    println("TEST1 " + roundmanager.players(roundmanager.idx).deck)
    println("TEST1 " + roundmanager.players(roundmanager.idx).stacker)
    println("TEST1 " + roundmanager.players(roundmanager.idx).hand)
    roundmanager = roundmanager.buyPhase(roundmanager)
    println("TEST2 " + roundmanager.players(roundmanager.idx).deck)
    println("TEST2 " + roundmanager.players(roundmanager.idx).stacker)
    println("TEST2 " + roundmanager.players(roundmanager.idx).hand)
    roundmanager = roundmanager.playerTurn(RoundManager(roundmanager.players, roundmanager.numberOfRounds, roundmanager.numberOfPlayers, roundmanager.names, roundmanager.score, roundmanager.idx + 1))
    roundmanager = roundmanager.roundNumber(roundmanager)
    notifyObservers
  }*/

  override def help(): Unit = {
    phaseString = Output.printRules()
    notifyObservers
  }

  /* override def endGame(): Unit = {
    val fPlayers = GameEnd.end(cPlayers)
    val score = GameEnd.score(fPlayers)
    phaseString = Output.printScore(score)
    notifyObservers
  }*/

  override def endGame(): Unit = {
    notifyObservers
  }

  override def undo(): Unit ={
    undoManager.undoStep
    notifyObservers
  }

  override def redo(): Unit = {
    undoManager.redoStep
    notifyObservers
  }
}