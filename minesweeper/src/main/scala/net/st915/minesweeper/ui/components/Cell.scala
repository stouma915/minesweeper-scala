package net.st915.minesweeper.ui.components

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.event.{CellClickEvent, CellRightClickEvent}
import net.st915.minesweeper.ui.components.instances.*
import net.st915.minesweeper.ui.components.typeclasses.*
import net.st915.minesweeper.ui.consts.CSSClasses
import net.st915.minesweeper.util.instances.MonadCanCreateCellID
import net.st915.minesweeper.util.typeclasses.CanCreateCellID
import net.st915.minesweeper.{Coordinate, EventQueue}
import org.scalajs.dom.*

object Cell {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](coord: Coordinate)(using HTMLDocument, IORuntime): F[HTMLDivElement] = {
    given CanAppendElement[F] = SyncCanAppendElement[F]
    given CanCreateElement[F, HTMLDivElement] = MonadCanCreateElementDiv[F]
    given CanUpdateElementClass[F] = SyncCanUpdateElementClass[F]
    given CanUpdateElementID[F] = SyncCanUpdateElementID[F]
    given CanUpdateClickEvent[F] = SyncCanUpdateClickEvent[F]
    given CanUpdateRightClickEvent[F] = SyncCanUpdateRightClickEvent[F]

    given CanCreateIconContainer[F, FlagIcon] =
      SyncCanCreateIconContainerFlag[F]
    given CanCreateIconContainer[F, FlagPlaceholderIcon] =
      SyncCanCreateIconContainerFlagPlaceholder[F]
    given CanCreateIconContainer[F, MineIcon] =
      SyncCanCreateIconContainerMine[F]

    given CanCreateCellID[F] = MonadCanCreateCellID[F]

    for {
      containerDiv <- CanCreateElement[F, HTMLDivElement].create
      _ <- CanUpdateElementClass[F].perform(containerDiv, CSSClasses.NotOpenedCell)

      _ <- CanUpdateClickEvent[F].perform(
        containerDiv,
        EventQueue.queue[F](CellClickEvent(coord))
      )
      _ <- CanUpdateRightClickEvent[F].perform(
        containerDiv,
        EventQueue.queue[F](CellRightClickEvent(coord))
      )

      containerID <- CanCreateCellID[F].create(coord)
      _ <- CanUpdateElementID[F].perform(containerDiv, containerID)

      flagIcon <- FlagIcon.wired[F]
      flagContainer <- CanCreateIconContainer[F, FlagIcon].create(coord)
      _ <- CanAppendElement[F].perform(flagContainer, flagIcon)
      _ <- CanAppendElement[F].perform(containerDiv, flagContainer)

      placeholderIcon <- FlagPlaceholderIcon.wired[F]
      placeholderContainer <- CanCreateIconContainer[F, FlagPlaceholderIcon].create(coord)
      _ <- CanAppendElement[F].perform(placeholderContainer, placeholderIcon)
      _ <- CanAppendElement[F].perform(containerDiv, placeholderContainer)

      mineIcon <- MineIcon.wired[F]
      mineContainer <- CanCreateIconContainer[F, MineIcon].create(coord)
      _ <- CanAppendElement[F].perform(mineContainer, mineIcon)
      _ <- CanAppendElement[F].perform(containerDiv, mineContainer)

      mineCountContainer <- MineCountContainer.wired[F](coord)
      _ <- CanAppendElement[F].perform(containerDiv, mineCountContainer)
    } yield containerDiv
  }

}
