package net.st915.minesweeper.ui.component.impl

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.{Coordinate, Difficulty}
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.component.application.*
import org.scalajs.dom.*

class SyncCellArray[F[
    _
]: Sync: AppendElement: CreateElement: UpdateHTMLClass: CellLine]
    extends CellArray[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  override def create(
      difficulty: Difficulty
  )(implicit document: HTMLDocument, runtime: IORuntime): F[HTMLDivElement] =
    for {
      cellArray <- CreateElement[F].create[HTMLDivElement]("div")
      _ <- UpdateHTMLClass[F].update(cellArray, "cellArray")
      lines <-
        (0 until difficulty.height).toList.map { y =>
          CellLine[F].create(
            y,
            difficulty
          )
        }.sequence
      _ <-
        lines.map { line =>
          AppendElement[F].append(cellArray, line)
        }.sequence
    } yield cellArray

}
