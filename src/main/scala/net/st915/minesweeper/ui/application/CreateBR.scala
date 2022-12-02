package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object CreateBR {

  def apply[F[_]: CreateBR]: CreateBR[F] = implicitly

}

trait CreateBR[F[_]] {

  def create(implicit document: HTMLDocument): F[HTMLBRElement]

}
