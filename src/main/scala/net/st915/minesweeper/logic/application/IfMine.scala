package net.st915.minesweeper.logic.application

import net.st915.minesweeper.{Coordinate, GameState}

object IfMine {

  def apply[F[_]: IfMine]: IfMine[F] = implicitly

}

trait IfMine[F[_]] {

  def perform[A](coord: Coordinate)(ifMine: => F[A])(ifNotMine: => F[A])(implicit
  gameState: GameState): F[A]

}
