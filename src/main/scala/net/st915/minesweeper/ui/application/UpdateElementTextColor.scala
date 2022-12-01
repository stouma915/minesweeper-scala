package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object UpdateElementTextColor {

  def apply[F[_]: UpdateElementTextColor]: UpdateElementTextColor[F] = implicitly

}

trait UpdateElementTextColor[F[_]] {

  def update[A <: HTMLElement](element: A, color: String): F[Unit]

}
