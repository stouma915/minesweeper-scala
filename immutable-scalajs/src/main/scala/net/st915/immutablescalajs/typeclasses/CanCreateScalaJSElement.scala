package net.st915.immutablescalajs.typeclasses

import net.st915.immutablescalajs.ScalaJSDocument

object CanCreateScalaJSElement {

  def apply[F[_], A, B](using
  ev: CanCreateScalaJSElement[F, A, B]): CanCreateScalaJSElement[F, A, B] = ev

}

trait CanCreateScalaJSElement[F[_], A, B] {

  def apply(element: A)(using ScalaJSDocument): F[B]

}
