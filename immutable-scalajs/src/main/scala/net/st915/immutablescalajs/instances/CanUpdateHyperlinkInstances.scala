package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.dom.attributes.HasHyperlink
import net.st915.immutablescalajs.dom.properties.Hyperlink
import net.st915.immutablescalajs.typeclasses.CanUpdateHyperlink

trait CanUpdateHyperlinkInstances {

  given monadCanUpdateHyperlink[F[_]: Monad, A <: HasHyperlink[A]]: CanUpdateHyperlink[F, A] with
    override def apply(newHyperlink: Hyperlink)(element: A): F[A] =
      Monad[F].pure(element.copyWithNewHyperlink(Some(newHyperlink)))

}
