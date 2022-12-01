package net.st915.minesweeper.ui.application

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import org.scalajs.dom.*

object UpdateElementClickEvent {

  def apply[F[_]: UpdateElementClickEvent]: UpdateElementClickEvent[F] = implicitly

}

trait UpdateElementClickEvent[F[_]] {

  def update[A <: HTMLElement](element: A, onClick: IO[_])(implicit runtime: IORuntime): F[Unit]

}
