package net.st915.minesweeper.logic.refreshui.impl

import cats.effect.Sync
import net.st915.minesweeper.logic.refreshui.application.*
import org.scalajs.dom.*

class SyncUpdateTextContent[F[_]: Sync: GetElement] extends UpdateTextContent[F] {

  import cats.syntax.flatMap.*

  override def update(id: String, text: String)(implicit document: HTMLDocument): F[Unit] =
    GetElement[F].get[HTMLElement](id) >>= { element =>
      Sync[F].blocking(element.textContent = text)
    }

}
