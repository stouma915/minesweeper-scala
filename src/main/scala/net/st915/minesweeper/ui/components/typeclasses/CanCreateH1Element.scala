package net.st915.minesweeper.ui.components.typeclasses

import org.scalajs.dom.*

object CanCreateH1Element {

  def apply[F[_]](using ev: CanCreateH1Element[F]): CanCreateH1Element[F] = ev

}

trait CanCreateH1Element[F[_]] extends CanCreateElement[F, HTMLElement] {

  def create(using HTMLDocument): F[HTMLElement]

}
