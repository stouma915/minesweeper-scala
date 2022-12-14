package net.st915.minesweeper.logic.gamelogics.instances

import cats.Monad
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.gamelogics.typeclasses.CanExitFlagPlaceMode

class MonadCanExitFlagPlaceMode[F[_]: Monad] extends CanExitFlagPlaceMode[F] {

  override def perform(using gameState: GameState): F[GameState] =
    Monad[F].pure {
      gameState.copy(inFlagPlaceMode = false)
    }

}
