package net.st915.minesweeper.ui.components.typeclasses

import org.scalajs.dom.*

object CanCreateLinkElement {

  def apply[F[_]](using ev: CanCreateLinkElement[F]): CanCreateLinkElement[F] = ev

}

trait CanCreateLinkElement[F[_]] extends CanCreateElement[F, HTMLLinkElement] {

  def create(using HTMLDocument): F[HTMLLinkElement]

}
