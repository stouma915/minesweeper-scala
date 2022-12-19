package net.st915.immutablescalajs.converters

import cats.effect.unsafe.IORuntime

private[converters] object CanApplyRightClickEvent {

  def apply[F[_], A, B](using
  ev: CanApplyRightClickEvent[F, A, B]): CanApplyRightClickEvent[F, A, B] = ev

}

private[converters] trait CanApplyRightClickEvent[F[_], A, B] {

  def apply(original: A)(scalaJSElem: B)(using IORuntime): F[B]

}
