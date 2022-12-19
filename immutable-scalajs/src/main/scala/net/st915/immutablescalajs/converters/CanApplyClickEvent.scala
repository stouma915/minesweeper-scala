package net.st915.immutablescalajs.converters

import cats.effect.unsafe.IORuntime

private[converters] object CanApplyClickEvent {

  def apply[F[_], A, B](using ev: CanApplyClickEvent[F, A, B]): CanApplyClickEvent[F, A, B] = ev

}

private[converters] trait CanApplyClickEvent[F[_], A, B] {

  def apply(original: A)(scalaJSElem: B)(using IORuntime): F[B]

}
