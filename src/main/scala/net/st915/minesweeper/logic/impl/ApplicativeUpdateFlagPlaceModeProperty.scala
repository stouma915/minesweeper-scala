package net.st915.minesweeper.logic.impl

import cats.Applicative
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.application.UpdateFlagPlaceModeProperty

class ApplicativeUpdateFlagPlaceModeProperty[F[_]: Applicative]
    extends UpdateFlagPlaceModeProperty[F] {

  override def update(newProperty: Boolean)(implicit gameState: GameState): F[GameState] =
    Applicative[F].pure(gameState.copy(inFlagPlaceMode = newProperty))

}
