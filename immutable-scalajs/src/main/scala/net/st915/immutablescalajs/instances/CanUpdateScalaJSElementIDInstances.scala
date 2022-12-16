package net.st915.immutablescalajs.instances

import cats.Monad
import net.st915.immutablescalajs.*
import net.st915.immutablescalajs.dom.properties.ID
import net.st915.immutablescalajs.typeclasses.CanUpdateScalaJSElementID

import scala.util.chaining.*

private[immutablescalajs] trait CanUpdateScalaJSElementIDInstances {

  given monadCanUpdateScalaJSElementID[F[_]: Monad, A <: ScalaJSElement]
    : CanUpdateScalaJSElementID[F, A] with
    override def apply(newID: ID)(element: A): F[A] =
      Monad[F].pure(element.tap(_.id = newID.parse))

}
