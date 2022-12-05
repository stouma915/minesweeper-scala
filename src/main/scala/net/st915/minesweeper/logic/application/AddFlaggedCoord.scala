package net.st915.minesweeper.logic.application

import net.st915.minesweeper.{Coordinate, GameState}

object AddFlaggedCoord {

  def apply[F[_]: AddFlaggedCoord]: AddFlaggedCoord[F] = implicitly

}

trait AddFlaggedCoord[F[_]] {

  def add(coord: Coordinate)(implicit gameState: GameState): F[GameState]

}
