package net.st915.immutablescalajs.converters

private[converters] object CanApplyCSSClass {

  def apply[F[_], A, B](using ev: CanApplyCSSClass[F, A, B]): CanApplyCSSClass[F, A, B] = ev

}

private[converters] trait CanApplyCSSClass[F[_], A, B] {

  def apply(original: A)(scalaJSElem: B): F[B]

}
