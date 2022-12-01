package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.{
  AppendBR,
  AppendElement,
  CreateElement
}
import org.scalajs.dom.{HTMLBRElement, HTMLDocument, HTMLElement}

class SyncAppendBR[F[_]: Sync: AppendElement: CreateElement]
    extends AppendBR[F] {

  import cats.syntax.flatMap.*

  override def append[A <: HTMLElement](
      parent: A
  )(implicit document: HTMLDocument): F[Unit] =
    CreateElement[F].create[HTMLBRElement]("br") >>= { br =>
      AppendElement[F].append(parent, br)
    }

}
