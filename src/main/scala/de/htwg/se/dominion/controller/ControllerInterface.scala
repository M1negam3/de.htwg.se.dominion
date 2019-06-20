package de.htwg.se.dominion.controller

import de.htwg.se.dominion.util.Observable

trait ControllerInterface extends Observable{

  def newGame(): Unit

  def turn(): Unit

  def help(): Unit

  def endGame(): Unit

  def undo(): Unit

  def redo(): Unit
}