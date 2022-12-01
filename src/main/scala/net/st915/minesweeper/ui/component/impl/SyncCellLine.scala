package net.st915.minesweeper.ui.component.impl

import cats.effect.{IO, Sync}
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.{Coordinate, Difficulty}
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.*
import org.scalajs.dom.*

class SyncCellLine[F[
    _
]: Sync: AppendElement: CreateElement: UpdateHTMLClass: Cell]
    extends CellLine[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  override def create(
      y: Int,
      difficulty: Difficulty,
      onCellClick: Coordinate => IO[Unit],
      onCellRightClick: Coordinate => IO[Unit]
  )(implicit document: HTMLDocument, runtime: IORuntime): F[HTMLDivElement] =
    for {
      cellLine <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(cellLine, "line")
      cells <-
        (0 until difficulty.width).toList.map { x =>
          Cell[F].create(
            Coordinate(x, y),
            onCellClick,
            onCellRightClick
          )
        }.sequence
      _ <-
        cells.map { cell =>
          AppendElement[F].append(cellLine, cell)
        }.sequence
    } yield cellLine

}
