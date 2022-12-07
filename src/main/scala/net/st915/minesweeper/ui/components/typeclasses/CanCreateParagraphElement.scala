package net.st915.minesweeper.ui.components.typeclasses

import org.scalajs.dom.*

object CanCreateParagraphElement {

  def apply[F[_]](using ev: CanCreateParagraphElement[F]): CanCreateParagraphElement[F] = ev

}

trait CanCreateParagraphElement[F[_]] extends CanCreateElement[F, HTMLParagraphElement] {

  def create(using HTMLDocument): F[HTMLParagraphElement]

}
