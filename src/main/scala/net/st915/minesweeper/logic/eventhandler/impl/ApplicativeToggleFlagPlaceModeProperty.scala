package net.st915.minesweeper.logic.eventhandler.impl

import cats.Applicative
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.eventhandler.application.*

class ApplicativeToggleFlagPlaceModeProperty[
  F[_]: Applicative: UpdateFlagPlaceModeProperty
] extends ToggleFlagPlaceModeProperty[F] {

  override def update(implicit gameState: GameState): F[GameState] =
    UpdateFlagPlaceModeProperty[F].update(!gameState.inFlagPlaceMode)

}
