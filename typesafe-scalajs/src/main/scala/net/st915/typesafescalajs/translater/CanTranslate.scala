package net.st915.typesafescalajs.translater

import cats.effect.unsafe.IORuntime
import net.st915.typesafescalajs.*

object CanTranslate {

  def apply[F[_]](using ev: CanTranslate[F]): CanTranslate[F] = ev

}

trait CanTranslate[F[_]] {

  def apply(node: Node)(using ScalaJSDocument, IORuntime): F[ScalaJSNode]

}
