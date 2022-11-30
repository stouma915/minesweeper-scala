package net.st915.minesweeper.renderer

import cats.effect.Sync
import net.st915.minesweeper.renderer.impl.SyncWriteDocument
import net.st915.minesweeper.renderer.repo.WriteDocument
import org.scalajs.dom.HTMLDocument

object Service {

  def wired[F[_]: Sync](implicit document: HTMLDocument): F[Unit] = {
    implicit val _writeDocument: WriteDocument[F] = SyncWriteDocument[F]

    Renderer()
  }

}
