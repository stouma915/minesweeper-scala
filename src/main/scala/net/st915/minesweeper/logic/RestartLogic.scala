package net.st915.minesweeper.logic

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.application.*
import net.st915.minesweeper.logic.impl.*

object RestartLogic {

  def wired[F[_]: Sync](implicit gameState: GameState): F[GameState] = {
    implicit val _resetGameState: ResetGameState[F] = ApplicativeResetGameState[F]

    RestartLogic()
  }

  def apply[F[_]: Sync: ResetGameState]()(implicit gameState: GameState): F[GameState] =
    ResetGameState[F].perform

}
