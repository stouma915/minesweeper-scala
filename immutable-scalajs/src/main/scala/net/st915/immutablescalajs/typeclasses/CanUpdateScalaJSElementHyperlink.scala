package net.st915.immutablescalajs.typeclasses

import net.st915.immutablescalajs.dom.properties.Hyperlink

private[immutablescalajs] object CanUpdateScalaJSElementHyperlink {

  def apply[F[_], A](using
  ev: CanUpdateScalaJSElementHyperlink[F, A]): CanUpdateScalaJSElementHyperlink[F, A] = ev

}

private[immutablescalajs] trait CanUpdateScalaJSElementHyperlink[F[_], A] {

  def apply(newHyperlink: Hyperlink)(element: A): F[A]

}
