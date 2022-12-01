package net.st915.minesweeper.ui.component.application

import org.scalajs.dom.*

object AboutPage {

  def apply[F[_]: AboutPage]: AboutPage[F] = implicitly

}

trait AboutPage[F[_]] {

  def create(implicit document: HTMLDocument): F[HTMLDivElement]

}
