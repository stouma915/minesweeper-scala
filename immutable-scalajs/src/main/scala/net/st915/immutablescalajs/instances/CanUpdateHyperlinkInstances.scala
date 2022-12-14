package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.dom.*
import net.st915.immutablescalajs.properties.Hyperlink
import net.st915.immutablescalajs.typeclasses.CanUpdateHyperlink

trait CanUpdateHyperlinkInstances {

  given monadCanUpdateAnchorHyperlink[F[_]: Monad]: CanUpdateHyperlink[F, HTMLAnchorElement] with
    override def apply(newHyperlink: Hyperlink)(element: HTMLAnchorElement): F[HTMLAnchorElement] =
      Monad[F].pure(element.copy(hyperlink = Some(newHyperlink)))

}
