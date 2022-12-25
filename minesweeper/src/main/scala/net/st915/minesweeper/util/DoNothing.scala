package net.st915.minesweeper.util

object DoNothing {

  def apply[F[_]](using ev: DoNothing[F]): DoNothing[F] = ev

}

trait DoNothing[F[_]] {

  def apply[A]()(using A): F[A]

}
