package de.htwg.se.dominion.controller.maincontroller

import de.htwg.se.dominion.model.ModelInterface
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.gameComponent._
import de.htwg.se.dominion.model.gameComponent.gameEndComponent.GameEnd
import de.htwg.se.dominion.model.gameComponent.gameInitComponent.GameInit
import de.htwg.se.dominion.model.gameComponent.gameTurnComponent.{GameTurn, StrategyPatternForActionPhase}
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player
import play.api.libs.json.{JsBoolean, JsLookupResult, JsNumber, JsObject, JsValue, Json, OWrites, Writes}

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.{break, breakable}
import scala.xml.{Elem, Node}

case class RoundManager(players: List[Player] = List(),
                        names: List[String] = List(),
                        numberOfPlayer: Int = 0,
                        playerturn: Int = 0,
                        score: List[(Int, String)] = List(),
                        playingDecks: List[List[Cards]] = GameTurn.playingDecks,
                        action: Boolean = true,
                        empty: Int = 0,
                        end: Boolean = false) extends ModelInterface {

  override def toXML: Elem = {
    <RoundManager>
      <players>{for (i <- players.indices) yield playerToXml(players(i))}</players>
      <names>{for (i <- names.indices) yield <name>{names(i)}</name>}</names>
      <numberOfPlayers>{numberOfPlayer}</numberOfPlayers>
      <playerTurn>{playerturn}</playerTurn>
      <score>{tupleListToXML(score)}</score>
      <playingDecks>{for (i <- playingDecks.indices) yield <playingDeck>{cardsListToXML(playingDecks(i))}</playingDeck>}</playingDecks>
      <action>{action}</action>
      <empty>{empty}</empty>
      <end>{end}</end>
    </RoundManager>
  }

  def tupleListToXML(l: List[(Int, String)]): List[Elem] = {
    var list = List.empty[Elem]
    l.foreach(kv => list = <entry><points>{kv._1}</points><player>{kv._2}</player></entry> :: list)
    list
  }

  def cardsListToXML(l: List[Cards]): List[Elem] = {
    var list = List.empty[Elem]
    l.foreach(c => list = Cards.cardsToXml(c) :: list)
    list
  }

  def playerToXml(player: Player): Elem = {
    <player>
      <name>{player.name}</name>
      <value>{player.value}</value>
      <deck>{for (i <- player.deck.indices) yield Cards.cardsToXml(player.deck(i))}</deck>
      <stacker>{for (i <- player.stacker.indices) yield Cards.cardsToXml(player.stacker(i))}</stacker>
      <hand>{for (i <- player.hand.indices) yield Cards.cardsToXml(player.hand(i))}</hand>
      <playingCards>{for (i <- player.playingCards.indices) yield Cards.cardsToXml(player.playingCards(i))}</playingCards>
      <action>{player.actions}</action>
      <buys>{player.buys}</buys>
      <stringValue>{player.stringValue}</stringValue>
      <money>{player.money}</money>
    </player>
  }

  override def fromXML(node: Node): RoundManager = {
    var listBuffer1: ListBuffer[List[Cards]] = ListBuffer()

    val playersNode = (node \ "players").head.child
    val players = (playersNode.map(node => playerFromXML(node))).toList

    val namesNode = (node \ "names").head.child
    val names = (namesNode.map(node => (node \\ "name").text.trim)).toList

    val numberOfPlayers = (node \ "numberOfPlayers").text.toInt

    val playerTurn = (node \ "playerTurn").text.toInt

    val scoreNode = (node \ "score").head.child
    val score = scoreFromXML(scoreNode)

    val playingDecksNode = (node \ "playingDecks").head.child
    for(i <- (node \ "playingDecks" \ "playingDeck").indices) {
      listBuffer1 += Cards.ListfromXml(node \ "playingDecks",i)
    }
    val playingDecks = listBuffer1.toList

    val action = (node \ "action").text.toBoolean

    val empty = (node \ "empty").text.toInt

    val end = (node \ "end").text.toBoolean

    RoundManager(players, names, numberOfPlayers, playerTurn, score, playingDecks, action, empty, end)
  }

  def scoreFromXML(node: scala.xml.NodeSeq): List[(Int, String)] = {
    var list = List.empty[(Int, String)]
    node.foreach(pp => list = ((node \ "points").text.toInt, (node \ "player").text) :: list)
    list
  }

  def playerFromXML(node: scala.xml.Node): Player = {
    val name= (node \ "name").text.trim
    val value= (node \ "value").text.toInt
    val action = (node \ "action").text.toInt
    val buys = (node \ "buys").text.toInt
    val stringValue = (node \ "stringValue").text.toInt
    val money = (node \ "money").text.toInt
    var listBuffer1: ListBuffer[Cards] = ListBuffer()

    for (i <- 0 until (node \ "deck" \ "card").length) {
      if (!(node \ "deck" \ "card" \ "costValue").text.equals("")) {
        listBuffer1 += Cards.fromXML(node \ "deck" \ "card", i)
      }
    }
    val playerdeck: List[Cards] = listBuffer1.toList
    listBuffer1 = ListBuffer()

    for (f <- 0 until (node \ "stacker" \ "card").length) {
      if (!(node \ "stacker" \ "card" \ "costValue").text.equals("")) {
        listBuffer1 += Cards.fromXML(node \ "stacker" \ "card", f)
      }
    }
    val playerstacker: List[Cards] = listBuffer1.toList
    listBuffer1 = ListBuffer()

    for (i <- 0 until (node \ "hand" \ "card").length) {
      if (!(node \ "hand" \ "card" \ "costValue").text.equals("")) {
        listBuffer1 += Cards.fromXML(node \ "hand" \ "card", i)
      }
    }
    val playerhand: List[Cards] = listBuffer1.toList
    listBuffer1 = ListBuffer()

    for (i <- 0 until (node \ "playingCards" \ "card").length) {
      if (!(node \ "playingCards" \ "card" \ "costValue").text.equals("")) {
        listBuffer1 += Cards.fromXML(node \ "playingCards" \ "card", i)
      }
    }
    val playerplayingCards: List[Cards]= listBuffer1.toList
    listBuffer1 = ListBuffer()

    Player(name,value,playerdeck,playerstacker,playerhand,playerplayingCards,action,buys,stringValue,money)
  }

  override def toJson: JsValue = Json.toJson(this)

  override def fromJson(jsValue: JsValue): RoundManager = {jsValue.validate[RoundManager].asOpt.get}

  def getNames(r: RoundManager, name: String): RoundManager = {
    val copiedRoundManagerRe = r
    val names = GameInit().getPlayerName(copiedRoundManagerRe.names, name)
    RoundManager(copiedRoundManagerRe.players, names, copiedRoundManagerRe.numberOfPlayer, copiedRoundManagerRe.playerturn, copiedRoundManagerRe.score, GameTurn.playingDecks)
  }

  def getNameSetupStrings(): String = {
    "     Player " + (playerturn + 1) + ", please enter your name:"
  }

  def nextPlayer(): Int = {
    if (playerturn < numberOfPlayer - 1) {
      playerturn + 1
    } else {
      0
    }
  }

  def createPlayer(r: RoundManager): List[Player] = {
    val copiedRoundManagerRe = r
    val p = Player().createPlayer(copiedRoundManagerRe.numberOfPlayer, copiedRoundManagerRe.names)
    p
  }

  def actionPhase(r: RoundManager): List[Player] = {
    val copiedRoundManagerRe = r
    val p = GameTurn().actionPhase(copiedRoundManagerRe.players, copiedRoundManagerRe.playerturn)
    p
  }

  def actionPhase2(r: RoundManager, cardnumber: Int): List[Player] = {
    val copiedRoundManagerRe = r
    val p = GameTurn().actionPhase2(copiedRoundManagerRe.players, copiedRoundManagerRe.playerturn, cardnumber)
    p
  }

  def editStringValue(r: RoundManager, newStringValue: Int): List[Player] = {
    val copiedRoundManagerRe = r
    var p = copiedRoundManagerRe.players
    var q: ListBuffer[Player] = ListBuffer()
    for (i <- 0 until copiedRoundManagerRe.players.length) {
      if (i == copiedRoundManagerRe.playerturn) {
        q += new Player(p(copiedRoundManagerRe.playerturn).name, p(copiedRoundManagerRe.playerturn).value, p(copiedRoundManagerRe.playerturn).deck, p(copiedRoundManagerRe.playerturn).stacker, p(copiedRoundManagerRe.playerturn).hand, p(copiedRoundManagerRe.playerturn).playingCards, p(copiedRoundManagerRe.playerturn).actions, p(copiedRoundManagerRe.playerturn).buys, newStringValue, p(copiedRoundManagerRe.playerturn).money)
      } else {
        q += p(i)
      }
    }
    val l = q.toList
    l
  }

  def actionCardEffect1(r: RoundManager, input: Int): List[Player] = {
    val copiedRoundManagerRe = r
    val l = StrategyPatternForActionPhase.getCardname(copiedRoundManagerRe.players, copiedRoundManagerRe.playerturn, input.toInt)
    l
  }

  def actionCardEffect2(r: RoundManager, input: String): List[Player] = {
    val copiedRoundManagerRe = r
    val l = StrategyPatternForActionPhase.getCardName2(copiedRoundManagerRe.players, copiedRoundManagerRe.playerturn, input)
    l
  }

  def updateActions(r: RoundManager): List[Player] = {
    val copiedRoundManagerRe = r
    var p = copiedRoundManagerRe.players
    var l: ListBuffer[Player] = new ListBuffer[Player]
    for (i <- 0 until p.length) {
      if (i == copiedRoundManagerRe.playerturn) {
        l += new Player(p(copiedRoundManagerRe.playerturn).name, p(copiedRoundManagerRe.playerturn).value, p(copiedRoundManagerRe.playerturn).deck, p(copiedRoundManagerRe.playerturn).stacker, p(copiedRoundManagerRe.playerturn).hand, List(), (p(copiedRoundManagerRe.playerturn).actions - 1), p(copiedRoundManagerRe.playerturn).buys, p(copiedRoundManagerRe.playerturn).stringValue, p(copiedRoundManagerRe.playerturn).money)
      } else {
        l += p(i)
      }
    }
    val o: List[Player] = l.toList
    o
  }

  def updateMoney(r: RoundManager, money: Int): List[Player] = {
    val copiedRoundManagerRe = r
    var p = copiedRoundManagerRe.players
    var q: ListBuffer[Player] = ListBuffer()
    for (i <- 0 until copiedRoundManagerRe.players.length) {
      if (i == copiedRoundManagerRe.playerturn) {
        q += new Player(p(copiedRoundManagerRe.playerturn).name, p(copiedRoundManagerRe.playerturn).value, p(copiedRoundManagerRe.playerturn).deck, p(copiedRoundManagerRe.playerturn).stacker, p(copiedRoundManagerRe.playerturn).hand, p(copiedRoundManagerRe.playerturn).playingCards, p(copiedRoundManagerRe.playerturn).actions, p(copiedRoundManagerRe.playerturn).buys, p(copiedRoundManagerRe.playerturn).stringValue, money)
      } else {
        q += p(i)
      }
    }
    val l = q.toList
    l
  }
  def updateActions(r: RoundManager, actions: Int): List[Player] = {
    val copiedRoundManagerRe = r
    var p = copiedRoundManagerRe.players
    var q: ListBuffer[Player] = ListBuffer()
    for (i <- 0 until copiedRoundManagerRe.players.length) {
      if (i == copiedRoundManagerRe.playerturn) {
        q += new Player(p(copiedRoundManagerRe.playerturn).name, p(copiedRoundManagerRe.playerturn).value, p(copiedRoundManagerRe.playerturn).deck, p(copiedRoundManagerRe.playerturn).stacker, p(copiedRoundManagerRe.playerturn).hand, p(copiedRoundManagerRe.playerturn).playingCards, actions, p(copiedRoundManagerRe.playerturn).buys, p(copiedRoundManagerRe.playerturn).stringValue, p(copiedRoundManagerRe.playerturn).money)
      } else {
        q += p(i)
      }
    }
    val l = q.toList
    l
  }

  def getHand(r: RoundManager): List[Player] = {
    val copiedRoundManagerRe = r
    var l = copiedRoundManagerRe.players
    l = Player().updatePlayer(l, Player().getHand(l(copiedRoundManagerRe.playerturn)))
    l
  }

  def end(r: RoundManager): List[Player] = {
    val copiedRoundManagerRe = r
    var l = copiedRoundManagerRe.players
    l = GameEnd().end(l)
    l
  }

  def score(r: RoundManager): List[(Int, String)] = {
    val copiedRoundManagerRe = r
    val score = GameEnd().score(copiedRoundManagerRe.players)
    score
  }
}

object RoundManager {
  import play.api.libs.json._
  implicit val roundManagerWrites: OWrites[RoundManager] = Json.writes[RoundManager]
  implicit val roundManagerReads: Reads[RoundManager] = Json.reads[RoundManager]
}
