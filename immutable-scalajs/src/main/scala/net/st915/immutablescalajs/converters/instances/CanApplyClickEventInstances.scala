package net.st915.immutablescalajs.converters.instances

import cats.Monad
import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.immutablescalajs.ScalaJSElement
import net.st915.immutablescalajs.converters.CanApplyClickEvent
import net.st915.immutablescalajs.dom.attributes.Clickable

import scala.util.chaining.*

private[converters] trait CanApplyClickEventInstances {

  given monadCanApplyClickEvent[F[_]: Monad, A <: Clickable[A], B <: ScalaJSElement]
    : CanApplyClickEvent[F, A, B] with
    override def apply(original: A)(scalaJSElem: B)(using IORuntime): F[B] =
      Monad[F].pure {
        scalaJSElem
          .tap {
            _.onclick = event =>
              (IO(event.preventDefault()) >> original.onClick).unsafeRunAndForget()
          }
      }

}
