package net.st915.immutablescalajs.typeclasses

import net.st915.immutablescalajs.dom.properties.ID

private[immutablescalajs] object CanUpdateScalaJSElementID {

  def apply[F[_], A](using ev: CanUpdateScalaJSElementID[F, A]): CanUpdateScalaJSElementID[F, A] =
    ev

}

private[immutablescalajs] trait CanUpdateScalaJSElementID[F[_], A] {

  def apply(newID: ID)(element: A): F[A]

}
