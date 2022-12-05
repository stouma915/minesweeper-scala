package net.st915.minesweeper.logic.eventhandler.impl

import cats.Applicative
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.eventhandler.application.IfGameStopped

class ApplicativeIfGameStopped[F[_]: Applicative] extends IfGameStopped[F] {

  override def perform(ifStopped: => F[GameState])(ifNotStopped: => F[GameState])(implicit
  gameState: GameState): F[GameState] =
    if (gameState.stopped)
      ifStopped
    else
      ifNotStopped

}
