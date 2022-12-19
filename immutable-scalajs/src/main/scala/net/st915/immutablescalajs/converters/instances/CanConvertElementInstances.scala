package net.st915.immutablescalajs.converters.instances

import cats.Monad
import cats.effect.unsafe.IORuntime
import net.st915.immutablescalajs.*
import net.st915.immutablescalajs.converters.*
import net.st915.immutablescalajs.dom.*

trait CanConvertElementInstances {

  import cats.syntax.flatMap.*

  import instances.packagePrivates.given

  given monadCanConvertAnchor[F[_]: Monad]
    : CanConvertElement[F, HTMLAnchorElement, ScalaJSAnchorElement] with
    override def apply(original: HTMLAnchorElement)(
      using ScalaJSDocument,
      IORuntime
    ): F[ScalaJSAnchorElement] =
      CanCreateScalaJSElement[F, HTMLAnchorElement, ScalaJSAnchorElement](original) >>=
        CanApplyCSSClass[F, HTMLAnchorElement, ScalaJSAnchorElement](original) >>=
        CanApplyID[F, HTMLAnchorElement, ScalaJSAnchorElement](original) >>=
        CanApplyHyperlink[F, HTMLAnchorElement, ScalaJSAnchorElement](original) >>=
        CanApplyText[F, HTMLAnchorElement, ScalaJSAnchorElement](original)

  given monadCanConvertBR[F[_]: Monad]: CanConvertElement[F, HTMLBRElement, ScalaJSBRElement] with
    override def apply(original: HTMLBRElement)(
      using ScalaJSDocument,
      IORuntime
    ): F[ScalaJSBRElement] =
      CanCreateScalaJSElement[F, HTMLBRElement, ScalaJSBRElement](original)

  given monadCanConvertDiv[F[_]: Monad]: CanConvertElement[F, HTMLDivElement, ScalaJSDivElement]
    with
    override def apply(original: HTMLDivElement)(
      using ScalaJSDocument,
      IORuntime
    ): F[ScalaJSDivElement] =
      CanCreateScalaJSElement[F, HTMLDivElement, ScalaJSDivElement](original) >>=
        CanApplyCSSClass[F, HTMLDivElement, ScalaJSDivElement](original) >>=
        CanApplyID[F, HTMLDivElement, ScalaJSDivElement](original) >>=
        CanApplyClickEvent[F, HTMLDivElement, ScalaJSDivElement](original) >>=
        CanApplyRightClickEvent[F, HTMLDivElement, ScalaJSDivElement](original)

  given monadCanConvertH1[F[_]: Monad]: CanConvertElement[F, HTMLH1Element, ScalaJSH1Element] with
    override def apply(original: HTMLH1Element)(
      using ScalaJSDocument,
      IORuntime
    ): F[ScalaJSH1Element] =
      CanCreateScalaJSElement[F, HTMLH1Element, ScalaJSH1Element](original) >>=
        CanApplyCSSClass[F, HTMLH1Element, ScalaJSH1Element](original) >>=
        CanApplyID[F, HTMLH1Element, ScalaJSH1Element](original) >>=
        CanApplyText[F, HTMLH1Element, ScalaJSH1Element](original)

  given monadCanConvertParagraph[F[_]: Monad]
    : CanConvertElement[F, HTMLParagraphElement, ScalaJSParagraphElement] with
    override def apply(original: HTMLParagraphElement)(
      using ScalaJSDocument,
      IORuntime
    ): F[ScalaJSParagraphElement] =
      CanCreateScalaJSElement[F, HTMLParagraphElement, ScalaJSParagraphElement](original) >>=
        CanApplyCSSClass[F, HTMLParagraphElement, ScalaJSParagraphElement](original) >>=
        CanApplyID[F, HTMLParagraphElement, ScalaJSParagraphElement](original) >>=
        CanApplyText[F, HTMLParagraphElement, ScalaJSParagraphElement](original)

  given monadCanConvertSpan[F[_]: Monad]: CanConvertElement[F, HTMLSpanElement, ScalaJSSpanElement]
    with
    override def apply(original: HTMLSpanElement)(
      using ScalaJSDocument,
      IORuntime
    ): F[ScalaJSSpanElement] =
      CanCreateScalaJSElement[F, HTMLSpanElement, ScalaJSSpanElement](original) >>=
        CanApplyCSSClass[F, HTMLSpanElement, ScalaJSSpanElement](original) >>=
        CanApplyID[F, HTMLSpanElement, ScalaJSSpanElement](original) >>=
        CanApplyText[F, HTMLSpanElement, ScalaJSSpanElement](original)

}
