package net.st915.immutablescalajs

import cats.Monad
import net.st915.immutablescalajs.componentcreators.*
import net.st915.immutablescalajs.dom.*

object Components {

  import net.st915.immutablescalajs.componentcreators.instances.all.given

  def BR[F[_]: Monad]: F[HTMLBRElement] =
    CanCreateElement[F, HTMLBRElement]()

}
