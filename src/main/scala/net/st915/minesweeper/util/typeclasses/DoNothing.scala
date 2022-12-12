package net.st915.minesweeper.util.typeclasses

object DoNothing {
  
  def apply[F[_]](using ev: DoNothing[F]): DoNothing[F] = ev
  
}

trait DoNothing[F[_]] {

  def perform[A](using A): F[A]

}
