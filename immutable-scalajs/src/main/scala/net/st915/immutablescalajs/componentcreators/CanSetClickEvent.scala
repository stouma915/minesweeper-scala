package net.st915.immutablescalajs.componentcreators

import cats.effect.IO

object CanSetClickEvent {

  def apply[F[_], A](using ev: CanSetClickEvent[F, A]): CanSetClickEvent[F, A] = ev

}

trait CanSetClickEvent[F[_], A] {

  def apply(newClickEvent: IO[Unit])(element: A): F[A]

}
