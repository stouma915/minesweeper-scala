package net.st915.immutablescalajs.typeclasses

object CanAppendChild {

  def apply[F[_], A, B](using ev: CanAppendChild[F, A, B]): CanAppendChild[F, A, B] = ev

}

trait CanAppendChild[F[_], A, B] {

  def apply(child: A)(parent: B): F[B]

}
