package net.st915.immutablescalajs.converters.instances

import cats.Monad
import net.st915.immutablescalajs.ScalaJSElement
import net.st915.immutablescalajs.converters.CanApplyCSSClass
import net.st915.immutablescalajs.dom.attributes.HasCSSClass

import scala.util.chaining.*

private[converters] trait CanApplyCSSClassInstances {

  given monadCanApplyCSSClass[F[_]: Monad, A <: HasCSSClass[A], B <: ScalaJSElement]
    : CanApplyCSSClass[F, A, B] with
    override def apply(original: A)(scalaJSElem: B): F[B] =
      Monad[F].pure {
        scalaJSElem
          .tap { elem =>
            original
              .cssClass
              .map { newClass => elem.className = newClass.parse }
          }
      }

}
