package net.st915.immutablescalajs.componentcreators.instances

import cats.Monad
import cats.effect.IO
import net.st915.immutablescalajs.componentcreators.CanSetClickEvent
import net.st915.immutablescalajs.dom.attributes.Clickable

trait CanSetClickEventInstances {

  given monadCanSetClickEvent[F[_]: Monad, A <: Clickable[A]]: CanSetClickEvent[F, A] with
    override def apply(newClickEvent: IO[Unit])(element: A): F[A] =
      Monad[F].pure {
        element.copyWithNewClickEvent(newClickEvent)
      }

}
