package net.st915.minesweeper.util.typeclasses

object CanCheckSubset {

  def apply[F[_]](using ev: CanCheckSubset[F]): CanCheckSubset[F] = ev

}

trait CanCheckSubset[F[_]] {

  def isSubset[A](a: F[A], b: F[A]): Boolean

  def notSubset[A](a: F[A], b: F[A]): Boolean = !isSubset(a, b)

}
