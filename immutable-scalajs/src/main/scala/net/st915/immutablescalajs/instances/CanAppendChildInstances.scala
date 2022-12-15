package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.dom.HTMLElement
import net.st915.immutablescalajs.dom.attributes.HasChildElements
import net.st915.immutablescalajs.typeclasses.CanAppendChild

trait CanAppendChildInstances {

  given monadCanAppendChild[F[_]: Monad, A <: HTMLElement, B <: HasChildElements[B]]
    : CanAppendChild[F, A, B] with
    override def apply(child: A)(parent: B): F[B] =
      Monad[F].pure(parent.copyWithNewChildElements(parent.childElements.appended(child)))

}
