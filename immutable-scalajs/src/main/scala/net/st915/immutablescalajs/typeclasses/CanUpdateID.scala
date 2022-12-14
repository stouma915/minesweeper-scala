package net.st915.immutablescalajs.typeclasses

import net.st915.immutablescalajs.dom.properties.ID

object CanUpdateID {

  def apply[F[_], A](using ev: CanUpdateID[F, A]): CanUpdateID[F, A] = ev

}

trait CanUpdateID[F[_], A] {

  def apply(newID: ID)(element: A): F[A]

}
