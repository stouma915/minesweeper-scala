package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.*
import net.st915.immutablescalajs.dom.properties.Hyperlink
import net.st915.immutablescalajs.typeclasses.CanUpdateScalaJSElementHyperlink

import scala.util.chaining.*

private[immutablescalajs] trait CanUpdateScalaJSElementHyperlinkInstances {

  given monadCanUpdateScalaJSAnchorElementHyperlink[F[_]: Monad]
    : CanUpdateScalaJSElementHyperlink[F, ScalaJSAnchorElement] with
    override def apply(newHyperlink: Hyperlink)(element: ScalaJSAnchorElement)
      : F[ScalaJSAnchorElement] =
      Monad[F].pure(element.tap(_.href = newHyperlink.parse))

}
