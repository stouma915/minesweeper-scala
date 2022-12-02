package net.st915.minesweeper.ui.component.impl

import cats.effect.unsafe.IORuntime
import cats.effect.{IO, Sync}
import net.st915.minesweeper.Consts.{CSSClass, ElementID}
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.*
import org.scalajs.dom.*

class SyncCell[
  F[_]: Sync: AppendElement: CreateDiv: UpdateElementClickEvent: UpdateElementID: UpdateElementRightClickEvent: UpdateHTMLClass: FlagIcon: FlagPlaceholderIcon: IconContainer: MineCountContainer: MineIcon
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
      _ <- UpdateElementID[F].update(
        cell,
        s"${ElementID.CellPrefix}${coord.x}${ElementID.Underscore}${coord.y}"
      )
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
      flagIconContainer <- IconContainer[F].create(
        s"${ElementID.FlagContainerPrefix}${coord.x}${ElementID.Underscore}${coord.y}",
        flagIcon
      )
      _ <- AppendElement[F].append(cell, flagIconContainer)
      flagPlaceholderIcon <- FlagPlaceholderIcon[F].create
      flagPlaceholderIconContainer <- IconContainer[F].create(
        s"${ElementID.FlagPlaceholderContainerPrefix}${coord.x}${ElementID.Underscore}${coord.y}",
        flagPlaceholderIcon
      )
      _ <- AppendElement[F].append(cell, flagPlaceholderIconContainer)
      mineIcon <- MineIcon[F].create
      mineIconContainer <- IconContainer[F].create(
        s"${ElementID.MineContainerPrefix}${coord.x}${ElementID.Underscore}${coord.y}",
        mineIcon
      )
      _ <- AppendElement[F].append(cell, mineIconContainer)
      mineCountContainers <-
        (1 to 8).toList.map { i =>
          MineCountContainer[F].create(
            s"$i${ElementID.Underscore}${coord.x}${ElementID.Underscore}${coord.y}",
            i
          )
        }.sequence
      _ <-
        mineCountContainers.map { mineCountContainer =>
          AppendElement[F].append(cell, mineCountContainer)
        }.sequence
    } yield cell

}
