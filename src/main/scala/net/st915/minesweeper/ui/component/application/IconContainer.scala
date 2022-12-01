package net.st915.minesweeper.ui.component.application

import org.scalajs.dom.*

object IconContainer {

  def apply[F[_]: IconContainer]: IconContainer[F] = implicitly

}

trait IconContainer[F[_]] {

  def create[A <: HTMLElement](id: String, icon: A)(implicit
  document: HTMLDocument): F[HTMLDivElement]

}
