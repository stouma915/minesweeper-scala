package net.st915.minesweeper.util.instances

import cats.Monad
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.ui.components.FlagPlaceholderIcon
import net.st915.minesweeper.util.ID
import net.st915.minesweeper.util.typeclasses.CanCreateIconContainerID

class MonadCanCreateIconContainerIDFlagPlaceholder[F[_]: Monad]
    extends CanCreateIconContainerID[F, FlagPlaceholderIcon] {

  override def create(coord: Coordinate): F[ID] =
    Monad[F].pure {
      ID(s"iconContainer_flagPlaceholder_${coord.x}_${coord.y}")
    }

}
