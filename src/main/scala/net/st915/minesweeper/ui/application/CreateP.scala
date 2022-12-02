package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object CreateP {

  def apply[F[_]: CreateP]: CreateP[F] = implicitly

}

trait CreateP[F[_]] {

  def create(implicit document: HTMLDocument): F[HTMLParagraphElement]

}
