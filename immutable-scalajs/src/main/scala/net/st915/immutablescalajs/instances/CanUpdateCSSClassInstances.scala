package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.dom.attributes.HasCSSClass
import net.st915.immutablescalajs.dom.properties.CSSClass
import net.st915.immutablescalajs.typeclasses.CanUpdateCSSClass

trait CanUpdateCSSClassInstances {

  given monadCanUpdateCSSClass[F[_]: Monad, A <: HasCSSClass[A]]: CanUpdateCSSClass[F, A] with
    override def apply(newCSSClass: CSSClass)(element: A): F[A] =
      Monad[F].pure(element.copyWithNewCSSClass(Some(newCSSClass)))

}
