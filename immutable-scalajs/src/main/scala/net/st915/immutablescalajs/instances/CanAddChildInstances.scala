package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.dom.*
import net.st915.immutablescalajs.typeclasses.CanAddChild

trait CanAddChildInstances {

  given monadCanAddDivChild[F[_]: Monad]: CanAddChild[F, HTMLDivElement] with
    override def apply(child: HTMLElement)(parent: HTMLDivElement): F[HTMLDivElement] =
      Monad[F].pure(parent.copy(childs = parent.childs.appended(child)))

}
