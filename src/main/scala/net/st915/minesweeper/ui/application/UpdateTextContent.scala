package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object UpdateTextContent {

  def apply[F[_]: UpdateTextContent]: UpdateTextContent[F] = implicitly

}

trait UpdateTextContent[F[_]] {

  def update[A <: HTMLElement](element: A, text: String)(implicit document: HTMLDocument): F[Unit]

}
