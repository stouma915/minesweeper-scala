package net.st915.minesweeper.logic.gamelogics.typeclasses

import net.st915.minesweeper.{Coordinate, GameState}

object CanAddFlagged {

  def apply[F[_]](using ev: CanAddFlagged[F]): CanAddFlagged[F] = ev

}

trait CanAddFlagged[F[_]] {

  def perform(coord: Coordinate)(using GameState): F[GameState]

}
