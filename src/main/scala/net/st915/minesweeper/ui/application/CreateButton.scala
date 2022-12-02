package net.st915.minesweeper.ui.application

import cats.effect.IO
import cats.effect.unsafe.IORuntime
import org.scalajs.dom.*

object CreateButton {

  def apply[F[_]: CreateButton]: CreateButton[F] = implicitly

}

trait CreateButton[F[_]] {

  def create(text: String, id: String, onClick: IO[Unit])(
    implicit document: HTMLDocument,
    runtime: IORuntime
  ): F[HTMLDivElement]

}
