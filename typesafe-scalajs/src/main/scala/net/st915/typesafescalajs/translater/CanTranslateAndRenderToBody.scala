package net.st915.typesafescalajs.translater

import net.st915.typesafescalajs.{Node, ScalaJSDocument}

object CanTranslateAndRenderToBody {

  def apply[F[_]](using ev: CanTranslateAndRenderToBody[F]): CanTranslateAndRenderToBody[F] = ev

}

trait CanTranslateAndRenderToBody[F[_]] {

  def apply(node: Node)(using ScalaJSDocument): F[Unit]

}
