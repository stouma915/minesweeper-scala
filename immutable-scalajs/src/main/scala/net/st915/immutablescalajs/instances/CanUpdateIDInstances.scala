package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.dom.attributes.HasID
import net.st915.immutablescalajs.dom.properties.ID
import net.st915.immutablescalajs.typeclasses.CanUpdateID

trait CanUpdateIDInstances {

  given monadCanUpdateAnchorID[F[_]: Monad, A <: HasID[A]]: CanUpdateID[F, A] with
    override def apply(newID: ID)(element: A): F[A] =
      Monad[F].pure(element.copyWithNewID(Some(newID)))

}
