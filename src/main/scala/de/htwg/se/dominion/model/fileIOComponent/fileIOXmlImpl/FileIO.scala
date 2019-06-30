package de.htwg.se.dominion.model.fileIOComponent.fileIOXmlImpl

import de.htwg.se.dominion.controller.maincontroller.RoundManager
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.fileIOComponent.FileIOInterface
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player

import scala.xml.{Elem, PrettyPrinter}

class FileIO extends FileIOInterface {

  override def load: RoundManager = {
    var roundManager: RoundManager = null
    val file = scala.xml.XML.loadFile("roundManager.xml")
    val namesAttr = (file \\ "roundManager" \ "@names")
    val names = namesAttr.asInstanceOf[List[String]]
    val playersAttr = file \\ "roundManager" \ "@players" \\ "@money"
    val players = playersAttr.asInstanceOf[List[Player]]
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
    roundManager = new RoundManager(players,names,numberOfPlayer,playerTurn,score,playingDeck,action,empty,end)
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

  def currentStateToXml(roundManager: RoundManager): Elem = {
    <roundManager>
      <names>{roundManager.names}</names>
      <players>{roundManager.players}</players>
      <numberOfPlayers>{roundManager.numberOfPlayer}</numberOfPlayers>
      <playerTurn>{roundManager.playerturn}</playerTurn>
      <score>{roundManager.score}</score>
      <playingDecks>{roundManager.playingDecks}</playingDecks>
      <action>{roundManager.action}</action>
      <empty>{roundManager.empty}</empty>
      <end>{roundManager.end}</end>
    </roundManager>
  }

}
