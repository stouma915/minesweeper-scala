package net.st915.minesweeper.logic.application

import net.st915.minesweeper.{Coordinate, GameState}

object AddOpenedCoord {

  def apply[F[_]: AddOpenedCoord]: AddOpenedCoord[F] = implicitly

}

trait AddOpenedCoord[F[_]] {

  def add(coord: Coordinate)(implicit gameState: GameState): F[GameState]

}
