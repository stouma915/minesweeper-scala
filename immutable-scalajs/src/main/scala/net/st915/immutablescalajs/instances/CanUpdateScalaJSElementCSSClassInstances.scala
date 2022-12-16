package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.*
import net.st915.immutablescalajs.dom.properties.CSSClass
import net.st915.immutablescalajs.typeclasses.CanUpdateScalaJSElementCSSClass

import scala.util.chaining.*

private[immutablescalajs] trait CanUpdateScalaJSElementCSSClassInstances {

  given monadCanUpdateScalaJSElementCSSClass[F[_]: Monad, A <: ScalaJSElement]
    : CanUpdateScalaJSElementCSSClass[F, A] with
    override def apply(newCSSClass: CSSClass)(element: A): F[A] =
      Monad[F].pure(element.tap(_.className = newCSSClass.parse))

}
