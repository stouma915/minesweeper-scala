package net.st915.immutablescalajs.typeclasses

import net.st915.immutablescalajs.dom.properties.Hyperlink

object CanUpdateHyperlink {

  def apply[F[_], A](using ev: CanUpdateHyperlink[F, A]): CanUpdateHyperlink[F, A] = ev

}

trait CanUpdateHyperlink[F[_], A] {

  def apply(newHyperlink: Hyperlink)(element: A): F[A]

}
