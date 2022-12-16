package net.st915.immutablescalajs.componentcreators.instances

import cats.Monad
import net.st915.immutablescalajs.componentcreators.CanSetID
import net.st915.immutablescalajs.dom.attributes.HasID
import net.st915.immutablescalajs.dom.properties.ID

trait CanSetIDInstances {

  given monadCanSetID[F[_]: Monad, A <: HasID[A]]: CanSetID[F, A] with
    override def apply(newID: ID)(element: A): F[A] =
      Monad[F].pure {
        element.copyWithNewID(Some(newID))
      }

}
