package net.st915.minesweeper.logic.impl

import cats.Applicative
import net.st915.minesweeper.logic.application.IfMine
import net.st915.minesweeper.{Coordinate, GameState}

class ApplicativeIfMine[F[_]: Applicative] extends IfMine[F] {

  override def perform[A](coord: Coordinate)(ifMine: => F[A])(ifNotMine: => F[A])(implicit
  gameState: GameState): F[A] =
    if (gameState.mines.contains(coord))
      ifMine
    else
      ifNotMine

}
