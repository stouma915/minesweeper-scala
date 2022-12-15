package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.*
import net.st915.immutablescalajs.dom.HTMLElement
import net.st915.immutablescalajs.typeclasses.CanCreateScalaJSElement

trait CanCreateScalaJSElementInstances {

  given monadCanCreateScalaJSElement[F[_]: Monad, A <: HTMLElement, B <: ScalaJSElement]: CanCreateScalaJSElement[F, A, B] with
    override def apply(element: A)(using ScalaJSDocument): F[B] =
      Monad[F].pure {
        summon[ScalaJSDocument]
          .createElement(element.tagName.parse)
          .asInstanceOf[B]
      }

}
