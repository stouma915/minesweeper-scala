package net.st915.immutablescalajs.componentcreators.instances

import cats.Monad
import net.st915.immutablescalajs.componentcreators.CanAppendChild
import net.st915.immutablescalajs.dom.HTMLElement
import net.st915.immutablescalajs.dom.attributes.HasChildElements

trait CanAppendChildInstances {

  given monadCanAppendChild[F[_]: Monad, A <: HasChildElements[A]]: CanAppendChild[F, A] with
    override def apply(child: HTMLElement)(parent: A): F[A] =
      apply(List(child))(parent)

    override def apply(childs: List[HTMLElement])(parent: A): F[A] =
      Monad[F].pure {
        parent.copyWithNewChildElements(parent.childElements.appendedAll(childs))
      }

    override def apply(childs: HTMLElement*)(parent: A): F[A] =
      apply(childs.toList)(parent)

}
