package net.st915.immutablescalajs.converters

import net.st915.immutablescalajs.ScalaJSDocument

private[converters] object CanCreateScalaJSElement {

  def apply[F[_], A, B](using
  ev: CanCreateScalaJSElement[F, A, B]): CanCreateScalaJSElement[F, A, B] = ev

}

private[converters] trait CanCreateScalaJSElement[F[_], A, B] {

  def apply(original: A)(using ScalaJSDocument): F[B]

}
