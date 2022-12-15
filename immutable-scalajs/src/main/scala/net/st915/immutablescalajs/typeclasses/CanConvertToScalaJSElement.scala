package net.st915.immutablescalajs.typeclasses

import net.st915.immutablescalajs.ScalaJSDocument

object CanConvertToScalaJSElement {

  def apply[F[_], A, B](using
  ev: CanConvertToScalaJSElement[F, A, B]): CanConvertToScalaJSElement[F, A, B] = ev

}

trait CanConvertToScalaJSElement[F[_], A, B] {

  def apply(element: A)(using ScalaJSDocument): F[B]

}
