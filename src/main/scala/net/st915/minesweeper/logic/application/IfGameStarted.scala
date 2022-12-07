package net.st915.minesweeper.logic.application

import net.st915.minesweeper.GameState

object IfGameStarted {

  def apply[F[_]: IfGameStarted]: IfGameStarted[F] = implicitly

}

trait IfGameStarted[F[_]] {

  def perform[A](ifStarted: => F[A])(ifNotStarted: => F[A])(implicit gameState: GameState): F[A]

}
