package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.*
import net.st915.immutablescalajs.dom.properties.Text
import net.st915.immutablescalajs.typeclasses.CanUpdateScalaJSElementText

import scala.util.chaining.*

private[immutablescalajs] trait CanUpdateScalaJSElementTextInstances {

  given monadCanUpdateScalaJSElementCSSClass[F[_]: Monad, A <: ScalaJSElement]
    : CanUpdateScalaJSElementText[F, A] with
    override def apply(newText: Text)(element: A): F[A] =
      Monad[F].pure(element.tap(_.textContent = newText.parse))

}
