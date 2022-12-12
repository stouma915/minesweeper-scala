package net.st915.minesweeper.logic.gamelogics.typeclasses

import net.st915.minesweeper.{Coordinate, GameState}

object CanRemoveFlagged {

  def apply[F[_]](using ev: CanRemoveFlagged[F]): CanRemoveFlagged[F] = ev

}

trait CanRemoveFlagged[F[_]] {

  def perform(coord: Coordinate)(using GameState): F[GameState]

}
