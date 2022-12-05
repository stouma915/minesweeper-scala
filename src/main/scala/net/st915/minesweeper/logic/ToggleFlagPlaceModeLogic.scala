package net.st915.minesweeper.logic

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.application.*
import net.st915.minesweeper.logic.impl.*

object ToggleFlagPlaceModeLogic {

  def wired[F[_]: Sync](implicit gameState: GameState): F[GameState] = {
    implicit val _doNothing: DoNothing[F] = ApplicativeDoNothing[F]
    implicit val _ifGameStarted: IfGameStarted[F] = ApplicativeIfGameStarted[F]
    implicit val _ifGameStopped: IfGameStopped[F] = ApplicativeIfGameStopped[F]
    implicit val _ifCanOperation: IfCanOperation[F] = ApplicativeIfCanOperation[F]
    implicit val _updateFlagPlaceModeProperty: UpdateFlagPlaceModeProperty[F] =
      ApplicativeUpdateFlagPlaceModeProperty[F]
    implicit val _toggleFlagPlaceModeProperty: ToggleFlagPlaceModeProperty[F] =
      ApplicativeToggleFlagPlaceModeProperty[F]

    ToggleFlagPlaceModeLogic()
  }

  def apply[
    F[_]: Sync: IfCanOperation: ToggleFlagPlaceModeProperty: DoNothing
  ]()(implicit gameState: GameState): F[GameState] =
    IfCanOperation[F].perform {
      ToggleFlagPlaceModeProperty[F].update
    } {
      DoNothing[F].perform
    }

}
