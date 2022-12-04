package net.st915.minesweeper.ui.component

import cats.effect.unsafe.IORuntime
import cats.effect.{IO, Sync}
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.event.{CellClickEvent, CellRightClickEvent}
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.impl.*
import net.st915.minesweeper.ui.util.application.IDFactory
import net.st915.minesweeper.ui.util.impl.ApplicativeIDFactory
import net.st915.minesweeper.{Coordinate, EventQueue}
import org.scalajs.dom.*

object Cell {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  def wired[F[_]: Sync](coord: Coordinate)(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement] = {
    implicit val _appendElement: AppendElement[F] = SyncAppendElement[F]
    implicit val _createElement: CreateElement[F] = SyncCreateElement[F]
    implicit val _createDiv: CreateDiv[F] = SyncCreateDiv[F]
    implicit val _updateElementID: UpdateElementID[F] = SyncUpdateElementID[F]
    implicit val _updateHTMLClass: UpdateHTMLClass[F] = SyncUpdateHTMLClass[F]
    implicit val _updateElementClickEvent: UpdateElementClickEvent[F] =
      SyncUpdateElementClickEvent[F]
    implicit val _updateElementRightClickEvent: UpdateElementRightClickEvent[F] =
      SyncUpdateElementRightClickEvent[F]

    implicit val _idFactory: IDFactory[F] = ApplicativeIDFactory[F]

    Cell(coord)
  }

  def apply[
    F[_]: Sync: AppendElement: CreateDiv: UpdateElementClickEvent: UpdateElementID: UpdateElementRightClickEvent: UpdateHTMLClass: IDFactory
  ](coord: Coordinate)(implicit document: HTMLDocument, runtime: IORuntime): F[HTMLDivElement] =
    for {
      cell <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(cell, CSSClass.NotOpenedCell)
      cellID <- IDFactory[F].cell(coord)
      _ <- UpdateElementID[F].update(cell, cellID)
      _ <- UpdateElementClickEvent[F].update(
        cell,
        EventQueue.queue[IO](CellClickEvent(coord))
      )
      _ <- UpdateElementRightClickEvent[F].update(
        cell,
        EventQueue.queue[IO](CellRightClickEvent(coord))
      )
      flagIcon <- FlagIcon.wired[F]
      flagIconContainerID <- IDFactory[F].iconContainer(CSSClass.FlagIcon, coord)
      flagIconContainer <- IconContainer.wired[F, HTMLDivElement](flagIconContainerID, flagIcon)
      _ <- AppendElement[F].append(cell, flagIconContainer)
      flagPlaceholderIcon <- FlagPlaceholderIcon.wired[F]
      flagPlaceholderIconContainerID <- IDFactory[F].iconContainer(
        CSSClass.FlagPlaceholderIcon,
        coord
      )
      flagPlaceholderIconContainer <- IconContainer.wired[F, HTMLDivElement](
        flagPlaceholderIconContainerID,
        flagPlaceholderIcon
      )
      _ <- AppendElement[F].append(cell, flagPlaceholderIconContainer)
      mineIcon <- MineIcon.wired[F]
      mineIconContainerID <- IDFactory[F].iconContainer(CSSClass.MineIcon, coord)
      mineIconContainer <- IconContainer.wired[F, HTMLDivElement](mineIconContainerID, mineIcon)
      _ <- AppendElement[F].append(cell, mineIconContainer)
      mineCountContainers <-
        (1 to 8).toList.map { i =>
          for {
            mineCountContainerId <- IDFactory[F].mineCountContainer(i, coord)
            mineCountContainer <- MineCountContainer.wired[F](mineCountContainerId, i)
          } yield mineCountContainer
        }.sequence
      _ <-
        mineCountContainers.map { mineCountContainer =>
          AppendElement[F].append(cell, mineCountContainer)
        }.sequence
    } yield cell
}
