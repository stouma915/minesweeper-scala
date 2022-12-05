package net.st915.minesweeper.logic.eventhandler

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellClickEvent
import net.st915.minesweeper.logic.*
import net.st915.minesweeper.logic.application.*
import net.st915.minesweeper.logic.impl.*

object HandleCellClickEvent {

  def wired[F[_]: Sync](event: CellClickEvent)(implicit gameState: GameState): F[GameState] = {
    implicit val _doNothing: DoNothing[F] = ApplicativeDoNothing[F]
    implicit val _ifGameStarted: IfGameStarted[F] = ApplicativeIfGameStarted[F]
    implicit val _ifGameStopped: IfGameStopped[F] = ApplicativeIfGameStopped[F]
    implicit val _ifCanOpenOperation: IfCanOpenOperation[F] = ApplicativeIfCanOpenOperation[F]
    implicit val _ifInFlagPlaceMode: IfInFlagPlaceMode[F] = ApplicativeIfInFlagPlaceMode[F]

    HandleCellClickEvent(event)
  }

  def apply[
    F[_]: Sync: IfCanOpenOperation: IfInFlagPlaceMode
  ](event: CellClickEvent)(implicit gameState: GameState): F[GameState] =
    IfCanOpenOperation[F].perform {
      IfInFlagPlaceMode[F].perform {
        PlaceFlagLogic.wired[F](event.coord)
      } {
        OpenCellLogic.wired[F](event.coord)
      }
    } {
      // TODO: Write start game logic here
      Sync[F].pure(gameState)
    }
}
