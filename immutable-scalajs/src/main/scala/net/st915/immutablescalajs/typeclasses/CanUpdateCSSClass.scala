package net.st915.immutablescalajs.typeclasses

import net.st915.immutablescalajs.dom.properties.CSSClass

object CanUpdateCSSClass {

  def apply[F[_], A](using ev: CanUpdateCSSClass[F, A]): CanUpdateCSSClass[F, A] = ev

}

trait CanUpdateCSSClass[F[_], A] {

  def apply(newCSSClass: CSSClass)(element: A): F[A]

}
