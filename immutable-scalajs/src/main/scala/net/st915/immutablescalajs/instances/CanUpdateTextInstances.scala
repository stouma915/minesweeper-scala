package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.dom.attributes.HasText
import net.st915.immutablescalajs.dom.properties.Text
import net.st915.immutablescalajs.typeclasses.CanUpdateText

trait CanUpdateTextInstances {

  given monadCanUpdateAnchorText[F[_]: Monad, A <: HasText[A]]: CanUpdateText[F, A] with
    override def apply(newText: Text)(element: A): F[A] =
      Monad[F].pure(element.copyWithNewText(Some(newText)))

}
