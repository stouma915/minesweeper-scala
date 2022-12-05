package net.st915.minesweeper.logic.application

import net.st915.minesweeper.{Coordinate, GameState}

object IfFlagged {

  def apply[F[_]: IfFlagged]: IfFlagged[F] = implicitly

}

trait IfFlagged[F[_]] {

  def perform[A](coord: Coordinate)(ifFlagged: => F[A])(ifNotFlagged: => F[A])(implicit
  gameState: GameState): F[A]

}
