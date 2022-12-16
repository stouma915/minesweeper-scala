package net.st915.immutablescalajs.componentcreators

import net.st915.immutablescalajs.dom.properties.CSSClass

object CanSetCSSClass {

  def apply[F[_], A](using ev: CanSetCSSClass[F, A]): CanSetCSSClass[F, A] = ev

}

trait CanSetCSSClass[F[_], A] {

  def apply(newCSSClass: CSSClass)(element: A): F[A]

}
