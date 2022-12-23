package net.st915.immutablescalajs.converters.instances

import cats.Monad
import cats.effect.unsafe.IORuntime
import net.st915.immutablescalajs.*
import net.st915.immutablescalajs.converters.CanConvertElement
import net.st915.immutablescalajs.converters.privatetypeclasses.*
import net.st915.immutablescalajs.dom.*

trait CanConvertElementInstances {

  import cats.syntax.flatMap.*

  import net.st915.immutablescalajs.converters.privateinstances.all.given

  import net.st915.immutablescalajs.typealiases.*
  import net.st915.immutablescalajs.dom.typealiases.*

  given monadCanConvertAnchor[F[_]: Monad]: CanConvertElement[F, Anchor, SJSAnchor] with
    override def apply(original: Anchor)(using ScalaJSDocument, IORuntime): F[SJSAnchor] =
      CanCreateScalaJSElement[F, Anchor, SJSAnchor](original) >>=
        CanApplyCSSClass[F, Anchor, SJSAnchor](original) >>=
        CanApplyID[F, Anchor, SJSAnchor](original) >>=
        CanApplyHyperlink[F, Anchor, SJSAnchor](original) >>=
        CanApplyText[F, Anchor, SJSAnchor](original) >>=
        CanApplyChildElements[F, Anchor, SJSAnchor](original)

  given monadCanConvertBR[F[_]: Monad]: CanConvertElement[F, BR, SJSBR] with
    override def apply(original: BR)(using ScalaJSDocument, IORuntime): F[SJSBR] =
      CanCreateScalaJSElement[F, BR, SJSBR](original)

  given monadCanConvertDiv[F[_]: Monad]: CanConvertElement[F, Div, SJSDiv] with
    override def apply(original: Div)(using ScalaJSDocument, IORuntime): F[SJSDiv] =
      CanCreateScalaJSElement[F, Div, SJSDiv](original) >>=
        CanApplyCSSClass[F, Div, SJSDiv](original) >>=
        CanApplyID[F, Div, SJSDiv](original) >>=
        CanApplyClickEvent[F, Div, SJSDiv](original) >>=
        CanApplyRightClickEvent[F, Div, SJSDiv](original) >>=
        CanApplyChildElements[F, Div, SJSDiv](original)

  given monadCanConvertH1[F[_]: Monad]: CanConvertElement[F, H1, SJSH1] with
    override def apply(original: H1)(using ScalaJSDocument, IORuntime): F[SJSH1] =
      CanCreateScalaJSElement[F, H1, SJSH1](original) >>=
        CanApplyCSSClass[F, H1, SJSH1](original) >>=
        CanApplyID[F, H1, SJSH1](original) >>=
        CanApplyText[F, H1, SJSH1](original)

  given monadCanConvertParagraph[F[_]: Monad]: CanConvertElement[F, Paragraph, SJSParagraph] with
    override def apply(original: Paragraph)(using ScalaJSDocument, IORuntime): F[SJSParagraph] =
      CanCreateScalaJSElement[F, Paragraph, SJSParagraph](original) >>=
        CanApplyCSSClass[F, Paragraph, SJSParagraph](original) >>=
        CanApplyID[F, Paragraph, SJSParagraph](original) >>=
        CanApplyText[F, Paragraph, SJSParagraph](original)

  given monadCanConvertSpan[F[_]: Monad]: CanConvertElement[F, Span, SJSSpan] with
    override def apply(original: Span)(using ScalaJSDocument, IORuntime): F[SJSSpan] =
      CanCreateScalaJSElement[F, Span, SJSSpan](original) >>=
        CanApplyCSSClass[F, Span, SJSSpan](original) >>=
        CanApplyID[F, Span, SJSSpan](original) >>=
        CanApplyText[F, Span, SJSSpan](original)

}
