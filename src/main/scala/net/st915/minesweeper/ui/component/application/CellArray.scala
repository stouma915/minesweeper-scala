package net.st915.minesweeper.ui.component.application

import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.{Coordinate, Difficulty}
import org.scalajs.dom.*

object CellArray {

  def apply[F[_]: CellArray]: CellArray[F] = implicitly

}

trait CellArray[F[_]] {

  def create(difficulty: Difficulty)(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement]

}
