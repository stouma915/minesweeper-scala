package net.st915.minesweeper.logic.eventhandler.application

import net.st915.minesweeper.{Coordinate, GameState}

object RemoveFlaggedCoord {

  def apply[F[_]: RemoveFlaggedCoord]: RemoveFlaggedCoord[F] = implicitly

}

trait RemoveFlaggedCoord[F[_]] {

  def remove(coord: Coordinate)(implicit gameState: GameState): F[GameState]

}
