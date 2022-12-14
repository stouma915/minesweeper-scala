package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.dom.*
import net.st915.immutablescalajs.properties.CSSClass
import net.st915.immutablescalajs.typeclasses.CanUpdateCSSClass

trait CanUpdateCSSClassInstances {

  given monadCanUpdateAnchorClass[F[_]: Monad]: CanUpdateCSSClass[F, HTMLAnchorElement] with
    override def apply(newCSSClass: CSSClass)(element: HTMLAnchorElement): F[HTMLAnchorElement] =
      Monad[F].pure(element.copy(cssClass = Some(newCSSClass)))

  given monadCanUpdateDivClass[F[_]: Monad]: CanUpdateCSSClass[F, HTMLDivElement] with
    override def apply(newCSSClass: CSSClass)(element: HTMLDivElement): F[HTMLDivElement] =
      Monad[F].pure(element.copy(cssClass = Some(newCSSClass)))

  given monadCanUpdateH1Class[F[_]: Monad]: CanUpdateCSSClass[F, HTMLH1Element] with
    override def apply(newCSSClass: CSSClass)(element: HTMLH1Element): F[HTMLH1Element] =
      Monad[F].pure(element.copy(cssClass = Some(newCSSClass)))

  given monadCanUpdateParagraphClass[F[_]: Monad]: CanUpdateCSSClass[F, HTMLParagraphElement] with
    override def apply(newCSSClass: CSSClass)(element: HTMLParagraphElement)
      : F[HTMLParagraphElement] =
      Monad[F].pure(element.copy(cssClass = Some(newCSSClass)))

  given monadCanUpdateSpanClass[F[_]: Monad]: CanUpdateCSSClass[F, HTMLSpanElement] with
    override def apply(newCSSClass: CSSClass)(element: HTMLSpanElement): F[HTMLSpanElement] =
      Monad[F].pure(element.copy(cssClass = Some(newCSSClass)))

}
