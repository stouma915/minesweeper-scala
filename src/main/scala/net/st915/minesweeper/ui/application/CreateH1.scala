package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object CreateH1 {

  def apply[F[_]: CreateH1]: CreateH1[F] = implicitly

}

trait CreateH1[F[_]] {

  def create(implicit document: HTMLDocument): F[HTMLElement]

}
