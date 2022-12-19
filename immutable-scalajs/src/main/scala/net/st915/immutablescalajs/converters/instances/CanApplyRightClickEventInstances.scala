package net.st915.immutablescalajs.converters.instances

import cats.Monad
import cats.effect.IO
import cats.effect.unsafe.IORuntime
import net.st915.immutablescalajs.ScalaJSElement
import net.st915.immutablescalajs.converters.CanApplyRightClickEvent
import net.st915.immutablescalajs.dom.attributes.RightClickable

import scala.util.chaining.*

private[converters] trait CanApplyRightClickEventInstances {

  given monadCanApplyRightClickEvent[F[_]: Monad, A <: RightClickable[A], B <: ScalaJSElement]
    : CanApplyRightClickEvent[F, A, B] with
    override def apply(original: A)(scalaJSElem: B)(using IORuntime): F[B] =
      Monad[F].pure {
        scalaJSElem.tap {
          _.oncontextmenu = event =>
            (IO(event.preventDefault()) >> original.onRightClick).unsafeRunAndForget()
        }
      }

}
