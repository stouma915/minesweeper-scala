package net.st915.immutablescalajs.componentcreators

import net.st915.immutablescalajs.dom.properties.ID

object CanSetID {

  def apply[F[_], A](using ev: CanSetID[F, A]): CanSetID[F, A] = ev

}

trait CanSetID[F[_], A] {

  def apply(newID: ID)(element: A): F[A]

}
