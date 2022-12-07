package net.st915.minesweeper.util.instances

import cats.Monad
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.ui.components.FlagIcon
import net.st915.minesweeper.util.ID
import net.st915.minesweeper.util.typeclasses.CanCreateIconContainerID

class MonadCanCreateIconContainerIDFlag[F[_]: Monad] extends CanCreateIconContainerID[F, FlagIcon] {

  override def create(coord: Coordinate): F[ID] =
    Monad[F].pure {
      ID(s"iconContainer_flag_${coord.x}_${coord.y}")
    }

}
