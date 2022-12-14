package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.dom.*
import net.st915.immutablescalajs.properties.ID
import net.st915.immutablescalajs.typeclasses.CanUpdateID

trait CanUpdateIDInstances {

  given monadCanUpdateAnchorID[F[_]: Monad]: CanUpdateID[F, HTMLAnchorElement] with
    override def apply(newID: ID)(element: HTMLAnchorElement): F[HTMLAnchorElement] =
      Monad[F].pure(element.copy(id = Some(newID)))

  given monadCanUpdateDivID[F[_]: Monad]: CanUpdateID[F, HTMLDivElement] with
    override def apply(newID: ID)(element: HTMLDivElement): F[HTMLDivElement] =
      Monad[F].pure(element.copy(id = Some(newID)))

  given monadCanUpdateH1ID[F[_]: Monad]: CanUpdateID[F, HTMLH1Element] with
    override def apply(newID: ID)(element: HTMLH1Element): F[HTMLH1Element] =
      Monad[F].pure(element.copy(id = Some(newID)))

  given monadCanUpdateParagraphID[F[_]: Monad]: CanUpdateID[F, HTMLParagraphElement] with
    override def apply(newID: ID)(element: HTMLParagraphElement): F[HTMLParagraphElement] =
      Monad[F].pure(element.copy(id = Some(newID)))

  given monadCanUpdateSpanID[F[_]: Monad]: CanUpdateID[F, HTMLSpanElement] with
    override def apply(newID: ID)(element: HTMLSpanElement): F[HTMLSpanElement] =
      Monad[F].pure(element.copy(id = Some(newID)))

}
