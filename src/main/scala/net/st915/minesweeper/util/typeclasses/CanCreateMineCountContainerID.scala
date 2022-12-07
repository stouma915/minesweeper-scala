package net.st915.minesweeper.util.typeclasses

import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.util.ID

object CanCreateMineCountContainerID {

  def apply[F[_]](using ev: CanCreateMineCountContainerID[F]): CanCreateMineCountContainerID[F] = ev

}

trait CanCreateMineCountContainerID[F[_]] {

  def create(coord: Coordinate): F[ID]

}
