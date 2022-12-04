package net.st915.minesweeper.logic.eventhandler.impl

import cats.Applicative
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.eventhandler.application.DoNothing

class ApplicativeDoNothing[F[_]: Applicative] extends DoNothing[F] {

  override def perform(implicit gameState: GameState): F[GameState] =
    Applicative[F].pure(gameState)

}
