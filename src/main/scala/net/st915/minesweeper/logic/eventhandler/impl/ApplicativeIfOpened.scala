package net.st915.minesweeper.logic.eventhandler.impl

import cats.Applicative
import net.st915.minesweeper.logic.eventhandler.application.IfOpened
import net.st915.minesweeper.{Coordinate, GameState}

class ApplicativeIfOpened[F[_]: Applicative] extends IfOpened[F] {

  override def perform(coord: Coordinate)(ifOpened: => F[GameState])(ifNotOpened: => F[GameState])(
    implicit gameState: GameState
  ): F[GameState] =
    if (gameState.openedCoords.contains(coord))
      ifOpened
    else
      ifNotOpened

}
