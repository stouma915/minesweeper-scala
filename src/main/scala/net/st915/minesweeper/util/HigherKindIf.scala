package net.st915.minesweeper.util

import cats.Monad

case class HigherKindIf[F[_]: Monad, A]()(cond: => F[Boolean]) {

  case class HigherKindIfApplied[F[_]: Monad, A]()(cond: => F[Boolean], ifTrue: => F[A]) {

    import cats.syntax.flatMap.*

    def else_(ifFalse: => F[A]): F[A] =
      cond >>= (if (_) ifTrue else ifFalse)

  }

  def then_(ifTrue: => F[A]): HigherKindIfApplied[F, A] =
    HigherKindIfApplied()(cond, ifTrue)

}
