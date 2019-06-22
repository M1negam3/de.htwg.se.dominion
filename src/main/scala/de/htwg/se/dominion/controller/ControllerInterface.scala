package de.htwg.se.dominion.controller

import de.htwg.se.dominion.util.Observable

trait ControllerInterface extends Observable{

  def undo(): Unit

  def redo(): Unit
}