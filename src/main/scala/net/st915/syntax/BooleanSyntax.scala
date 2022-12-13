package net.st915.syntax

import cats.Monad

trait BooleanSyntax {

  def not[F[_]: Monad](fBool: F[Boolean]): F[Boolean] = fBool.not

  implicit class FBooleanOps[F[_]: Monad](fBool: F[Boolean]) {

    import cats.syntax.flatMap.*
    import cats.syntax.functor.*

    def not: F[Boolean] =
      for {
        a <- fBool
      } yield !a

    def and(another: F[Boolean]): F[Boolean] =
      for {
        a <- fBool
        b <- another
      } yield a && b

    def or(another: F[Boolean]): F[Boolean] =
      for {
        a <- fBool
        b <- another
      } yield a || b

  }

}
