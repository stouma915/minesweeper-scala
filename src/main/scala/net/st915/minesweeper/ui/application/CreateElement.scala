package net.st915.minesweeper.ui.application

import org.scalajs.dom.*

object CreateElement {

  def apply[F[_]: CreateElement]: CreateElement[F] = implicitly

}

trait CreateElement[F[_]] {

  def create[A <: HTMLElement](tagName: String)(implicit document: HTMLDocument): F[A]

}
