package de.htwg.se.dominion.util

trait Command {

  def doStep():Unit
  def undoStep():Unit
  def redoStep():Unit

}
