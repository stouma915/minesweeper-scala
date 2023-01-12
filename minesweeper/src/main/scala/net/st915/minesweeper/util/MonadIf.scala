package net.st915.minesweeper.util

import cats.Monad

object MonadIf {

  def begin[F[_]: Monad, A](cond: => F[Boolean]): MonadIf[F, A] =
    MonadIf()(cond)

}

case class MonadIf[F[_]: Monad, A]()(cond: => F[Boolean]) {

  case class PartlyApplied[F[_]: Monad, A]()(cond: => F[Boolean], ifTrue: => F[A]) {

    import cats.syntax.flatMap.*

    def else_(ifFalse: => F[A]): F[A] =
      cond >>= (if (_) ifTrue else ifFalse)

  }

  def then_(ifTrue: => F[A]): PartlyApplied[F, A] =
    PartlyApplied()(cond, ifTrue)

}
