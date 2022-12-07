package net.st915.minesweeper.ui.components.typeclasses

import org.scalajs.dom.*

object CanCreateDivElement {

  def apply[F[_]](using ev: CanCreateDivElement[F]): CanCreateDivElement[F] = ev

}

trait CanCreateDivElement[F[_]] extends CanCreateElement[F, HTMLDivElement] {

  def create(using HTMLDocument): F[HTMLDivElement]

}
