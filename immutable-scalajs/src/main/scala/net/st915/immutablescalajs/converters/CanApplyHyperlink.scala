package net.st915.immutablescalajs.converters

private[converters] object CanApplyHyperlink {

  def apply[F[_], A, B](using ev: CanApplyHyperlink[F, A, B]): CanApplyHyperlink[F, A, B] = ev

}

private[converters] trait CanApplyHyperlink[F[_], A, B] {

  def apply(original: A)(scalaJSElem: B): F[B]

}
