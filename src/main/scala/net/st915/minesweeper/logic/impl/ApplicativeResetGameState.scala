package net.st915.minesweeper.logic.impl

import cats.Applicative
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.application.ResetGameState

class ApplicativeResetGameState[F[_]: Applicative] extends ResetGameState[F] {

  override def perform: F[GameState] = Applicative[F].pure(GameState.empty)

}
