package net.st915.immutablescalajs.componentcreators

import net.st915.immutablescalajs.dom.properties.Hyperlink

object CanSetHyperlink {

  def apply[F[_], A](using ev: CanSetHyperlink[F, A]): CanSetHyperlink[F, A] = ev

}

trait CanSetHyperlink[F[_], A] {

  def apply(newHyperlink: Hyperlink)(element: A): F[A]

}
