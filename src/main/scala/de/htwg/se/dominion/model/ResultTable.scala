package de.htwg.se.dominion.model

case class ResultTable(players: List[Player],points: List[Int]) {
  override  def toString: String ={
    for(i <- 0 until players.length) {
      return "\n Player " + players(i).value + "'(s) Points are: " + points(i)
    }
  }
}
