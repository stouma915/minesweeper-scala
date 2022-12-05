package net.st915.minesweeper.logic.application

import net.st915.minesweeper.{Coordinate, GameState}

object IfOpened {

  def apply[F[_]: IfOpened]: IfOpened[F] = implicitly

}

trait IfOpened[F[_]] {

  def perform[A](coord: Coordinate)(ifOpened: => F[A])(ifNotOpened: => F[A])(implicit
  gameState: GameState): F[A]

}
