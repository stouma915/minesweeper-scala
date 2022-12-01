package net.st915.minesweeper.ui.component.application

import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.{Coordinate, Difficulty}
import org.scalajs.dom.*

object Cell {

  def apply[F[_]: Cell]: Cell[F] = implicitly

}

trait Cell[F[_]] {

  def create(coord: Coordinate)(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement]

}