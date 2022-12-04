package net.st915.minesweeper.logic.eventhandler.application

import net.st915.minesweeper.{Coordinate, GameState}

object AddOpenedCoordIfNotExists {

  def apply[F[_]: AddOpenedCoordIfNotExists]: AddOpenedCoordIfNotExists[F] = implicitly

}

trait AddOpenedCoordIfNotExists[F[_]] {

  def add(coord: Coordinate)(implicit gameState: GameState): F[GameState]

}
