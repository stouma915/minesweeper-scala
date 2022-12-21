package net.st915.immutablescalajs.converters.privatetypeclasses

private[converters] object CanApplyID {

  def apply[F[_], A, B](using ev: CanApplyID[F, A, B]): CanApplyID[F, A, B] = ev

}

private[converters] trait CanApplyID[F[_], A, B] {

  def apply(original: A)(scalaJSElem: B): F[B]

}
