package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object UpdateElementID {

  def apply[F[_]: UpdateElementID]: UpdateElementID[F] = implicitly

}

trait UpdateElementID[F[_]] {

  def update[A <: HTMLElement](element: A, elementID: String): F[Unit]

}
