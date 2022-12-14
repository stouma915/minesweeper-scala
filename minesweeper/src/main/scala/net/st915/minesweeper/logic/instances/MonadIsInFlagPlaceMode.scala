package net.st915.minesweeper.logic.instances

import cats.Monad
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.typeclasses.IsInFlagPlaceMode

class MonadIsInFlagPlaceMode[F[_]: Monad] extends IsInFlagPlaceMode[F] {

  override def check(using gameState: GameState): F[Boolean] =
    Monad[F].pure {
      gameState.inFlagPlaceMode
    }

}
