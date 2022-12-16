package net.st915.immutablescalajs.componentcreators

object CanCreateElement {

  def apply[F[_], A](using ev: CanCreateElement[F, A]): CanCreateElement[F, A] = ev

}

trait CanCreateElement[F[_], A] {

  def apply(): F[A]

}
