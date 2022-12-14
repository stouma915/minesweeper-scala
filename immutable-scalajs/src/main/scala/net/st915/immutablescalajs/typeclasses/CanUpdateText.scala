package net.st915.immutablescalajs.typeclasses

import net.st915.immutablescalajs.properties.Text

object CanUpdateText {

  def apply[F[_], A](using ev: CanUpdateText[F, A]): CanUpdateText[F, A] = ev

}

trait CanUpdateText[F[_], A] {

  def apply(newText: Text)(element: A): F[A]

}
