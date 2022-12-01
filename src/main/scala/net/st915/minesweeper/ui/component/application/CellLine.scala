package net.st915.minesweeper.ui.component.application

import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.{Coordinate, Difficulty}
import org.scalajs.dom.*

object CellLine {

  def apply[F[_]: CellLine]: CellLine[F] = implicitly

}

trait CellLine[F[_]] {

  def create(y: Int, difficulty: Difficulty)(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement]

}
