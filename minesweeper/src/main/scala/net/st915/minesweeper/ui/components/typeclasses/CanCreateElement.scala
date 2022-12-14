package net.st915.minesweeper.ui.components.typeclasses

import org.scalajs.dom.*

object CanCreateElement {

  def apply[F[_], A](using ev: CanCreateElement[F, A]): CanCreateElement[F, A] = ev

}

trait CanCreateElement[F[_], A] {

  def create(using HTMLDocument): F[A]

}
