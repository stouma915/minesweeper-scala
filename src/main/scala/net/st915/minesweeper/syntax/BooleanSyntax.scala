package net.st915.minesweeper.syntax

import cats.Monad

trait BooleanSyntax {

  implicit class FBooleanOps[F[_]: Monad](fBool: F[Boolean]) {

    import cats.syntax.flatMap.*

    def not: F[Boolean] = fBool >>= { a =>
      Monad[F].pure(!a)
    }

    def and(another: F[Boolean]): F[Boolean] = fBool >>= { a =>
      another >>= { b =>
        Monad[F].pure(a && b)
      }
    }

    def or(another: F[Boolean]): F[Boolean] = fBool >>= { a =>
      another >>= { b =>
        Monad[F].pure(a || b)
      }
    }

  }

}
