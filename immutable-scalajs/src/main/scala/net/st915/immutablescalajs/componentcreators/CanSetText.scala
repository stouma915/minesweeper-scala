package net.st915.immutablescalajs.componentcreators

import net.st915.immutablescalajs.dom.properties.Text

object CanSetText {

  def apply[F[_], A](using ev: CanSetText[F, A]): CanSetText[F, A] = ev

}

trait CanSetText[F[_], A] {

  def apply(newText: Text)(element: A): F[A]

}
