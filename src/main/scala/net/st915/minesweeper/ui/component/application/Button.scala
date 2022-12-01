package net.st915.minesweeper.ui.component.application

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import org.scalajs.dom.*

object Button {

  def apply[F[_]: Button]: Button[F] = implicitly

}

trait Button[F[_]] {

  def create(text: String, id: String, onClick: IO[Unit])(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement]

}
