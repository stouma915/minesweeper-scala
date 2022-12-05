package net.st915.minesweeper.logic.impl

import cats.Applicative
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.application.DoNothing

class ApplicativeDoNothing[F[_]: Applicative] extends DoNothing[F] {

  override def perform(implicit gameState: GameState): F[GameState] =
    Applicative[F].pure(gameState)

}
