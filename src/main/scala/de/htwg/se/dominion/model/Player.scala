package de.htwg.se.dominion.model

case class Player(value : Int, name : String, deck : List[BasicCards], hand : List[BasicCards]) {
  override def toString: String = this.name
}
