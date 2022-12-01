package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object AppendElement {

  def apply[F[_]: AppendElement]: AppendElement[F] = implicitly

}

trait AppendElement[F[_]] {

  def append[A <: HTMLElement](parent: A, child: A): F[Unit]

}
