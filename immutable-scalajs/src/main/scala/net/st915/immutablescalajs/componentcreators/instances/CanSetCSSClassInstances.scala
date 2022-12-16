package net.st915.immutablescalajs.componentcreators.instances

import cats.Monad
import net.st915.immutablescalajs.componentcreators.CanSetCSSClass
import net.st915.immutablescalajs.dom.attributes.HasCSSClass
import net.st915.immutablescalajs.dom.properties.CSSClass

trait CanSetCSSClassInstances {

  given monadCanSetCSSClass[F[_]: Monad, A <: HasCSSClass[A]]: CanSetCSSClass[F, A] with
    override def apply(newCSSClass: CSSClass)(element: A): F[A] =
      Monad[F].pure {
        element.copyWithNewCSSClass(Some(newCSSClass))
      }

}
