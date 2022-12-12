package net.st915.minesweeper.eventlistener.tasks.instances

import cats.Monad
import net.st915.minesweeper.GameState
import net.st915.minesweeper.eventlistener.tasks.typeclasses.CanResetGameState

class MonadCanResetGameState[F[_]: Monad] extends CanResetGameState[F] {

  override def perform(using GameState): F[GameState] =
    Monad[F].pure(GameState.empty)

}
