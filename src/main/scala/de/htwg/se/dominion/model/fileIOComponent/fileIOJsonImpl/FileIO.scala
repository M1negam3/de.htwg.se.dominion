package de.htwg.se.dominion.model.fileIOComponent.fileIOJsonImpl

import de.htwg.se.dominion.controller.maincontroller.RoundManager
import de.htwg.se.dominion.model.ModelInterface
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.fileIOComponent.FileIOInterface
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player

import scala.io.Source
import play.api.libs.json._

/*class FileIO extends FileIOInterface {

  override def load: RoundManager = {
    val source: String = Source.fromFile("roundmanager.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val players = (json \\ "Players").asInstanceOf[List[Player]]
    val names = (json \\ "Names").asInstanceOf[List[String]]
    val numberOfPlayer = (json \ "number Of Players").get.toString.toInt
    val playerTurn = (json \ "player Turn").get.toString.toInt
    val score = (json \\ "score").asInstanceOf[List[(Int, String)]]
    val playingDecks = (json \\ "playing Decks").asInstanceOf[List[List[Cards]]]
    val action = (json \ "action").get.toString.toBoolean
    val empty = (json \ "empty").get.toString.toInt
    val end = (json \ "end").get.toString.toBoolean

    val roundManager = RoundManager(players, names, numberOfPlayer, playerTurn, score, playingDecks, action, empty, end)
    roundManager
  }

  override def save(roundManager: RoundManager): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("roundmanager.json"))
    pw.write(Json.prettyPrint(currentStateToJson(roundManager)))
    pw.close()
  }

  implicit val cardsWrites = new Writes[Cards] {
    def writes(cards: Cards) = Json.obj(
      "Cost Value" -> cards.CostValue,
      "Money Value" -> cards.MoneyValue,
      "Wp Value" -> cards.WpValue,
      "Action Value" -> cards.ActionValue,
      "Buy Addition Value" -> cards.BuyAdditionValue,
      "Bonus Money Value" -> cards.BonusMoneyValue,
      "Drawing Value" -> cards.DrawingValue,
      "Effect Value" -> cards.EffectValue,
      "Card Name" -> cards.CardName,
      "Type" -> cards.Type
    )
  }

  implicit val playerWrites = new Writes[Player] {
    def writes(player: Player) = Json.obj(
      "name" -> player.name,
      "value" -> player.value,
      "deck" -> player.deck,
      "stacker" -> player.stacker,
      "hand" -> player.hand,
      "playingCards" -> player.hand,
      "actions" -> player.actions,
      "buys" -> player.buys,
      "string Value" -> player.stringValue,
      "money" -> player.money
    )
  }

  implicit val roundManagerWrites = new Writes[RoundManager] {
    def writes(roundManager: RoundManager) = Json.obj (
    "Players" -> roundManager.players,
    "Names" -> roundManager.names,
    "number Of Players" -> roundManager.numberOfPlayer,
    "score" -> roundManager.score,
    "playing Decks" -> roundManager.playingDecks,
    "action" -> roundManager.action,
    "empty" -> roundManager.empty,
    "end" -> roundManager.end
    )
  }

  def currentStateToJson(roundManager: RoundManager) = {
    Json.obj(
        "Players" -> Json.toJson(roundManager.players),
        "Names" -> Json.toJson(roundManager.names),
        "number Of Players" -> JsNumber(roundManager.numberOfPlayer),
        "player Turn" -> JsNumber(roundManager.playerturn),
        "score" -> Json.toJson(roundManager.score),
        "playing Decks" -> Json.toJson(roundManager.playingDecks),
        "action" -> JsBoolean(roundManager.action),
        "empty" -> JsNumber(roundManager.empty),
        "end" -> JsBoolean(roundManager.end)
    )
  }
  override def load(modelInterface: ModelInterface): (String, RoundManager) = ???

  override def save(controllerState: String, modelInterface: ModelInterface): Unit = ???
}
  }
}*/