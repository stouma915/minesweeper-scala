package net.st915.minesweeper.logic.impl

import cats.Applicative
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.application.UpdateGameStartedProperty

class ApplicativeUpdateGameStartedProperty[F[_]: Applicative]
    extends UpdateGameStartedProperty[F] {

  override def update(implicit gameState: GameState): F[GameState] =
    Applicative[F].pure(gameState.copy(gameStarted = true))

}
