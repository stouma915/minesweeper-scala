package net.st915.minesweeper.eventlistener.tasks

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.eventlistener.tasks.instances.MonadCanResetGameState
import net.st915.minesweeper.eventlistener.tasks.typeclasses.CanResetGameState

object RestartButtonClickEventListener {

  import cats.syntax.flatMap.*

  def wired[F[_]: Sync](using GameState): F[GameState] = {
    given CanResetGameState[F] = MonadCanResetGameState[F]

    CanResetGameState[F].perform
  }

}
