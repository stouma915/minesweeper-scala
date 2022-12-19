package net.st915.immutablescalajs.converters.instances

import cats.Monad
import net.st915.immutablescalajs.ScalaJSElement
import net.st915.immutablescalajs.converters.CanApplyText
import net.st915.immutablescalajs.dom.attributes.HasText

import scala.util.chaining.*

private[converters] trait CanApplyTextInstances {

  given monadCanApplyText[F[_]: Monad, A <: HasText[A], B <: ScalaJSElement]: CanApplyText[F, A, B]
    with
    override def apply(original: A)(scalaJSElem: B): F[B] =
      Monad[F].pure {
        scalaJSElem
          .tap { elem =>
            original
              .text
              .map { newText => elem.textContent = newText.parse }
          }
      }

}
