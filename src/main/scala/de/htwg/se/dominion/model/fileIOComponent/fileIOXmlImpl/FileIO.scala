package de.htwg.se.dominion.model.fileIOComponent.fileIOXmlImpl

import de.htwg.se.dominion.controller.maincontroller.RoundManager
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.fileIOComponent.FileIOInterface
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player

import scala.xml.{Elem, PrettyPrinter}

class FileIO extends FileIOInterface {

  override def load: RoundManager = {
    val file = scala.xml.XML.loadFile("roundmanager.xml")

    val playersAttr = file \\ "roundManager" \\ "@players"
    val players = playersAttr.asInstanceOf[List[Player]]

    val namesAttr = file \\ "roundManager" \ "@names"
    val names = namesAttr.asInstanceOf[List[String]]
    val numberOfPlayersAttr = file \\ "roundManager" \ "@numberOfPlayer"
    val numberOfPlayer = numberOfPlayersAttr.text.toInt
    val playerTurnAttr = file \\ "roundManager" \ "@playerTurn"
    val playerTurn = playerTurnAttr.text.toInt
    val scoreAttr = file \\ "roundManager" \ "@score"
    val score = scoreAttr.asInstanceOf[List[(Int, String)]]
    val playingDeckAttr = file \\ "roundManager" \ "@playingDeck"
    val playingDeck = playingDeckAttr.asInstanceOf[List[List[Cards]]]
    val actionAttr = file \\ "roundManager" \ "@action"
    val action = actionAttr.text.toBoolean
    val emptyAttr = file \\ "roundManager" \ "@empty"
    val empty = emptyAttr.text.toInt
    val endAttr = file \\ "roundManager" \ "@end"
    val end = endAttr.text.toBoolean
    val roundManager = RoundManager(players,names,numberOfPlayer,playerTurn,score,playingDeck,action,empty,end)
    roundManager
  }

  override def save(roundManager: RoundManager): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("roundmanager.xml"))
    val prettyPrinter = new PrettyPrinter(120,4)
    val xml = prettyPrinter.format(currentStateToXml(roundManager))
    pw.write(xml)
    pw.close()
  }

  def saveXML(roundManager: RoundManager): Unit = {
    scala.xml.XML.save("roundmanager.xml", currentStateToXml(roundManager))
  }


  def cardsToXml(cards: Cards) = {
    <cards>
      <costValue>{cards.CostValue}</costValue>
      <moneyValue>{cards.MoneyValue}</moneyValue>
      <wpValue>{cards.WpValue}</wpValue>
      <actionValue>{cards.ActionValue}</actionValue>
      <buyAdditionValue>{cards.BuyAdditionValue}</buyAdditionValue>
      <bonusMoneyValue>{cards.BonusMoneyValue}</bonusMoneyValue>
      <drawingValue>{cards.DrawingValue}</drawingValue>
      <effectValue>{cards.EffectValue}</effectValue>
      <cardName>{cards.CardName}</cardName>
      <type>{cards.Type}</type>
    </cards>
  }

  def currentStateToXml(roundManager: RoundManager): Elem = {
    <roundmanager>
      <players>
        {for {
        player <- roundManager.players.indices
      } yield playerToXml(roundManager.players(player))
        }</players>
      <names>{
        for {
          name <- roundManager.names.indices
        } yield namesToXml(roundManager.names(name))
        }</names>
      <numberOfPlayers>{roundManager.numberOfPlayer}</numberOfPlayers>
      <playerTurn>{roundManager.playerturn}</playerTurn>
      <score>{roundManager.score}</score>
      <playingDeck>{roundManager.playingDecks}</playingDeck>
      <action>{roundManager.action}</action>
      <empty>{roundManager.empty}</empty>
      <end>{roundManager.end}</end>
    </roundmanager>
  }

  def playerToXml(player: Player): Elem = {
    <player>
      <name>{player.name}</name>
      <value>{player.value}</value>
      <deck>{player.deck}</deck>
      <stacker>{player.stacker}</stacker>
      <hand>{player.hand}</hand>
      <playingCards>{player.playingCards}</playingCards>
      <action>{player.actions}</action>
      <buys>{player.buys}</buys>
      <stringValue>{player.stringValue}</stringValue>
      <money>{player.money}</money>
    </player>
  }

  def namesToXml(name: String): Elem = {
    <name>{name}</name>
  }
}

