package net.st915.immutablescalajs.componentcreators

import cats.effect.IO

object CanSetRightClickEvent {

  def apply[F[_], A](using ev: CanSetRightClickEvent[F, A]): CanSetRightClickEvent[F, A] = ev

}

trait CanSetRightClickEvent[F[_], A] {

  def apply(newRightClickEvent: IO[Unit])(element: A): F[A]

}
