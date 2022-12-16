package net.st915.immutablescalajs.typeclasses

import net.st915.immutablescalajs.dom.properties.CSSClass

private[immutablescalajs] object CanUpdateScalaJSElementCSSClass {

  def apply[F[_], A](using
  ev: CanUpdateScalaJSElementCSSClass[F, A]): CanUpdateScalaJSElementCSSClass[F, A] = ev

}

private[immutablescalajs] trait CanUpdateScalaJSElementCSSClass[F[_], A] {

  def apply(newCSSClass: CSSClass)(element: A): F[A]

}
