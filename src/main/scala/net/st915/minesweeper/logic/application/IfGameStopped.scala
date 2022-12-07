package net.st915.minesweeper.logic.application

import net.st915.minesweeper.GameState

object IfGameStopped {

  def apply[F[_]: IfGameStopped]: IfGameStopped[F] = implicitly

}

trait IfGameStopped[F[_]] {

  def perform[A](ifStopped: => F[A])(ifNotStopped: => F[A])(implicit gameState: GameState): F[A]

}
