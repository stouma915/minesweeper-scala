package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object AppendBR {

  def apply[F[_]: AppendBR]: AppendBR[F] = implicitly

}

trait AppendBR[F[_]] {

  def append[A <: HTMLElement](parent: A)(implicit document: HTMLDocument): F[Unit]

}
