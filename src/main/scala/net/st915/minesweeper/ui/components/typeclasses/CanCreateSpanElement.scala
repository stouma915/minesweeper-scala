package net.st915.minesweeper.ui.components.typeclasses

import org.scalajs.dom.*

object CanCreateSpanElement {

  def apply[F[_]](using ev: CanCreateSpanElement[F]): CanCreateSpanElement[F] = ev

}

trait CanCreateSpanElement[F[_]] extends CanCreateElement[F, HTMLSpanElement] {

  def create(using HTMLDocument): F[HTMLSpanElement]

}
