package net.st915.immutablescalajs.componentcreators.instances

import cats.Monad
import cats.effect.IO
import net.st915.immutablescalajs.componentcreators.CanCreateElement
import net.st915.immutablescalajs.dom.*

trait CanCreateElementInstances {

  import net.st915.immutablescalajs.dom.typealiases.*

  given monadCanCreateAnchor[F[_]: Monad]: CanCreateElement[F, Anchor] with
    override def apply(): F[Anchor] =
      Monad[F].pure {
        HTMLAnchorElement(
          cssClass = None,
          id = None,
          hyperlink = None,
          text = None,
          childElements = List()
        )
      }

  given monadCanCreateBR[F[_]: Monad]: CanCreateElement[F, BR] with
    override def apply(): F[BR] =
      Monad[F].pure {
        HTMLBRElement()
      }

  given monadCanCreateDiv[F[_]: Monad]: CanCreateElement[F, Div] with
    override def apply(): F[Div] =
      Monad[F].pure {
        HTMLDivElement(
          cssClass = None,
          id = None,
          childElements = List(),
          onClick = IO.unit,
          onRightClick = IO.unit
        )
      }

  given monadCanCreateH1[F[_]: Monad]: CanCreateElement[F, H1] with
    override def apply(): F[H1] =
      Monad[F].pure {
        HTMLH1Element(
          cssClass = None,
          id = None,
          text = None
        )
      }

  given monadCanCreateParagraph[F[_]: Monad]: CanCreateElement[F, Paragraph] with
    override def apply(): F[Paragraph] =
      Monad[F].pure {
        HTMLParagraphElement(
          cssClass = None,
          id = None,
          text = None
        )
      }

  given monadCanCreateSpan[F[_]: Monad]: CanCreateElement[F, Span] with
    override def apply(): F[Span] =
      Monad[F].pure {
        HTMLSpanElement(
          cssClass = None,
          id = None,
          text = None
        )
      }

}
