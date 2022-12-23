package net.st915.immutablescalajs.componentcreators.instances

import cats.Monad
import net.st915.immutablescalajs.componentcreators.CanAppendChilds
import net.st915.immutablescalajs.dom.HTMLElement
import net.st915.immutablescalajs.dom.attributes.HasChildElements

trait CanAppendChildsInstances {

  import cats.syntax.flatMap.*

  given monadCanAppendChilds[F[_]: Monad, A <: HasChildElements[A]]: CanAppendChilds[F, A] with
    override def apply[B <: HTMLElement](childs: List[B])(parent: A): F[A] =
      Monad[F].pure {
        parent.copyWithNewChildElements(parent.childElements.appendedAll(childs))
      }

    override def apply[B <: HTMLElement](fchilds: F[List[B]])(parent: A): F[A] =
      fchilds >>= { childs => apply(childs)(parent) }

}
