package net.st915.minesweeper

import cats.Monad

package object util {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def lift[M[_]: Monad, A, B](op: A => B)(ma: M[A]): M[B] =
    for {
      a <- ma
    } yield op(a)

  def liftM2[M[_]: Monad, A, B, C](op: A => B => C)(ma: M[A])(mb: M[B]): M[C] =
    for {
      a <- ma
      b <- mb
    } yield op(a)(b)

}
