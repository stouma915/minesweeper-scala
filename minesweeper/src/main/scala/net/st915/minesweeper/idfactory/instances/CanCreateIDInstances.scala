package net.st915.minesweeper.idfactory.instances

import cats.Monad
import net.st915.immutablescalajs.dom.properties.ID
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.idfactory.CanCreateID
import net.st915.minesweeper.ui.types.*

trait CanCreateIDInstances {

  import net.st915.immutablescalajs.dom.syntax.propertySyntax.*

  given monadCanCreateCellID[F[_]: Monad]: CanCreateID[F, Cell] with
    override def apply(coord: Coordinate): F[ID] =
      Monad[F].pure {
        s"cell_${coord.x}_${coord.y}".asID
      }

  given monadCanCreateFlagContainerID[F[_]: Monad]: CanCreateID[F, FlagIcon] with
    override def apply(coord: Coordinate): F[ID] =
      Monad[F].pure {
        s"iconContainer_flag_${coord.x}_${coord.y}".asID
      }

  given monadCanCreateFlagPlaceholderContainerID[F[_]: Monad]: CanCreateID[F, FlagPlaceholderIcon]
    with
    override def apply(coord: Coordinate): F[ID] =
      Monad[F].pure {
        s"iconContainer_flagPlaceholder_${coord.x}_${coord.y}".asID
      }

  given monadCanCreateMineContainerID[F[_]: Monad]: CanCreateID[F, MineIcon] with
    override def apply(coord: Coordinate): F[ID] =
      Monad[F].pure {
        s"iconContainer_mine_${coord.x}_${coord.y}".asID
      }

  given monadCanCreateMineCountContainerID[F[_]: Monad]: CanCreateID[F, MineCountContainer] with
    override def apply(coord: Coordinate): F[ID] =
      Monad[F].pure {
        s"mineCountContainer_${coord.x}_${coord.y}".asID
      }

}
