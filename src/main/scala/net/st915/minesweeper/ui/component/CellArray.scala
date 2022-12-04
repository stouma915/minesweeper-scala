package net.st915.minesweeper.ui.component

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.Consts.CSSClass
import net.st915.minesweeper.ui.application.*
import net.st915.minesweeper.ui.impl.*
import net.st915.minesweeper.{Coordinate, Difficulty}
import org.scalajs.dom.*

object CellArray {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  def wired[F[_]: Sync](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement] = {
    implicit val _appendElement: AppendElement[F] = SyncAppendElement[F]
    implicit val _createElement: CreateElement[F] = SyncCreateElement[F]
    implicit val _createDiv: CreateDiv[F] = SyncCreateDiv[F]
    implicit val _updateHTMLClass: UpdateHTMLClass[F] = SyncUpdateHTMLClass[F]

    CellArray(difficulty)
  }

  def apply[
    F[_]: Sync: AppendElement: CreateDiv: UpdateHTMLClass
  ](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement] =
    for {
      cellArray <- CreateDiv[F].create
      _ <- UpdateHTMLClass[F].update(cellArray, CSSClass.CellArray)
      lines <-
        (0 until difficulty.height).toList.map { y => CellLine.wired[F](y, difficulty) }.sequence
      _ <-
        lines.map { line => AppendElement[F].append(cellArray, line) }.sequence
    } yield cellArray

}
