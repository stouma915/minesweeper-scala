package net.st915.minesweeper.ui.component.impl

import cats.effect.unsafe.IORuntime
import cats.effect.{IO, Sync}
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.*
import net.st915.minesweeper.{Consts, Coordinate}
import org.scalajs.dom.*

class SyncCell[
  F[_]: Sync: AppendElement: CreateElement: UpdateElementClickEvent: UpdateElementID: UpdateElementRightClickEvent: UpdateHTMLClass: FlagIcon: FlagPlaceholderIcon: IconContainer: MineCountContainer: MineIcon
] extends Cell[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  override def create(coord: Coordinate)(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement] =
    for {
      cell <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(cell, Consts.NotOpenedCellClass)
      _ <- UpdateElementID[F].update(cell, s"cell_${coord.x}_${coord.y}")
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
        s"flag_${coord.x}_${coord.y}",
        flagIcon
      )
      _ <- AppendElement[F].append(cell, flagIconContainer)
      flagPlaceholderIcon <- FlagPlaceholderIcon[F].create
      flagPlaceholderIconContainer <- IconContainer[F].create(
        s"flagPlaceholder_${coord.x}_${coord.y}",
        flagPlaceholderIcon
      )
      _ <- AppendElement[F].append(cell, flagPlaceholderIconContainer)
      mineIcon <- MineIcon[F].create
      mineIconContainer <- IconContainer[F].create(
        s"mine_${coord.x}_${coord.y}",
        mineIcon
      )
      _ <- AppendElement[F].append(cell, mineIconContainer)
      mineCountContainers <-
        (1 to 8).toList.map { i =>
          MineCountContainer[F].create(s"${i}_${coord.x}_${coord.y}", i)
        }.sequence
      _ <-
        mineCountContainers.map { mineCountContainer =>
          AppendElement[F].append(cell, mineCountContainer)
        }.sequence
    } yield cell

}
