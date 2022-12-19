package net.st915.immutablescalajs.converters.instances

import cats.Monad
import net.st915.immutablescalajs.ScalaJSElement
import net.st915.immutablescalajs.converters.CanApplyID
import net.st915.immutablescalajs.dom.attributes.HasID

import scala.util.chaining.*

private[converters] trait CanApplyIDInstances {

  given monadCanApplyID[F[_]: Monad, A <: HasID[A], B <: ScalaJSElement]: CanApplyID[F, A, B] with
    override def apply(original: A)(scalaJSElem: B): F[B] =
      Monad[F].pure {
        scalaJSElem
          .tap { elem =>
            original
              .id
              .map { newID => elem.id = newID.unwrap }
          }
      }

}
