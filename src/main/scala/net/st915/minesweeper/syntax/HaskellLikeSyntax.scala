package net.st915.minesweeper.syntax

import cats.Monad

trait HaskellLikeSyntax {

  implicit class FunctionFAFBOps[F[_]: Monad, A, B](func: F[A] => F[B]) {
    
    def $(value: F[A]): F[B] = func(value)
    
  }
  
}
