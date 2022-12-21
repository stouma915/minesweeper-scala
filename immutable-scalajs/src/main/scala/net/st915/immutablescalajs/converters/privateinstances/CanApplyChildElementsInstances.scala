package net.st915.immutablescalajs.converters.privateinstances

import cats.Monad
import cats.effect.unsafe.IORuntime
import net.st915.immutablescalajs.*
import net.st915.immutablescalajs.converters.CanConvertElement
import net.st915.immutablescalajs.converters.privatetypeclasses.CanApplyChildElements
import net.st915.immutablescalajs.dom.*
import net.st915.immutablescalajs.dom.attributes.HasChildElements
import net.st915.immutablescalajs.privateutil.AsInstanceOf

private[converters] trait CanApplyChildElementsInstances {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  import net.st915.immutablescalajs.converters.instances.all.given
  import net.st915.immutablescalajs.privateutil.instances.all.given

  import net.st915.immutablescalajs.typealiases.*
  import net.st915.immutablescalajs.dom.typealiases.*

  given monadCanApplyChildElements[F[_]: Monad, A <: HasChildElements[A], B <: ScalaJSElement]
    : CanApplyChildElements[F, A, B] with
    override def apply(original: A)(scalaJSElem: B)(using ScalaJSDocument, IORuntime): F[B] =
      for {
        elem <- Monad[F].pure(scalaJSElem)
        childs <- original.childElements.map { e =>
          e match
            case a: Anchor =>
              CanConvertElement[F, Anchor, SJSAnchor](a) >>=
                AsInstanceOf[F].perform[ScalaJSElement]
            case a: BR =>
              CanConvertElement[F, BR, SJSBR](a) >>=
                AsInstanceOf[F].perform[ScalaJSElement]
            case a: Div =>
              CanConvertElement[F, Div, SJSDiv](a) >>=
                AsInstanceOf[F].perform[ScalaJSElement]
            case a: H1 =>
              CanConvertElement[F, H1, SJSH1](a) >>=
                AsInstanceOf[F].perform[ScalaJSElement]
            case a: Paragraph =>
              CanConvertElement[F, Paragraph, SJSParagraph](a) >>=
                AsInstanceOf[F].perform[ScalaJSElement]
            case a: Span =>
              CanConvertElement[F, Span, SJSSpan](a) >>=
                AsInstanceOf[F].perform[ScalaJSElement]
        }.sequence
        _ <- childs.map { c => Monad[F].pure(elem.appendChild(c)) }.sequence
      } yield elem

}
