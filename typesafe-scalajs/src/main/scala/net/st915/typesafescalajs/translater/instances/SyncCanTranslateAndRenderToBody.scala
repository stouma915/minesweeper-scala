package net.st915.typesafescalajs.translater.instances

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.typesafescalajs.*
import net.st915.typesafescalajs.domain.typealiases.ScalaJSNode
import net.st915.typesafescalajs.translater.*

import scala.util.chaining.*

class SyncCanTranslateAndRenderToBody[F[_]: Sync: CanTranslate]
    extends CanTranslateAndRenderToBody[F] {

  import cats.syntax.flatMap.*

  private def appendChild(sjsNode: ScalaJSNode)(using ScalaJSDocument): F[Unit] =
    Sync[F].blocking(summon[ScalaJSDocument].body.appendChild(sjsNode))

  override def apply(node: Node)(using ScalaJSDocument, IORuntime): F[Unit] =
    CanTranslate[F](node) >>= appendChild

}
