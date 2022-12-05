package net.st915.minesweeper.logic

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.application.*
import net.st915.minesweeper.logic.impl.*

object ToggleFlagPlaceModeLogic {

  def wired[F[_]: Sync](implicit gameState: GameState): F[GameState] = {
    implicit val _updateFlagPlaceModeProperty: UpdateFlagPlaceModeProperty[F] =
      ApplicativeUpdateFlagPlaceModeProperty[F]
    implicit val _toggleFlagPlaceModeProperty: ToggleFlagPlaceModeProperty[F] =
      ApplicativeToggleFlagPlaceModeProperty[F]

    ToggleFlagPlaceModeLogic()
  }

  def apply[F[_]: Sync: ToggleFlagPlaceModeProperty]()(implicit
  gameState: GameState): F[GameState] =
    ToggleFlagPlaceModeProperty[F].update

}
