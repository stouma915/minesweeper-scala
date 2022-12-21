package net.st915.immutablescalajs.privateutil.instances

import cats.Monad
import net.st915.immutablescalajs.privateutil.AsInstanceOf

private[immutablescalajs] trait AsInstanceOfInstances {

  given monadAsInstanceOf[F[_]: Monad]: AsInstanceOf[F] with
    override def perform[B](original: AnyRef): F[B] =
      Monad[F].pure {
        original.asInstanceOf[B]
      }

}
