package net.st915.immutablescalajs.converters

import cats.effect.unsafe.IORuntime
import net.st915.immutablescalajs.ScalaJSDocument

object CanConvertElement {

  def apply[F[_], A, B](using ev: CanConvertElement[F, A, B]): CanConvertElement[F, A, B] = ev

}

trait CanConvertElement[F[_], A, B] {

  def apply(original: A)(using ScalaJSDocument, IORuntime): F[B]

}
