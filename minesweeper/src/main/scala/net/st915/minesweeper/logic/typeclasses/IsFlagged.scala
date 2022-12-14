package net.st915.minesweeper.logic.typeclasses

import net.st915.minesweeper.{Coordinate, GameState}

object IsFlagged {

  def apply[F[_]](using ev: IsFlagged[F]): IsFlagged[F] = ev

}

trait IsFlagged[F[_]] {

  def check(coord: Coordinate)(using GameState): F[Boolean]

}
