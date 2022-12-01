package net.st915.minesweeper.ui.application

import org.scalajs.dom.HTMLElement

object UpdateHTMLClass {

  def apply[F[_]: UpdateHTMLClass]: UpdateHTMLClass[F] = implicitly

}

trait UpdateHTMLClass[F[_]] {

  def update[A <: HTMLElement](element: A, htmlClass: String): F[Unit]

}
