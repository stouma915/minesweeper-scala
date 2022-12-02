package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.*
import net.st915.minesweeper.{Coordinate, Difficulty}
import org.scalajs.dom.*

class SyncCellLine[
  F[_]: Sync: AppendElement: CreateElement: UpdateHTMLClass: Cell
] extends CellLine[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  override def create(y: Int, difficulty: Difficulty)(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement] =
    for {
      cellLine <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(cellLine, CSSClass.CellLine)
      cells <-
        (0 until difficulty.width).toList.map { x => Cell[F].create(Coordinate(x, y)) }.sequence
      _ <-
        cells.map { cell => AppendElement[F].append(cellLine, cell) }.sequence
    } yield cellLine

}
