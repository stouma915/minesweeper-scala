package net.st915.minesweeper.logic.refreshui.application

import org.scalajs.dom.*

object UpdateTextContent {

  def apply[F[_]: UpdateTextContent]: UpdateTextContent[F] = implicitly

}

trait UpdateTextContent[F[_]] {

  def update(id: String, text: String)(implicit document: HTMLDocument): F[Unit]

}
