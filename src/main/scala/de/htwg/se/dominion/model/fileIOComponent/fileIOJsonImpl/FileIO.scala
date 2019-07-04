package de.htwg.se.dominion.model.fileIOComponent.fileIOJsonImpl

import de.htwg.se.dominion.controller.maincontroller.RoundManager
import de.htwg.se.dominion.model.ModelInterface
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.fileIOComponent.FileIOInterface
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player

import scala.io.Source
import play.api.libs.json._

class FileIO extends FileIOInterface {

  override def load(modelInterface: ModelInterface): (String, RoundManager) = {
    val source = Source.fromFile("roundmanager.json")
    val sourceString = source.getLines.mkString
    source.close()
    val json = Json.parse(sourceString)

    val controllerStateString = (json \ "controllerState").get.as[String]
    val roundManager = modelInterface.fromJson((json \ "RoundManager").get)
    (controllerStateString, roundManager)
  }

  override def save(controllerState: String, modelInterface: ModelInterface): Unit = {
    val savedGame = Json.obj (
      "controllerState" -> controllerState,
      "RoundManager" -> modelInterface.toJson
    )

    import java.io._
    val pw = new PrintWriter(new File("roundmanager.json"))
    pw.write(Json.prettyPrint(savedGame))
    pw.close()
  }
}
