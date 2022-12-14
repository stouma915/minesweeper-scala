package net.st915.minesweeper.logic.gamelogics.instances

import cats.Monad
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.gamelogics.typeclasses.CanEnterFlagPlaceMode

class MonadCanEnterFlagPlaceMode[F[_]: Monad] extends CanEnterFlagPlaceMode[F] {

  override def perform(using gameState: GameState): F[GameState] =
    Monad[F].pure {
      gameState.copy(inFlagPlaceMode = true)
    }

}
