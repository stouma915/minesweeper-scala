package net.st915.minesweeper.logic.eventhandler.application

import net.st915.minesweeper.GameState

object IfGameStopped {

  def apply[F[_]: IfGameStopped]: IfGameStopped[F] = implicitly

}

trait IfGameStopped[F[_]] {

  def perform(ifStopped: => F[GameState])(ifNotStopped: => F[GameState])(implicit
  gameState: GameState): F[GameState]

}
