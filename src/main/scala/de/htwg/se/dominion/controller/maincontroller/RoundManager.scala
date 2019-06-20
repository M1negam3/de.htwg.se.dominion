package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.model.GameTurn._
import de.htwg.se.dominion.model.Player.draw
import de.htwg.se.dominion.model.{Cards, GameInit, Player}

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.{break, breakable}

case class RoundManager(players: List[Player] = Nil,
                        numberOfRounds: Int = 0,
                        numberOfPlayers: Int = 0,
                        names: List[String] = Nil){
                        //startDeck:  List[Cards] = Cards.startDeck,
                        //playingDeck: List[List[Cards]] = Cards.playingDeck)
  {


  def getnumberOfPlayers() : Int ={
     val copiednumberOfPlayers = GameInit.getPlayerCount()
    copiednumberOfPlayers
  }
  def names(numberOfPlayers: Int); List[String] = {
    val copiednames = GameInit.getPlayerName(numberOfPlayers)
  }

  def checkNumberOfPlayers(number: Int): Boolean = {
    Player.checkNumberOfPlayers(number)
  }
  def createPlayer (numberOfPlayers: Int, names: List[String]) : RoundManager = {
    var players = new ListBuffer[Player]
    for (i <- 0 until numberOfPlayers) {
      players += new Player(names(i), i + 1, Cards.shuffle(Cards.startDeck), Cards.stacker, Cards.hand)
      print(Console.BLUE + "     Player " + (i + 1) + " wurde erstellt!\n")
    }
    val Players: List[Player] = players.toList
    RoundManager(Players,0,numberOfPlayers,names)
  }

  def getPlayerStateStrings(playingDeck: List[List[Cards]]): String = {
    var emptyStacks = 0
    for(i <- 0 until playingDeck.length) {
     if(playingDeck(i).length == 0) {
       emptyStacks += 1
     }
    }
    if(emptyStacks == 3) {
     return "\nGame Over! Press q to quit.\n"
   } else {
      return "weiß noch net"
    }
  }
  def actionPhase(list: List[Player], idx: Int): RoundManager = {
    var l = list
    var actionNumber = 0
    var z: Integer = 0
    var x: Integer = 0
    var y: Integer = 0
    var cardNumber = 0
    var check = false
    var playingCards: List[Cards] = Nil
    val copiedRoundManager = RoundManager

    money = 0
    l = Player.updatePlayer(l, Player.getHand(l(idx)))
    money = Player.getMoney(l(idx))

    for (f <- 0 until 5) {
      if (l(idx).hand(f).Type.equals("Action") && !check) {
        actionNumber = 1
      }
    }
    if (actionNumber == 0) {
      println(Console.BLUE + "     You dont have any Actioncards to play")
    }
    while (actionNumber > 0) {
      breakable {
        for (h <- 0 until l(idx).hand.length) {
          if (l(idx).hand(h).Type.equals("Action") && !check) {
            actionString += l(idx).hand(h).CardName + Console.BLACK + " (" + h + ")" + "\n"
            check = true
          } else if (l(idx).hand(h).Type.equals("Action") && check) {
            actionString += "                            " + Console.BLUE + l(idx).hand(h).CardName + Console.BLACK + " (" + h + ")" + "\n"
          }
          if (l(idx).hand(h).Type == "Money") {
            z += 1
          }
        }
        check = false
        x = l(idx).hand.length
        y = l(idx).hand.length - 1
        if (z.equals(x)) {
          println(Console.BLUE + "     Your actions are: " + actionNumber)
          println(Console.RED + "     You dont have any Action Cards to play!")
          actionNumber = 0
          inputStr = ""
          break
        }
        z = 0
        println(Console.BLUE + "     Your actions are: " + actionNumber)
        println(Console.BLUE + "     Your action cards are: " + actionString)
        print(Console.YELLOW + "\n     Do you want to play a Card? (Y/N)\n")
        while (true) {
          inputStr = scala.io.StdIn.readLine()
          if (inputStr.equals("N")) {
            actionNumber = 0
            inputStr = ""
            break
          } else if (inputStr.equals("Y")) {
            println(Console.BLACK + "     Enter a number to choose a card, which you want to play")
            while (true) {
              try {
                cardNumber = scala.io.StdIn.readInt()
                if (cardNumber < x && l(idx).hand(cardNumber).Type == "Action") {
                  playingCards = l(idx).hand(cardNumber) :: Nil
                  l = Player.updatePlayer(l, removeHandcard(cardNumber, l(idx)))
                  money += playingCards.head.BonusMoneyValue
                  buys += playingCards.head.BuyAdditionValue
                  draws += playingCards.head.DrawingValue

                  print(Console.BLUE + "     Your card effect is: "+ Console.BLACK + playingCards.head.EffectValue + "\n\n")
                  print(Console.BLUE + "     Your Hand cards are: " + l(idx).hand.head.CardName + Console.BLACK + " (" + 0 + ")\n")
                  for (i <- 1 until l(idx).hand.length) {
                    print(Console.BLUE + "                          " + l(idx).hand(i).CardName + Console.BLACK + " (" + i + ")\n")
                  }
                  if (playingCards.head.CardName == "Cellar") {
                    l = cellar(l, idx)
                  } else if (playingCards.head.CardName == "Mine") {
                    l = mine(l, idx)
                  } else if (playingCards.head.CardName == "Remodel") {
                    l = remodel(l, idx)
                  } else if (playingCards.head.CardName == "Merchant") {
                    money = money + merchant(l, idx)
                  } else if(playingCards.head.CardName == "Workshop") {
                    l = workshop(l, idx)
                  }
                  for (h <- 0 until playingDecks.length) {
                    if (playingDecks(h).isEmpty) {
                      if (h == 3) {
                        end = true
                      }
                      playingDecks = updatePlayingDecks(playingDecks, h)
                      empty += 1
                      if (empty == 3) {
                        end = true
                      }
                      break
                    }
                  }
                  l = Player.updatePlayer(l, draw(l(idx), draws))
                  draws = 0
                  l = Player.updatePlayer(l, updateStacker(l(idx), playingCards.head))
                  actionNumber += playingCards.head.ActionValue
                  actionNumber -= 1
                  //println("TEST " + l(idx).hand)
                  playingCards = Nil
                  actionString = ""
                } else {
                  println(Console.RED + "     Please enter an Actioncard between 0 and " + y)
                }
              } catch {
                case exception: NumberFormatException => println(Console.RED + "      Please enter a correct number!")
              }
              break
            }
          } else {
            println(Console.RED + "     Try Y or N!")
          }
        }
      }
    }
    actionString = ""
    RoundManager(l)
  }

  def buyPhase(list: List[Player], idx: Int): RoundManager = {
    var l = list
    var availableCards: ListBuffer[Int] = ListBuffer()
    while (buys > 0) {
      inputInt = 0
      inputStr = ""
      println(Console.BLUE + " Your money is: " + money)
      println(Console.BLUE + "     Your Buy actions are: " + buys)
      print(Console.BLUE + "     You can buy these: " + Console.CYAN + "{Quantity}" + Console.MAGENTA + "[Cost]" + Console.BLACK + "(PRESS)\n")
      for (g <- 0 until playingDecks.length) {
        if (money >= playingDecks(g).head.CostValue) {
          availableCards += g
          print(Console.BLUE + "                        " + playingDecks(g).head.CardName + Console.CYAN + " {" + playingDecks(g).length + "} " + Console.MAGENTA + "[" + playingDecks(g).head.CostValue
            + "]" + Console.BLUE + " Card Effect: " + playingDecks(g).head.EffectValue + Console.BLACK + " (" + g + ")" + "\n")
        }
      }
      breakable {
        print(Console.YELLOW + "\n     Do you want to buy a Card? (Y/N)\n")
        while (buys > 0) {
          inputStr = scala.io.StdIn.readLine()
          if (inputStr.equals("N")) {
            buys = 0
            inputStr = ""
            break
          }
          else if (inputStr.equals("Y")) {
            while (buys > 0) {
              print(Console.YELLOW + "\n     Which Card do you want to buy?\n")
              try {
                inputInt = scala.io.StdIn.readInt()
                if (availableCards.contains(inputInt)) {
                  var copiedCard = playingDecks(inputInt).head
                  l = Player.updatePlayer(l, updateStacker(l(idx), copiedCard))
                  money = money - playingDecks(inputInt).head.CostValue
                  playingDecks = updateDeck(playingDecks, copyList(playingDecks(inputInt)), inputInt)
                  print(Console.BLUE + "\n     The Card " + copiedCard.CardName + " was bought and added to your stacker\n \n")
                  for (h <- 0 until playingDecks.length) {
                    if (playingDecks(h).isEmpty) {
                      if (h == 3) {
                        end = true
                      }
                      playingDecks = updatePlayingDecks(playingDecks, h)
                      empty += 1
                      if (empty == 3) {
                        end = true
                      }
                      break
                    }
                  }
                  break
                } else {
                  println(Console.RED + "     You cant buy that, please enter a valid number")
                }
              } catch {
                case exception: NumberFormatException => println(Console.RED + "     Please enter a correct number!")
              }
            }
          } else {
            println(Console.RED + "     Try Y or N!")
          }
        }
      }
      buys -= 1
    }

    for (e <- 0 until l(idx).hand.length) {
      l = Player.updatePlayer(l, updateStacker(l(idx), l(idx).hand(e)))
    }
    buys = 1
    RoundManager(l)
  }






  object RoundManager {
    def calcPoints(player: List[Player]): List[Int] = {
      var points: List[Int] = Nil
      var p = player
      var z: List[Int] = Nil
      for(j <- 0 until p.length){
        for(i <- 0 until p(j).hand.length){
          z(j) += p(j).hand(i).WpValue
        }
        for(k <- 0 until p(j).deck.length) {
          z(j) += p(j).deck(k).WpValue
        }
        /*for(z <- 0 until p(j).stacker.length) {
          z(j) += p(j).stacker(z).WpValue
        }*/
      }
     points
    }
    case class Builder() {
      var numberOfPlayers: Int = 0
      var numberOfRounds: Int = 0
      def withNumberOfPlayers(players: Int): Builder ={
        numberOfPlayers = players
        this
      }
      def withNumberOfRounds(rounds: Int): Builder = {
        numberOfRounds = rounds
        this
      }
      def build(): RoundManager = {
        RoundManager(
          numberOfPlayers, numberOfRounds,
        )
      }
    }
  }
}
