package net.st915.minesweeper.util.instances

import cats.Monad
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.util.ID
import net.st915.minesweeper.util.typeclasses.CanCreateCellID

class MonadCanCreateCellID[F[_]: Monad] extends CanCreateCellID[F] {

  override def create(coord: Coordinate): F[ID] =
    Monad[F].pure {
      ID(s"cell_${coord.x}_${coord.y}")
    }

}
