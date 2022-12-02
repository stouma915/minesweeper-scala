package net.st915.minesweeper.logic.eventloop.application

import cats.effect.unsafe.IORuntime
import org.scalajs.dom.*

object Loop {

  def apply[F[_]: Loop]: Loop[F] = implicitly

}

trait Loop[F[_]] {

  def perform(task: F[Unit])(implicit window: Window, runtime: IORuntime): F[Unit]

}
