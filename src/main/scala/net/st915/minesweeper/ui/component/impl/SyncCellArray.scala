package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.*
import net.st915.minesweeper.{Coordinate, Difficulty}
import org.scalajs.dom.*

class SyncCellArray[
  F[_]: Sync: AppendElement: CreateDiv: UpdateHTMLClass: CellLine
] extends CellArray[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  override def create(difficulty: Difficulty)(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement] =
    for {
      cellArray <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(cellArray, CSSClass.CellArray)
      lines <-
        (0 until difficulty.height).toList.map { y =>
          CellLine[F].create(
            y,
            difficulty
          )
        }.sequence
      _ <-
        lines.map { line => AppendElement[F].append(cellArray, line) }.sequence
    } yield cellArray

}
