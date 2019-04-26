package de.htwg.se.dominion.model

case class Player(name : String) {
  def this(value: Int) = this(f"Player $value")

  override def toString: String = this.name
}