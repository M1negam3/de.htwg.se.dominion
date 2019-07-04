package de.htwg.se.dominion.model

import de.htwg.se.dominion.controller.maincontroller.RoundManager
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player
import play.api.libs.json.{JsLookupResult, JsObject, JsValue}

import scala.xml.Elem

trait ModelInterface {

  def toXML: Elem

  def fromXML(node: scala.xml.Node): RoundManager

  def toJson: JsValue

  def fromJson(jsValue: JsValue): RoundManager

}
