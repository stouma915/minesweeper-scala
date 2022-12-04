package net.st915.minesweeper.ui.component

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.impl.*
import net.st915.minesweeper.{Coordinate, Difficulty}
import org.scalajs.dom.*

object CellLine {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  def wired[F[_]: Sync](y: Int,
                        difficulty: Difficulty
  )(implicit document: HTMLDocument, runtime: IORuntime): F[HTMLDivElement] = {
    implicit val _appendElement: AppendElement[F] = SyncAppendElement[F]
    implicit val _createElement: CreateElement[F] = SyncCreateElement[F]
    implicit val _createDiv: CreateDiv[F] = SyncCreateDiv[F]
    implicit val _updateHTMLClass: UpdateHTMLClass[F] = SyncUpdateHTMLClass[F]

    CellLine(y, difficulty)
  }

  def apply[
    F[_]: Sync: AppendElement: CreateDiv: UpdateHTMLClass
  ](y: Int,
    difficulty: Difficulty
  )(implicit document: HTMLDocument, runtime: IORuntime): F[HTMLDivElement] =
    for {
      cellLine <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(cellLine, CSSClass.CellLine)
      cells <-
        (0 until difficulty.width).toList.map {
          x => Cell.wired[F](Coordinate(x, y))
        }.sequence
      _ <-
        cells.map { cell => AppendElement[F].append(cellLine, cell) }.sequence
    } yield cellLine
}
