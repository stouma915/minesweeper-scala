package net.st915.minesweeper.ui.application

import org.scalajs.dom.HTMLLinkElement

object UpdateHyperlink {

  def apply[F[_]: UpdateHyperlink]: UpdateHyperlink[F] = implicitly

}

trait UpdateHyperlink[F[_]] {

  def update(element: HTMLLinkElement, hyperlink: String): F[Unit]

}
