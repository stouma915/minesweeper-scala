package net.st915.minesweeper.ui.components.instances

import cats.effect.Sync
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.ui.components.FlagIcon
import net.st915.minesweeper.ui.consts.CSSClasses
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.util.typeclasses.CanCreateIconContainerID
import net.st915.minesweeper.util.instances.MonadCanCreateIconContainerIDFlag
import org.scalajs.dom.*

class SyncCanCreateIconContainerFlag[F[_]: Sync] extends CanCreateIconContainer[F, FlagIcon] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(coord: Coordinate)(using HTMLDocument): F[HTMLDivElement] = {
    given CanCreateElement[F, HTMLDivElement] = MonadCanCreateElementDiv[F]
    given CanUpdateElementClass[F] = SyncCanUpdateElementClass[F]
    given CanUpdateElementID[F] = SyncCanUpdateElementID[F]

    given CanCreateIconContainerID[F, FlagIcon] = MonadCanCreateIconContainerIDFlag[F]

    for {
      containerDiv <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(containerDiv, CSSClasses.IconContainer)

      containerID <- CanCreateIconContainerID[F, FlagIcon].create(coord)
      _ <- CanUpdateElementID[F].perform(containerDiv, containerID)
    } yield containerDiv
  }

}
