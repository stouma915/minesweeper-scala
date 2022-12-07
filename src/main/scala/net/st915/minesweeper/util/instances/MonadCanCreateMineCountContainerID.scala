package net.st915.minesweeper.util.instances

import cats.Monad
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.util.ID
import net.st915.minesweeper.util.typeclasses.CanCreateMineCountContainerID

class MonadCanCreateMineCountContainerID[F[_]: Monad] extends CanCreateMineCountContainerID[F] {

  override def create(coord: Coordinate): F[ID] =
    Monad[F].pure {
      ID(s"mineCountContainer_${coord.x}_${coord.y}")
    }

}
