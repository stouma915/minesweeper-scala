package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object CreateSpan {

  def apply[F[_]: CreateSpan]: CreateSpan[F] = implicitly

}

trait CreateSpan[F[_]] {

  def create(implicit document: HTMLDocument): F[HTMLSpanElement]

}
