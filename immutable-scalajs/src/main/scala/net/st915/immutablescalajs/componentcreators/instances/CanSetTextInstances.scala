package net.st915.immutablescalajs.componentcreators.instances

import cats.Monad
import net.st915.immutablescalajs.componentcreators.CanSetText
import net.st915.immutablescalajs.dom.attributes.HasText
import net.st915.immutablescalajs.dom.properties.Text

trait CanSetTextInstances {

  given monadCanSetText[F[_]: Monad, A <: HasText[A]]: CanSetText[F, A] with
    override def apply(newText: Text)(element: A): F[A] =
      Monad[F].pure {
        element.copyWithNewText(Some(newText))
      }

}
