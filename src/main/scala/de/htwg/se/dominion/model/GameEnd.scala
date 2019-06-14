package de.htwg.se.dominion.model

object GameEnd {

  def end(i: Int): Unit = {
    if (i == 0) {
      print("\n The highest Winning Point Card is empty \n " +
        "---------------------- Game Ends ----------------------\n \n")
    }
    if (i == 1) {
      print("\n 3 piles are empty \n " +
        "---------------------- Game Ends ----------------------\n \n")
    }
  }

  def score(list: List[Player]): Unit = {

  }
}