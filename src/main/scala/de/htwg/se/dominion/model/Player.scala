package de.htwg.se.dominion.model

case class Player(value : Int, name : String, deck : List[BasicCards], hand : List[BasicCards]) {
  override def toString: String = this.name
}
object Player {
  val player1: List[BasicCards] = List()
  val player2: List[BasicCards] = List()
  val player3: List[BasicCards] = List()
  val player4: List[BasicCards] = List()
  val player5: List[BasicCards] = List()

}