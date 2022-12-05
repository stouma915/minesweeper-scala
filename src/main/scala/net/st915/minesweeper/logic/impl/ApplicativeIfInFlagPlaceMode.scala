package net.st915.minesweeper.logic.impl

import cats.Applicative
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.application.IfInFlagPlaceMode

class ApplicativeIfInFlagPlaceMode[F[_]: Applicative] extends IfInFlagPlaceMode[F] {

  override def perform[A](ifIn: => F[A])(ifNotIn: => F[A])(implicit gameState: GameState): F[A] =
    if (gameState.inFlagPlaceMode)
      ifIn
    else
      ifNotIn

}
