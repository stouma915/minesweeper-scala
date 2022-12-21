package net.st915.immutablescalajs.privateutil

private[immutablescalajs] object AsInstanceOf {

  def apply[F[_]](using ev: AsInstanceOf[F]): AsInstanceOf[F] = ev

}

private[immutablescalajs] trait AsInstanceOf[F[_]] {

  def perform[B](original: AnyRef): F[B]

}
