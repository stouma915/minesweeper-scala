package net.st915.minesweeper.logic.impl

import cats.Applicative
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.application.IfGameStarted

class ApplicativeIfGameStarted[F[_]: Applicative] extends IfGameStarted[F] {

  override def perform[A](ifStarted: => F[A])(ifNotStarted: => F[A])(implicit
  gameState: GameState): F[A] =
    if (gameState.gameStarted)
      ifStarted
    else
      ifNotStarted

}
