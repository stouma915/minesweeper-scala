package net.st915.minesweeper.logic.refreshui.application

import org.scalajs.dom.*

object GetElement {

  def apply[F[_]: GetElement]: GetElement[F] = implicitly

}

trait GetElement[F[_]] {

  def get[A <: HTMLElement](id: String)(implicit document: HTMLDocument): F[A]

}
