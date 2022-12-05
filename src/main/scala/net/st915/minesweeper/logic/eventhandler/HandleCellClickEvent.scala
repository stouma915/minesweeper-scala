package net.st915.minesweeper.logic.eventhandler

import cats.effect.Sync
import net.st915.minesweeper.{Difficulty, GameState}
import net.st915.minesweeper.event.CellClickEvent
import net.st915.minesweeper.logic.*
import net.st915.minesweeper.logic.application.*
import net.st915.minesweeper.logic.impl.*

object HandleCellClickEvent {

  import cats.syntax.flatMap.*

  def wired[F[_]: Sync](event: CellClickEvent)(
    implicit gameState: GameState,
    difficulty: Difficulty
  ): F[GameState] = {
    implicit val _doNothing: DoNothing[F] = ApplicativeDoNothing[F]
    implicit val _ifGameStarted: IfGameStarted[F] = ApplicativeIfGameStarted[F]
    implicit val _ifGameStopped: IfGameStopped[F] = ApplicativeIfGameStopped[F]
    implicit val _ifCanOpenOperation: IfCanOpenOperation[F] = ApplicativeIfCanOpenOperation[F]
    implicit val _ifInFlagPlaceMode: IfInFlagPlaceMode[F] = ApplicativeIfInFlagPlaceMode[F]

    HandleCellClickEvent(event)
  }

  def apply[
    F[_]: Sync: IfCanOpenOperation: IfInFlagPlaceMode
  ](event: CellClickEvent)(implicit gameState: GameState, difficulty: Difficulty): F[GameState] =
    IfCanOpenOperation[F].perform {
      IfInFlagPlaceMode[F].perform {
        PlaceFlagLogic.wired[F](event.coord)
      } {
        OpenCellLogic.wired[F](event.coord)
      }
    } {
      StartGameLogic.wired[F](event.coord, difficulty) >>= { implicit started =>
        OpenCellLogic.wired[F](event.coord)
      }
    }
}
