package net.st915.minesweeper.logic.eventhandler.impl

import cats.Applicative
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.eventhandler.application.ResetGameState

class ApplicativeResetGameState[F[_]: Applicative] extends ResetGameState[F] {

  override def perform: F[GameState] = Applicative[F].pure(GameState.empty)

}
