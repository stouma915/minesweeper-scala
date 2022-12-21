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

  given syncCanAppendScalaJSElement[F[_]: Sync]: CanAppendToDocument[F, ScalaJSElement] with
    override def apply(element: ScalaJSElement)(using ScalaJSDocument, IORuntime): F[Unit] =
      Sync[F].blocking {
        summon[ScalaJSDocument].body.appendChild(element)
      }

  given syncCanAppendAnchor[F[_]: Sync]: CanAppendToDocument[F, HTMLAnchorElement] with
    override def apply(element: HTMLAnchorElement)(using ScalaJSDocument, IORuntime): F[Unit] =
      CanConvertElement[F, HTMLAnchorElement, ScalaJSAnchorElement](element)
        >>= CanAppendToDocument[F, ScalaJSElement].apply

  given syncCanAppendBR[F[_]: Sync]: CanAppendToDocument[F, HTMLBRElement] with
    override def apply(element: HTMLBRElement)(using ScalaJSDocument, IORuntime): F[Unit] =
      CanConvertElement[F, HTMLBRElement, ScalaJSBRElement](element)
        >>= CanAppendToDocument[F, ScalaJSElement].apply

  given syncCanAppendDiv[F[_]: Sync]: CanAppendToDocument[F, HTMLDivElement] with
    override def apply(element: HTMLDivElement)(using ScalaJSDocument, IORuntime): F[Unit] =
      CanConvertElement[F, HTMLDivElement, ScalaJSDivElement](element)
        >>= CanAppendToDocument[F, ScalaJSElement].apply

  given syncCanAppendH1[F[_]: Sync]: CanAppendToDocument[F, HTMLH1Element] with
    override def apply(element: HTMLH1Element)(using ScalaJSDocument, IORuntime): F[Unit] =
      CanConvertElement[F, HTMLH1Element, ScalaJSH1Element](element)
        >>= CanAppendToDocument[F, ScalaJSElement].apply

  given syncCanAppendParagraph[F[_]: Sync]: CanAppendToDocument[F, HTMLParagraphElement] with
    override def apply(element: HTMLParagraphElement)(using ScalaJSDocument, IORuntime): F[Unit] =
      CanConvertElement[F, HTMLParagraphElement, ScalaJSParagraphElement](element)
        >>= CanAppendToDocument[F, ScalaJSElement].apply

  given syncCanAppendSpan[F[_]: Sync]: CanAppendToDocument[F, HTMLSpanElement] with
    override def apply(element: HTMLSpanElement)(using ScalaJSDocument, IORuntime): F[Unit] =
      CanConvertElement[F, HTMLSpanElement, ScalaJSSpanElement](element)
        >>= CanAppendToDocument[F, ScalaJSElement].apply

}
