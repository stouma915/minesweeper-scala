package net.st915.minesweeper.idfactory.instances

import cats.Monad
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.idfactory.CanCreateID
import net.st915.minesweeper.ui.components.GameScreen.{
  Cell,
  FlagIcon,
  FlagPlaceholderIcon,
  MineCountContainer,
  MineIcon
}
import net.st915.typesafescalajs.elements.properties.ID

trait CanCreateIDInstances {

  given monadCanCreateCellID[F[_]: Monad]: CanCreateID[F, Cell] with
    override def apply(coord: Coordinate): F[ID] =
      Monad[F].pure {
        ID(s"cell_${coord.x}_${coord.y}")
      }

  given monadCanCreateFlagContainerID[F[_]: Monad]: CanCreateID[F, FlagIcon] with
    override def apply(coord: Coordinate): F[ID] =
      Monad[F].pure {
        ID(s"iconContainer_flag_${coord.x}_${coord.y}")
      }

  given monadCanCreateFlagPlaceholderContainerID[F[_]: Monad]: CanCreateID[F, FlagPlaceholderIcon]
    with
    override def apply(coord: Coordinate): F[ID] =
      Monad[F].pure {
        ID(s"iconContainer_flagPlaceholder_${coord.x}_${coord.y}")
      }

  given monadCanCreateMineContainerID[F[_]: Monad]: CanCreateID[F, MineIcon] with
    override def apply(coord: Coordinate): F[ID] =
      Monad[F].pure {
        ID(s"iconContainer_mine_${coord.x}_${coord.y}")
      }

  given monadCanCreateMineCountContainerID[F[_]: Monad]: CanCreateID[F, MineCountContainer] with
    override def apply(coord: Coordinate): F[ID] =
      Monad[F].pure {
        ID(s"mineCountContainer_${coord.x}_${coord.y}")
      }

}
