package net.st915.minesweeper.logic.application

import net.st915.minesweeper.{Coordinate, GameState}

object IfNotOpenedAndNotFlagged {

  def apply[F[_]: IfNotOpenedAndNotFlagged]: IfNotOpenedAndNotFlagged[F] = implicitly

}

trait IfNotOpenedAndNotFlagged[F[_]] {

  def perform[A](coord: Coordinate)(ifTrue: => F[A])(ifFalse: => F[A])(implicit
  gameState: GameState): F[A]

}
