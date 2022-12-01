package net.st915.minesweeper.ui.application

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import org.scalajs.dom.*

object UpdateElementRightClickEvent {

  def apply[F[_]: UpdateElementRightClickEvent]
      : UpdateElementRightClickEvent[F] = implicitly

}

trait UpdateElementRightClickEvent[F[_]] {

  def update[A <: HTMLElement](
      element: A,
      onRightClick: IO[_]
  )(implicit runtime: IORuntime): F[Unit]

}
