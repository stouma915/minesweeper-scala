package net.st915.immutablescalajs.converters.privateinstances

import cats.Monad
import net.st915.immutablescalajs.*
import net.st915.immutablescalajs.converters.privatetypeclasses.CanCreateScalaJSElement
import net.st915.immutablescalajs.dom.HTMLElement

private[converters] trait CanCreateScalaJSElementInstances {

  given monadCanCreateScalaJSElement[F[_]: Monad, A <: HTMLElement, B <: ScalaJSElement]
    : CanCreateScalaJSElement[F, A, B] with
    override def apply(original: A)(using ScalaJSDocument): F[B] =
      Monad[F].pure {
        summon[ScalaJSDocument]
          .createElement(original.tagName.unwrap)
          .asInstanceOf[B]
      }

}
