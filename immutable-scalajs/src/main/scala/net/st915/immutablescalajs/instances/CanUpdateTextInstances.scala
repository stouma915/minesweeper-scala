package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.dom.*
import net.st915.immutablescalajs.dom.properties.Text
import net.st915.immutablescalajs.typeclasses.CanUpdateText

trait CanUpdateTextInstances {

  given monadCanUpdateAnchorText[F[_]: Monad]: CanUpdateText[F, HTMLAnchorElement] with
    override def apply(newText: Text)(element: HTMLAnchorElement): F[HTMLAnchorElement] =
      Monad[F].pure(element.copy(text = Some(newText)))

  given monadCanUpdateH1Text[F[_]: Monad]: CanUpdateText[F, HTMLH1Element] with
    override def apply(newText: Text)(element: HTMLH1Element): F[HTMLH1Element] =
      Monad[F].pure(element.copy(text = Some(newText)))

  given monadCanUpdateParagraphText[F[_]: Monad]: CanUpdateText[F, HTMLParagraphElement] with
    override def apply(newText: Text)(element: HTMLParagraphElement): F[HTMLParagraphElement] =
      Monad[F].pure(element.copy(text = Some(newText)))

  given monadCanUpdateSpanText[F[_]: Monad]: CanUpdateText[F, HTMLSpanElement] with
    override def apply(newText: Text)(element: HTMLSpanElement): F[HTMLSpanElement] =
      Monad[F].pure(element.copy(text = Some(newText)))

}
