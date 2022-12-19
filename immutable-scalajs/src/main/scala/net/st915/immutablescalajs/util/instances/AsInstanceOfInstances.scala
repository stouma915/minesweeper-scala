package net.st915.immutablescalajs.util.instances

import cats.Monad
import net.st915.immutablescalajs.util.AsInstanceOf

trait AsInstanceOfInstances {

  given monadAsInstanceOf[F[_]: Monad]: AsInstanceOf[F] with
    override def perform[B](original: AnyRef): F[B] =
      Monad[F].pure {
        original.asInstanceOf[B]
      }

}
