package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object UpdateElementVisibilityWithID {

  def apply[F[_]: UpdateElementVisibilityWithID]: UpdateElementVisibilityWithID[F] = implicitly

}

trait UpdateElementVisibilityWithID[F[_]] {

  def update(id: String, visible: Boolean)(implicit document: HTMLDocument): F[Unit]

}
