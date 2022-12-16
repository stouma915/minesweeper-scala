package net.st915.immutablescalajs.componentcreators.instances

import cats.Monad
import net.st915.immutablescalajs.componentcreators.CanSetHyperlink
import net.st915.immutablescalajs.dom.attributes.HasHyperlink
import net.st915.immutablescalajs.dom.properties.Hyperlink

trait CanSetHyperlinkInstances {

  given monadCanSetHyperlink[F[_]: Monad, A <: HasHyperlink[A]]: CanSetHyperlink[F, A] with
    override def apply(newHyperlink: Hyperlink)(element: A): F[A] =
      Monad[F].pure {
        element.copyWithNewHyperlink(Some(newHyperlink))
      }

}
