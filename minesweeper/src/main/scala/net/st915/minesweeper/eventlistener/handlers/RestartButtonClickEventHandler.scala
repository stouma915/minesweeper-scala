package net.st915.minesweeper.eventlistener.handlers

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.eventlistener.handlers.instances.MonadCanResetGameState
import net.st915.minesweeper.eventlistener.handlers.typeclasses.CanResetGameState

object RestartButtonClickEventHandler {

  import cats.syntax.flatMap.*

  def wired[F[_]: Sync](using GameState): F[GameState] = {
    given CanResetGameState[F] = MonadCanResetGameState[F]

    CanResetGameState[F].perform
  }

}
