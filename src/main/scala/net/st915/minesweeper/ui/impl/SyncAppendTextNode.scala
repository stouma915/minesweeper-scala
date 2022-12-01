package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.{AppendTextNode, CreateTextNode}
import org.scalajs.dom.*

class SyncAppendTextNode[F[_]: Sync: CreateTextNode] extends AppendTextNode[F] {

  import cats.syntax.flatMap.*

  override def append[A <: HTMLElement](parent: A, child: String)(implicit
  document: HTMLDocument): F[Unit] =
    CreateTextNode[F].create(child) >>= { textNode =>
      Sync[F].blocking(parent.appendChild(textNode))
    }
}
