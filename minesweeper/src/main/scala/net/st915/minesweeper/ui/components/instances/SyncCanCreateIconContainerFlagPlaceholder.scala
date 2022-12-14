package net.st915.minesweeper.ui.components.instances

import cats.effect.Sync
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.ui.components.FlagPlaceholderIcon
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.consts.CSSClasses
import net.st915.minesweeper.util.instances.MonadCanCreateIconContainerIDFlagPlaceholder
import net.st915.minesweeper.util.typeclasses.CanCreateIconContainerID
import org.scalajs.dom.*

class SyncCanCreateIconContainerFlagPlaceholder[F[_]: Sync]
    extends CanCreateIconContainer[F, FlagPlaceholderIcon] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  override def create(coord: Coordinate)(using HTMLDocument): F[HTMLDivElement] = {
    given CanCreateElement[F, HTMLDivElement] = MonadCanCreateElementDiv[F]
    given CanUpdateElementClass[F] = SyncCanUpdateElementClass[F]
    given CanUpdateElementID[F] = SyncCanUpdateElementID[F]

    given CanCreateIconContainerID[F, FlagPlaceholderIcon] =
      MonadCanCreateIconContainerIDFlagPlaceholder[F]

    for {
      containerDiv <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(containerDiv, CSSClasses.IconContainer)

      containerID <- CanCreateIconContainerID[F, FlagPlaceholderIcon].create(coord)
      _ <- CanUpdateElementID[F].perform(containerDiv, containerID)
    } yield containerDiv
  }

}
