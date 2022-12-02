package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object CreateDiv {

  def apply[F[_]: CreateDiv]: CreateDiv[F] = implicitly

}

trait CreateDiv[F[_]] {

  def create(implicit document: HTMLDocument): F[HTMLDivElement]

}
