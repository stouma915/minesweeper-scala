package net.st915.minesweeper.util.instances

import cats.Monad
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.ui.components.MineIcon
import net.st915.minesweeper.util.ID
import net.st915.minesweeper.util.typeclasses.CanCreateIconContainerID

class MonadCanCreateIconContainerIDMine[F[_]: Monad] extends CanCreateIconContainerID[F, MineIcon] {

  override def create(coord: Coordinate): F[ID] =
    Monad[F].pure {
      ID(s"iconContainer_mine_${coord.x}_${coord.y}")
    }

}
