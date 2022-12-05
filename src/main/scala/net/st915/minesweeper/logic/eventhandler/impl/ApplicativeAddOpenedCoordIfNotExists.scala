package net.st915.minesweeper.logic.eventhandler.impl

import cats.Applicative
import net.st915.minesweeper.logic.eventhandler.application.*
import net.st915.minesweeper.{Coordinate, GameState}

class ApplicativeAddOpenedCoordIfNotExists[F[_]: Applicative: AddOpenedCoord: DoNothing]
    extends AddOpenedCoordIfNotExists[F] {

  override def add(coord: Coordinate)(implicit gameState: GameState): F[GameState] =
    if (gameState.openedCoords.contains(coord))
      DoNothing[F].perform
    else
      AddOpenedCoord[F].add(coord)

}
