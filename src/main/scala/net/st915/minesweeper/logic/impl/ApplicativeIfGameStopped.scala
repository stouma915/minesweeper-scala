package net.st915.minesweeper.logic.impl

import cats.Applicative
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.application.IfGameStopped

class ApplicativeIfGameStopped[F[_]: Applicative] extends IfGameStopped[F] {

  override def perform[A](ifStopped: => F[A])(ifNotStopped: => F[A])(implicit
  gameState: GameState): F[A] =
    if (gameState.stopped)
      ifStopped
    else
      ifNotStopped

}
