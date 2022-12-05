package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object UpdateHTMLClassWithID {

  def apply[F[_]: UpdateHTMLClassWithID]: UpdateHTMLClassWithID[F] = implicitly

}

trait UpdateHTMLClassWithID[F[_]] {

  def update(id: String, htmlClass: String)(implicit document: HTMLDocument): F[Unit]

}
