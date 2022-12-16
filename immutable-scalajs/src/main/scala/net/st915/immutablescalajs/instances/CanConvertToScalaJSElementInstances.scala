package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.*
import net.st915.immutablescalajs.dom.*
import net.st915.immutablescalajs.dom.properties.*
import net.st915.immutablescalajs.typeclasses.*

trait CanConvertToScalaJSElementInstances {

  import cats.syntax.flatMap.*

  import instances.all.given
  import instances.canUpdateScalaJSElementCSSClassInstances.given
  import instances.canUpdateScalaJSElementIDInstances.given
  import instances.canUpdateScalaJSElementHyperlinkInstances.given
  import instances.canUpdateScalaJSElementTextInstances.given

  given monadCanConvertAnchor[F[_]: Monad]
    : CanConvertToScalaJSElement[F, HTMLAnchorElement, ScalaJSAnchorElement] with
    override def apply(element: HTMLAnchorElement)(using ScalaJSDocument): F[ScalaJSAnchorElement] =
      CanCreateScalaJSElement[F, HTMLAnchorElement, ScalaJSAnchorElement](element) >>=
        CanUpdateScalaJSElementCSSClass[F, ScalaJSAnchorElement](
          element.cssClass.getOrElse(CSSClass(""))
        ) >>=
        CanUpdateScalaJSElementID[F, ScalaJSAnchorElement](
          element.id.getOrElse(ID(""))
        ) >>=
        CanUpdateScalaJSElementHyperlink[F, ScalaJSAnchorElement](
          element.hyperlink.getOrElse(Hyperlink(""))
        ) >>=
        CanUpdateScalaJSElementText[F, ScalaJSAnchorElement](
          element.text.getOrElse(Text(""))
        )

  given monadCanConvertBR[F[_]: Monad]
    : CanConvertToScalaJSElement[F, HTMLBRElement, ScalaJSBRElement] with
    override def apply(element: HTMLBRElement)(using ScalaJSDocument): F[ScalaJSBRElement] =
      CanCreateScalaJSElement[F, HTMLBRElement, ScalaJSBRElement](element)

  given monadCanConvertDiv[F[_]: Monad]
    : CanConvertToScalaJSElement[F, HTMLDivElement, ScalaJSDivElement] with
    override def apply(element: HTMLDivElement)(using ScalaJSDocument): F[ScalaJSDivElement] =
      CanCreateScalaJSElement[F, HTMLDivElement, ScalaJSDivElement](element) >>=
        CanUpdateScalaJSElementCSSClass[F, ScalaJSDivElement](
          element.cssClass.getOrElse(CSSClass(""))
        ) >>=
        CanUpdateScalaJSElementID[F, ScalaJSDivElement](
          element.id.getOrElse(ID(""))
        )

  given monadCanConvertH1[F[_]: Monad]
    : CanConvertToScalaJSElement[F, HTMLH1Element, ScalaJSH1Element] with
    override def apply(element: HTMLH1Element)(using ScalaJSDocument): F[ScalaJSH1Element] =
      CanCreateScalaJSElement[F, HTMLH1Element, ScalaJSH1Element](element) >>=
        CanUpdateScalaJSElementCSSClass[F, ScalaJSH1Element](
          element.cssClass.getOrElse(CSSClass(""))
        ) >>=
        CanUpdateScalaJSElementID[F, ScalaJSH1Element](
          element.id.getOrElse(ID(""))
        ) >>=
        CanUpdateScalaJSElementText[F, ScalaJSH1Element](
          element.text.getOrElse(Text(""))
        )

  given monadCanConvertParagraph[F[_]: Monad]
    : CanConvertToScalaJSElement[F, HTMLParagraphElement, ScalaJSParagraphElement] with
    override def apply(element: HTMLParagraphElement)(using
    ScalaJSDocument): F[ScalaJSParagraphElement] =
      CanCreateScalaJSElement[F, HTMLParagraphElement, ScalaJSParagraphElement](element) >>=
        CanUpdateScalaJSElementCSSClass[F, ScalaJSParagraphElement](
          element.cssClass.getOrElse(CSSClass(""))
        ) >>=
        CanUpdateScalaJSElementID[F, ScalaJSParagraphElement](
          element.id.getOrElse(ID(""))
        ) >>=
        CanUpdateScalaJSElementText[F, ScalaJSParagraphElement](
          element.text.getOrElse(Text(""))
        )

  given monadCanConvertSpan[F[_]: Monad]
    : CanConvertToScalaJSElement[F, HTMLSpanElement, ScalaJSSpanElement] with
    override def apply(element: HTMLSpanElement)(using ScalaJSDocument): F[ScalaJSSpanElement] =
      CanCreateScalaJSElement[F, HTMLSpanElement, ScalaJSSpanElement](element) >>=
        CanUpdateScalaJSElementCSSClass[F, ScalaJSSpanElement](
          element.cssClass.getOrElse(CSSClass(""))
        ) >>=
        CanUpdateScalaJSElementID[F, ScalaJSSpanElement](
          element.id.getOrElse(ID(""))
        ) >>=
        CanUpdateScalaJSElementText[F, ScalaJSSpanElement](
          element.text.getOrElse(Text(""))
        )

}
