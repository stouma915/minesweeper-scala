package net.st915.immutablescalajs.util

import cats.effect.unsafe.IORuntime
import net.st915.immutablescalajs.ScalaJSDocument

object CanAppendToDocument {

  def apply[F[_], A](using ev: CanAppendToDocument[F, A]): CanAppendToDocument[F, A] = ev

}

trait CanAppendToDocument[F[_], A] {

  def apply(element: A)(using ScalaJSDocument, IORuntime): F[Unit]

}
