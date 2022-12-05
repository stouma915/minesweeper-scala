package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object UpdateElementVisibility {

  def apply[F[_]: UpdateElementVisibility]: UpdateElementVisibility[F] = implicitly

}

trait UpdateElementVisibility[F[_]] {

  def update[A <: HTMLElement](element: A, visible: Boolean): F[Unit]

}
