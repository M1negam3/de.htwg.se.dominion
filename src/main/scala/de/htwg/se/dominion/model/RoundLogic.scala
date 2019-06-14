package de.htwg.se.dominion.model
import de.htwg.se.dominion.model._
import de.htwg.se.dominion.model.Player._
import scala.collection.mutable.ListBuffer

object RoundLogic {

  def turn1(list: List[Player]): Unit = {
    var l = list
    var money = 0
    var actionNumber = 0
    var actionString = ""
    var buys = 2
    var input = 0
    var playingDecks = Cards.playingDeck
    var playingCards: List[Cards] = Nil
    var bufferStacker: List[Cards] = Nil

    for (i <- 0 until l.length) {
      print("---------------------- New Turn ----------------------\n \n")
      println("Player " + l(i).value + " `s turn")
      l = Player.updatePlayer(l, Player.getHand(l(i)))
      money = Player.getMoney(l(i))

      print("\n---------------------- Actionphase ----------------------\n \n")
      for (f <- 0 until 5) {
        if (l(i).hand(f).Type.equals("Action")) {
          actionNumber += 1
          actionString += l(i).hand(f).CardName + "(" + f + ")" + ", "
        }
      }
      if (actionNumber == 0) {
        println("You dont have any Actioncards to play")
      } else {
        println("Your action cards are: " + actionString)
      }

        /*do{
          println("Choose with a number the card to play")
            var j = scala.io.StdIn.readInt()
            playingCards = l(i).hand(j) :: Nil
            actionNumber += playingCards(i).ActionValue
            actionNumber -= 1
            bufferStacker =l(i).stacker ::: l(i).hand(j) :: Nil
            println(playingCards)

        } while(actionNumber > 0)


      playingCards = Nil*/
      // TODO GESPIELTE KARTEN

      print("\n---------------------- Buy Phase ----------------------\n \n")
      while (buys != 0) {
        println("Your money is: " + money)
        println("Your Buy actions are: " + buys)
        print("You can buy these: ")
        for (g <- 0 until playingDecks.length) {
          if (money >= playingDecks(g).head.CostValue) {
            print(playingDecks(g).head.CardName + "{" + playingDecks(g).length + "}" + "[" + playingDecks(g).head.CostValue + "]" + "(" + g + "), ")
          }
        }
        print("\nWhich Card do you want to buy?\n")
        input = scala.io.StdIn.readInt()
        var copiedCard = playingDecks(input).head
        l = Player.updatePlayer(l, updateStacker(l(i), copiedCard))
        playingDecks = updateDeck(playingDecks, copyList(playingDecks(input)), input)
        money = money - playingDecks(input).head.CostValue
        buys -= 1
      }

      println("You cant do anything anymore, your turn is over")
      println("Player" + l(i).value + "`s turn is over!")
      print("\n---------------------- Turn End ----------------------\n \n")
    }
  }

  def copyList(cards: List[Cards]): List[Cards] = {
    var l= new ListBuffer[Cards]

    for(j <- 1 until cards.length){
      l += cards(j)
    }
    val copiedList: List[Cards] = l.toList
    copiedList
  }

  def updateDeck(l: List[List[Cards]], o: List[Cards], i: Int): List[List[Cards]] = {
    var copiedPlayingDecks = l
    var changedList = o
    val idx = i
    var updatedPlayingDeck: ListBuffer[List[Cards]] = ListBuffer()
    for (i <- 0 until copiedPlayingDecks.length) {
      if (i == idx) {
        updatedPlayingDeck += changedList
      } else {
        updatedPlayingDeck += copiedPlayingDecks(i)
      }
    }
      val updatedList: List[List[Cards]] = updatedPlayingDeck.toList
      updatedList
  }

  def updateStacker(p: Player, c: Cards): Player = {
    var copiedPlayer = p
    val copiedCard = c
    var copiedStacker = new ListBuffer[Cards]
    for (i <- 0 until copiedPlayer.stacker.length) {
      copiedStacker += copiedPlayer.stacker(i)
    }
    copiedStacker += copiedCard
    val updatedStacker: List[Cards] = copiedStacker.toList
    new Player(copiedPlayer.name, copiedPlayer.value, copiedPlayer.deck, updatedStacker, copiedPlayer.hand)
  }

}
