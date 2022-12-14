package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.dom.*
import net.st915.immutablescalajs.typeclasses.CanCreateElement

trait CanCreateElementInstances {

  given monadCanCreateAnchor[F[_]: Monad]: CanCreateElement[F, HTMLAnchorElement] with
    override def apply(): F[HTMLAnchorElement] =
      Monad[F].pure(HTMLAnchorElement())

  given monadCanCreateBR[F[_]: Monad]: CanCreateElement[F, HTMLBRElement] with
    override def apply(): F[HTMLBRElement] =
      Monad[F].pure(HTMLBRElement())

  given monadCanCreateDiv[F[_]: Monad]: CanCreateElement[F, HTMLDivElement] with
    override def apply(): F[HTMLDivElement] =
      Monad[F].pure(HTMLDivElement())

  given monadCanCreateH1[F[_]: Monad]: CanCreateElement[F, HTMLH1Element] with
    override def apply(): F[HTMLH1Element] =
      Monad[F].pure(HTMLH1Element())

  given monadCanCreateParagraph[F[_]: Monad]: CanCreateElement[F, HTMLParagraphElement] with
    override def apply(): F[HTMLParagraphElement] =
      Monad[F].pure(HTMLParagraphElement())

  given monadCanCreateSpan[F[_]: Monad]: CanCreateElement[F, HTMLSpanElement] with
    override def apply(): F[HTMLSpanElement] =
      Monad[F].pure(HTMLSpanElement())

}
