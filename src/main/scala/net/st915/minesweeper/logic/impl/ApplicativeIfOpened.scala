package net.st915.minesweeper.logic.impl

import cats.Applicative
import net.st915.minesweeper.logic.application.IfOpened
import net.st915.minesweeper.{Coordinate, GameState}

class ApplicativeIfOpened[F[_]: Applicative] extends IfOpened[F] {

  override def perform[A](coord: Coordinate)(ifOpened: => F[A])(ifNotOpened: => F[A])(
    implicit gameState: GameState
  ): F[A] =
    if (gameState.openedCoords.contains(coord))
      ifOpened
    else
      ifNotOpened

}
