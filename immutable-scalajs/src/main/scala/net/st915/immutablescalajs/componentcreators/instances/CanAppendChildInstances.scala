package net.st915.immutablescalajs.componentcreators.instances

import cats.Monad
import net.st915.immutablescalajs.componentcreators.CanAppendChild
import net.st915.immutablescalajs.dom.HTMLElement
import net.st915.immutablescalajs.dom.attributes.HasChildElements

trait CanAppendChildInstances {

  import cats.syntax.flatMap.*

  given monadCanAppendChild[F[_]: Monad, A <: HasChildElements[A]]: CanAppendChild[F, A] with
    override def apply(child: HTMLElement)(parent: A): F[A] =
      Monad[F].pure {
        parent.copyWithNewChildElements(parent.childElements.appended(child))
      }

    override def apply[B <: HTMLElement](fchild: F[B])(parent: A): F[A] =
      fchild >>= { child => apply(child)(parent) }

}
