package net.st915.immutablescalajs.util.instances

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.immutablescalajs.*
import net.st915.immutablescalajs.converters.CanConvertElement
import net.st915.immutablescalajs.dom.*
import net.st915.immutablescalajs.util.CanAppendToDocument

trait CanAppendToDocumentInstances {

  import cats.syntax.flatMap.*

  import net.st915.immutablescalajs.converters.instances.all.given

  import net.st915.immutablescalajs.typealiases.*
  import net.st915.immutablescalajs.dom.typealiases.*

  given syncCanAppendScalaJSElement[F[_]: Sync]: CanAppendToDocument[F, ScalaJSElement] with
    override def apply(element: ScalaJSElement)(using ScalaJSDocument, IORuntime): F[Unit] =
      Sync[F].blocking {
        summon[ScalaJSDocument].body.appendChild(element)
      }

  given syncCanAppendAnchor[F[_]: Sync]: CanAppendToDocument[F, Anchor] with
    override def apply(element: Anchor)(using ScalaJSDocument, IORuntime): F[Unit] =
      CanConvertElement[F, Anchor, SJSAnchor](element)
        >>= CanAppendToDocument[F, ScalaJSElement].apply

  given syncCanAppendBR[F[_]: Sync]: CanAppendToDocument[F, BR] with
    override def apply(element: BR)(using ScalaJSDocument, IORuntime): F[Unit] =
      CanConvertElement[F, BR, SJSBR](element)
        >>= CanAppendToDocument[F, ScalaJSElement].apply

  given syncCanAppendDiv[F[_]: Sync]: CanAppendToDocument[F, Div] with
    override def apply(element: Div)(using ScalaJSDocument, IORuntime): F[Unit] =
      CanConvertElement[F, Div, SJSDiv](element)
        >>= CanAppendToDocument[F, ScalaJSElement].apply

  given syncCanAppendH1[F[_]: Sync]: CanAppendToDocument[F, H1] with
    override def apply(element: H1)(using ScalaJSDocument, IORuntime): F[Unit] =
      CanConvertElement[F, H1, SJSH1](element)
        >>= CanAppendToDocument[F, ScalaJSElement].apply

  given syncCanAppendParagraph[F[_]: Sync]: CanAppendToDocument[F, Paragraph] with
    override def apply(element: Paragraph)(using ScalaJSDocument, IORuntime): F[Unit] =
      CanConvertElement[F, Paragraph, SJSParagraph](element)
        >>= CanAppendToDocument[F, ScalaJSElement].apply

  given syncCanAppendSpan[F[_]: Sync]: CanAppendToDocument[F, Span] with
    override def apply(element: Span)(using ScalaJSDocument, IORuntime): F[Unit] =
      CanConvertElement[F, Span, SJSSpan](element)
        >>= CanAppendToDocument[F, ScalaJSElement].apply

}
