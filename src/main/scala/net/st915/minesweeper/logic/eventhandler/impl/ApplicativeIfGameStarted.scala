package net.st915.minesweeper.logic.eventhandler.impl

import cats.Applicative
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.eventhandler.application.IfGameStarted

class ApplicativeIfGameStarted[F[_]: Applicative] extends IfGameStarted[F] {

  override def perform(ifStarted: => F[GameState])(ifNotStarted: => F[GameState])(implicit
  gameState: GameState): F[GameState] =
    if (gameState.gameStarted)
      ifStarted
    else
      ifNotStarted

}
