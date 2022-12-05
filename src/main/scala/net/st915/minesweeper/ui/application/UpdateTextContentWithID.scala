package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object UpdateTextContentWithID {

  def apply[F[_]: UpdateTextContentWithID]: UpdateTextContentWithID[F] = implicitly

}

trait UpdateTextContentWithID[F[_]] {

  def update(id: String, text: String)(implicit document: HTMLDocument): F[Unit]

}
