package net.st915.immutablescalajs.converters.privatetypeclasses

private[converters] object CanApplyText {

  def apply[F[_], A, B](using ev: CanApplyText[F, A, B]): CanApplyText[F, A, B] = ev

}

private[converters] trait CanApplyText[F[_], A, B] {

  def apply(original: A)(scalaJSElem: B): F[B]

}
