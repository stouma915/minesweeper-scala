package net.st915.minesweeper.util.typeclasses

import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.util.ID

object CanCreateCellID {

  def apply[F[_]](using ev: CanCreateCellID[F]): CanCreateCellID[F] = ev

}

trait CanCreateCellID[F[_]] {

  def create(coord: Coordinate): F[ID]

}
