package net.st915.minesweeper.renderer.impl

import cats.effect.Sync
import net.st915.minesweeper.renderer.repo.WriteDocument
import org.scalajs.dom.{HTMLDocument, Element}

class SyncWriteDocument[F[_]: Sync] extends WriteDocument[F] {

  override def write(element: Element)(implicit
      document: HTMLDocument
  ): F[Unit] =
    Sync[F].delay(document.body.appendChild(element))

}
