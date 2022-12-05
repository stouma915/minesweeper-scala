package net.st915.minesweeper.logic.impl

import cats.Applicative
import net.st915.minesweeper.logic.application.IfFlagged
import net.st915.minesweeper.{Coordinate, GameState}

class ApplicativeIfFlagged[F[_]: Applicative] extends IfFlagged[F] {

  override def perform[A](
    coord: Coordinate
  )(ifFlagged: => F[A])(ifNotFlagged: => F[A])(
    implicit gameState: GameState
  ): F[A] =
    if (gameState.flaggedCoords.contains(coord))
      ifFlagged
    else
      ifNotFlagged

}
