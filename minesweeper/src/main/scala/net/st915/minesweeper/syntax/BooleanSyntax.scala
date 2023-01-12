package net.st915.minesweeper.syntax

import cats.Monad
import net.st915.minesweeper.util.{lift, liftM2}

trait BooleanSyntax {

  def not[F[_]: Monad](fBool: F[Boolean]): F[Boolean] = fBool.not

  implicit class FBooleanOps[F[_]: Monad](fBool: F[Boolean]) {

    import cats.syntax.flatMap.*
    import cats.syntax.functor.*

    def not: F[Boolean] =
      lift(NOT)(fBool)

    def and(another: F[Boolean]): F[Boolean] =
      liftM2(AND)(fBool)(another)

    def or(another: F[Boolean]): F[Boolean] =
      liftM2(OR)(fBool)(another)

    private def NOT(a: Boolean): Boolean = !a
    private def AND(a: Boolean)(b: Boolean): Boolean = a && b
    private def OR(a: Boolean)(b: Boolean): Boolean = a || b

  }

}
