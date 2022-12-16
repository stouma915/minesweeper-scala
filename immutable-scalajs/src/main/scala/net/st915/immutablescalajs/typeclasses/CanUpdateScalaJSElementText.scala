package net.st915.immutablescalajs.typeclasses

import net.st915.immutablescalajs.dom.properties.Text

private[immutablescalajs] object CanUpdateScalaJSElementText {

  def apply[F[_], A](using
  ev: CanUpdateScalaJSElementText[F, A]): CanUpdateScalaJSElementText[F, A] = ev

}

private[immutablescalajs] trait CanUpdateScalaJSElementText[F[_], A] {

  def apply(newText: Text)(element: A): F[A]

}
