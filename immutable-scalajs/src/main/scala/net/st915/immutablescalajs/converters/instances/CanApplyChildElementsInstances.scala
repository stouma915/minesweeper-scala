package net.st915.immutablescalajs.converters.instances

import cats.Monad
import cats.effect.unsafe.IORuntime
import net.st915.immutablescalajs.*
import net.st915.immutablescalajs.converters.*
import net.st915.immutablescalajs.dom.*
import net.st915.immutablescalajs.dom.attributes.HasChildElements
import net.st915.immutablescalajs.util.AsInstanceOf

private[converters] trait CanApplyChildElementsInstances {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  import all.given
  import packagePrivates.given
  import net.st915.immutablescalajs.util.instances.all.given

  given monadCanApplyChildElements[F[_]: Monad, A <: HasChildElements[A], B <: ScalaJSElement]
    : CanApplyChildElements[F, A, B] with
    override def apply(original: A)(scalaJSElem: B)(using ScalaJSDocument, IORuntime): F[B] =
      for {
        elem <- Monad[F].pure(scalaJSElem)
        childs <- original.childElements.map { e =>
          e match
            case a: HTMLAnchorElement =>
              CanConvertElement[F, HTMLAnchorElement, ScalaJSAnchorElement](a) >>=
                AsInstanceOf[F].perform[ScalaJSElement]
            case a: HTMLBRElement =>
              CanConvertElement[F, HTMLBRElement, ScalaJSBRElement](a) >>=
                AsInstanceOf[F].perform[ScalaJSElement]
            case a: HTMLDivElement =>
              CanConvertElement[F, HTMLDivElement, ScalaJSDivElement](a) >>=
                AsInstanceOf[F].perform[ScalaJSElement]
            case a: HTMLH1Element =>
              CanConvertElement[F, HTMLH1Element, ScalaJSH1Element](a) >>=
                AsInstanceOf[F].perform[ScalaJSElement]
            case a: HTMLParagraphElement =>
              CanConvertElement[F, HTMLParagraphElement, ScalaJSParagraphElement](a) >>=
                AsInstanceOf[F].perform[ScalaJSElement]
            case a: HTMLSpanElement =>
              CanConvertElement[F, HTMLSpanElement, ScalaJSSpanElement](a) >>=
                AsInstanceOf[F].perform[ScalaJSElement]
        }.sequence
        _ <- childs.map { c => Monad[F].pure(elem.appendChild(c)) }.sequence
      } yield elem

}
