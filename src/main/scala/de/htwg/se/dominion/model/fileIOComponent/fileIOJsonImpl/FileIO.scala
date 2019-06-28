package de.htwg.se.dominion.model.fileIOComponent.fileIOJsonImpl

import com.google.inject.Guice
import de.htwg.se.dominion.DominionModule
import de.htwg.se.dominion.controller.maincontroller.RoundManager
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.fileIOComponent.FileIOInterface
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player

import scala.io.Source
import play.api.libs.json._

class FileIO extends FileIOInterface {

  override def load: RoundManager = {
    var roundManager: RoundManager = null
    val source: String = Source.fromFile("roundmanager.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val injector = Guice.createInjector(new DominionModule)

    val players = (json \\ "Players").asInstanceOf[List[Player]]
    val names = (json \\ "Names").asInstanceOf[List[String]]
    val numberOfPlayer = (json \ "number Of Players").get.toString.toInt
    val playerTurn = (json \ "player Turn").get.toString.toInt
    val score = (json \\ "score").asInstanceOf[List[(Int, String)]]
    val playingDecks = (json \\ "playing Decks").asInstanceOf[List[List[Cards]]]
    val action = (json \ "action").get.toString().toBoolean
    val empty = (json \ "empty").get.toString.toInt
    val end = (json \ "end").get.toString.toBoolean

    roundManager = roundManager.copy(players, names, numberOfPlayer, playerTurn, score,
      playingDecks, action, empty, end)
    roundManager
  }

  override def save(roundManager: RoundManager): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("roundmanager.json"))
    pw.write(Json.prettyPrint(currentStateToJson(roundManager)))
    pw.close()
  }

  def currentStateToJson(roundManager: RoundManager) = {
    Json.obj(
      "GameState" -> Json.obj(
        //"Players" -> Json.toJson(roundManager.players),
        "Names" -> Json.toJson(roundManager.names),
        "number of Players" -> JsNumber(roundManager.numberOfPlayer),
        "player Turn" -> JsNumber(roundManager.playerturn),
        //"score" -> Json.toJson(roundManager.score),
        //"playing Decks" -> Json.toJson(roundManager.playingDecks),
        "action" -> JsBoolean(roundManager.action),
        "empty" -> JsNumber(roundManager.empty),
        "end" -> JsBoolean(roundManager.end)
      )
    )
  }

}