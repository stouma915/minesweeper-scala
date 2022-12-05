package net.st915.minesweeper.logic.eventhandler.impl

import cats.Applicative
import net.st915.minesweeper.logic.eventhandler.application.IfFlagged
import net.st915.minesweeper.{Coordinate, GameState}

class ApplicativeIfFlagged[F[_]: Applicative] extends IfFlagged[F] {

  override def perform(
    coord: Coordinate
  )(ifFlagged: => F[GameState])(ifNotFlagged: => F[GameState])(
    implicit gameState: GameState
  ): F[GameState] =
    if (gameState.flaggedCoords.contains(coord))
      ifFlagged
    else
      ifNotFlagged

}
