package de.htwg.se.dominion

import com.google.inject.AbstractModule
import de.htwg.se.dominion.controller.ControllerInterface
import de.htwg.se.dominion.controller.maincontroller.{Controller, RoundManager}
import de.htwg.se.dominion.model.fileIOComponent.FileIOInterface
import de.htwg.se.dominion.model.gameComponent.{GameEndInterface, GameInitInterface, GameTurnInterface}
import de.htwg.se.dominion.model.playerComponent.PlayerInterface
import de.htwg.se.dominion.model.stringComponent.OutputInterface
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.dominion.model.playerComponent.basePlayerComponent.Player
import de.htwg.se.dominion.model.stringComponent.baseOutputComponent.Output
import de.htwg.se.dominion.model.gameComponent.gameInitComponent.GameInit
import de.htwg.se.dominion.model.gameComponent.gameTurnComponent.GameTurn
import de.htwg.se.dominion.model.gameComponent.gameEndComponent.GameEnd
import de.htwg.se.dominion.model.fileIOComponent._

class DominionModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[PlayerInterface].to[Player]
    bind[OutputInterface].to[Output]
    bind[GameInitInterface].to[GameInit]
    bind[GameTurnInterface].to[GameTurn]
    bind[GameEndInterface].to[GameEnd]
    bind[ControllerInterface].to[Controller]
    bind[RoundManager].toInstance(RoundManager())
    bind[Player].toInstance(Player())

    bind[FileIOInterface].to[fileIOXmlImpl.FileIO]
  }
}
