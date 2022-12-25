package net.st915.minesweeper.util.instances

import cats.Monad
import net.st915.minesweeper.util.DoNothing

trait DoNothingInstances {

  given monadDoNothing[F[_]: Monad]: DoNothing[F] with
    override def apply[A]()(using A): F[A] =
      Monad[F].pure(summon[A])

}
