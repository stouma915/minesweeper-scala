package net.st915.minesweeper.util.instances

import cats.Monad
import net.st915.minesweeper.util.typeclasses.DoNothing

class MonadDoNothing[F[_]: Monad] extends DoNothing[F] {

  override def perform[A](using A): F[A] =
    Monad[F].pure(summon[A])

}
