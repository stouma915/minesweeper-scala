package net.st915.immutablescalajs.converters

import cats.effect.unsafe.IORuntime
import net.st915.immutablescalajs.ScalaJSDocument

private[converters] object CanApplyChildElements {

  def apply[F[_], A, B](using ev: CanApplyChildElements[F, A, B]): CanApplyChildElements[F, A, B] =
    ev

}

private[converters] trait CanApplyChildElements[F[_], A, B] {

  def apply(original: A)(scalaJSElem: B)(using ScalaJSDocument, IORuntime): F[B]

}
