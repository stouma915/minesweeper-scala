package net.st915.minesweeper.logic.impl

import cats.Applicative
import net.st915.minesweeper.logic.application.RemoveFlaggedCoord
import net.st915.minesweeper.{Coordinate, GameState}

class ApplicativeRemoveFlaggedCoord[F[_]: Applicative] extends RemoveFlaggedCoord[F] {

  override def remove(coord: Coordinate)(implicit gameState: GameState): F[GameState] =
    Applicative[F].pure(gameState.copy(flaggedCoords = gameState.flaggedCoords.filter(_ != coord)))

}
