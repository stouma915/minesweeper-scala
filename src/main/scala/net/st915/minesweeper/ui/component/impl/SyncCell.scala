package net.st915.minesweeper.ui.component.impl

import cats.effect.unsafe.IORuntime
import cats.effect.{IO, Sync}
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.*
import net.st915.minesweeper.ui.util.application.IDFactory
import org.scalajs.dom.*

class SyncCell[
  F[_]: Sync: AppendElement: CreateDiv: UpdateElementClickEvent: UpdateElementID: UpdateElementRightClickEvent: UpdateHTMLClass: FlagIcon: FlagPlaceholderIcon: IconContainer: MineCountContainer: MineIcon: IDFactory
] extends Cell[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  override def create(coord: Coordinate)(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement] =
    for {
      cell <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(cell, CSSClass.NotOpenedCell)
      cellID <- IDFactory[F].cell(coord)
      _ <- UpdateElementID[F].update(cell, cellID)
      _ <-
        UpdateElementClickEvent[F].update(
          cell,
          IO {
            // TODO
            println(s"clicked: $coord")
          }
        )
      _ <-
        UpdateElementRightClickEvent[F].update(
          cell,
          IO {
            // TODO
            println(s"right clicked: $coord")
          }
        )
      flagIcon <- FlagIcon[F].create
      flagIconContainerID <- IDFactory[F].iconContainer(CSSClass.FlagIcon, coord)
      flagIconContainer <- IconContainer[F].create(flagIconContainerID, flagIcon)
      _ <- AppendElement[F].append(cell, flagIconContainer)
      flagPlaceholderIcon <- FlagPlaceholderIcon[F].create
      flagPlaceholderIconContainerID <- IDFactory[F].iconContainer(
        CSSClass.FlagPlaceholderIcon,
        coord
      )
      flagPlaceholderIconContainer <- IconContainer[F].create(
        flagPlaceholderIconContainerID,
        flagPlaceholderIcon
      )
      _ <- AppendElement[F].append(cell, flagPlaceholderIconContainer)
      mineIcon <- MineIcon[F].create
      mineIconContainerID <- IDFactory[F].iconContainer(CSSClass.MineIcon, coord)
      mineIconContainer <- IconContainer[F].create(mineIconContainerID, mineIcon)
      _ <- AppendElement[F].append(cell, mineIconContainer)
      mineCountContainers <-
        (1 to 8).toList.map { i =>
          for {
            mineCountContainerId <- IDFactory[F].mineCountContainer(i, coord)
            mineCountContainer <- MineCountContainer[F].create(mineCountContainerId, i)
          } yield mineCountContainer
        }.sequence
      _ <-
        mineCountContainers.map { mineCountContainer =>
          AppendElement[F].append(cell, mineCountContainer)
        }.sequence
    } yield cell

}
