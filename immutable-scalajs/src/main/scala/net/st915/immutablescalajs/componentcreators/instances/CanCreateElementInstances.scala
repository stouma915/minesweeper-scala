package net.st915.immutablescalajs.componentcreators.instances

import cats.Monad
import cats.effect.IO
import net.st915.immutablescalajs.componentcreators.CanCreateElement
import net.st915.immutablescalajs.dom.*

trait CanCreateElementInstances {

  given monadCanCreateAnchor[F[_]: Monad]: CanCreateElement[F, HTMLAnchorElement] with
    override def apply(): F[HTMLAnchorElement] =
      Monad[F].pure {
        HTMLAnchorElement(
          cssClass = None,
          id = None,
          hyperlink = None,
          text = None
        )
      }

  given monadCanCreateBR[F[_]: Monad]: CanCreateElement[F, HTMLBRElement] with
    override def apply(): F[HTMLBRElement] =
      Monad[F].pure {
        HTMLBRElement()
      }

  given monadCanCreateDiv[F[_]: Monad]: CanCreateElement[F, HTMLDivElement] with
    override def apply(): F[HTMLDivElement] =
      Monad[F].pure {
        HTMLDivElement(
          cssClass = None,
          id = None,
          childElements = List(),
          onClick = IO.unit,
          onRightClick = IO.unit
        )
      }

  given monadCanCreateH1[F[_]: Monad]: CanCreateElement[F, HTMLH1Element] with
    override def apply(): F[HTMLH1Element] =
      Monad[F].pure {
        HTMLH1Element(
          cssClass = None,
          id = None,
          text = None
        )
      }

  given monadCanCreateParagraph[F[_]: Monad]: CanCreateElement[F, HTMLParagraphElement] with
    override def apply(): F[HTMLParagraphElement] =
      Monad[F].pure {
        HTMLParagraphElement(
          cssClass = None,
          id = None,
          text = None
        )
      }

  given monadCanCreateSpan[F[_]: Monad]: CanCreateElement[F, HTMLSpanElement] with
    override def apply(): F[HTMLSpanElement] =
      Monad[F].pure {
        HTMLSpanElement(
          cssClass = None,
          id = None,
          text = None
        )
      }

}
