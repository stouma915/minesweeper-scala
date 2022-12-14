package net.st915.util

import cats.Monad

object HigherKindIf {

  def begin[F[_]: Monad, A](cond: => F[Boolean]): HigherKindIf[F, A] =
    HigherKindIf()(cond)

}

case class HigherKindIf[F[_]: Monad, A]()(cond: => F[Boolean]) {

  case class HigherKindIfApplied[F[_]: Monad, A]()(cond: => F[Boolean], ifTrue: => F[A]) {

    import cats.syntax.flatMap.*

    def else_(ifFalse: => F[A]): F[A] =
      cond >>= (if (_) ifTrue else ifFalse)

  }

  def then_(ifTrue: => F[A]): HigherKindIfApplied[F, A] =
    HigherKindIfApplied()(cond, ifTrue)

}
