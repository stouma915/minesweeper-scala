package net.st915.minesweeper.logic.eventhandler.impl

import cats.Applicative
import net.st915.minesweeper.logic.eventhandler.application.AddFlaggedCoord
import net.st915.minesweeper.{Coordinate, GameState}

class ApplicativeAddFlaggedCoord[F[_]: Applicative] extends AddFlaggedCoord[F] {

  override def add(coord: Coordinate)(implicit gameState: GameState): F[GameState] =
    Applicative[F].pure(gameState.copy(flaggedCoords = gameState.flaggedCoords.appended(coord)))

}
