package net.st915.immutablescalajs.util

object AsInstanceOf {

  def apply[F[_]](using ev: AsInstanceOf[F]): AsInstanceOf[F] = ev

}

trait AsInstanceOf[F[_]] {

  def perform[B](original: AnyRef): F[B]

}
