package de.htwg.se.dominion

import com.google.inject.AbstractModule
import de.htwg.se.dominion.controller.ControllerInterface
import de.htwg.se.dominion.controller.maincontroller.{Controller, RoundManager}
import de.htwg.se.dominion.model.deckComponent.HeadDeckInterface
import de.htwg.se.dominion.model.deckComponent.cardComponent.baseCardsComponent.Cards
import de.htwg.se.dominion.model.gameComponent.{GameEndInterface, GameInitInterface, GameTurnInterface}
import de.htwg.se.dominion.model.playerComponent.PlayerInterface
import de.htwg.se.dominion.model.stringComponent.OutputInterface
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player
import de.htwg.se.dominion.model.stringComponent.baseOutputComponent.Output
import de.htwg.se.dominion.model.gameComponent.gameInitComponent.GameInit
import de.htwg.se.dominion.model.gameComponent.gameTurnComponent.GameTurn
import de.htwg.se.dominion.model.gameComponent.gameEndComponent.GameEnd

class DominionModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[PlayerInterface].to[Player]
    bind[OutputInterface].to[Output]
    bind[GameInitInterface].to[GameInit]
    bind[GameTurnInterface].to[GameTurn]
    bind[GameEndInterface].to[GameEnd]
    //bind[RoundManager].toInstance(RoundManager(staticPlayerInterface = StaticPlayer()))
  }
}
