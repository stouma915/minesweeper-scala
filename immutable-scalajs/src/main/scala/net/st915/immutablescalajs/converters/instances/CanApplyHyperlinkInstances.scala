package net.st915.immutablescalajs.converters.instances

import cats.Monad
import net.st915.immutablescalajs.ScalaJSAnchorElement
import net.st915.immutablescalajs.converters.CanApplyHyperlink
import net.st915.immutablescalajs.dom.attributes.HasHyperlink

import scala.util.chaining.*

private[converters] trait CanApplyHyperlinkInstances {

  given monadCanApplyHyperlink[F[_]: Monad, A <: HasHyperlink[A], B <: ScalaJSAnchorElement]
    : CanApplyHyperlink[F, A, B] with
    override def apply(original: A)(scalaJSElem: B): F[B] =
      Monad[F].pure {
        scalaJSElem
          .tap { elem =>
            original
              .hyperlink
              .map { newHyperlink => elem.href = newHyperlink.unwrap }
          }
      }

}
