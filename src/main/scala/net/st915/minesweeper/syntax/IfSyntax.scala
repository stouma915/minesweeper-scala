package net.st915.minesweeper.syntax

trait IfSyntax {

  implicit class FunctionFunctionFAOps[F[_], A](funcFuncFA: (=> F[A]) => (=> F[A]) => F[A]) {

    def ifTrue(value: => F[A]): (=> F[A]) => F[A] = funcFuncFA(value)

  }

  implicit class FunctionFAOps[F[_], A](funcFA: (=> F[A]) => F[A]) {

    def ifFalse(value: => F[A]): F[A] = funcFA(value)

  }

}
