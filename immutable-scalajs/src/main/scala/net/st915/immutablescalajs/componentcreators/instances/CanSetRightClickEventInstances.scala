package net.st915.immutablescalajs.componentcreators.instances

import cats.Monad
import cats.effect.IO
import net.st915.immutablescalajs.componentcreators.CanSetRightClickEvent
import net.st915.immutablescalajs.dom.attributes.RightClickable

trait CanSetRightClickEventInstances {

  given monadCanSetRightClickEvent[F[_]: Monad, A <: RightClickable[A]]: CanSetRightClickEvent[F, A]
    with
    override def apply(newRightClickEvent: IO[Unit])(element: A): F[A] =
      Monad[F].pure {
        element.copyWithNewRightClickEvent(newRightClickEvent)
      }

}
