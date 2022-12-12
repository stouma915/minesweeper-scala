package net.st915.minesweeper.eventlistener.tasks

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.util.instances.MonadDoNothing
import net.st915.minesweeper.util.typeclasses.DoNothing

object ToggleFlagModeButtonClickEventListener {

  import cats.syntax.flatMap.*

  def wired[F[_]: Sync](using GameState): F[GameState] = {
    given DoNothing[F] = MonadDoNothing[F]

    Sync[F].pure(println("toggle flag mode button clicked")) >>
      DoNothing[F].perform
  }

}
