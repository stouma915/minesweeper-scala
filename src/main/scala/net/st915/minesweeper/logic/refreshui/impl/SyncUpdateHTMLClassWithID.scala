package net.st915.minesweeper.logic.refreshui.impl

import cats.effect.Sync
import net.st915.minesweeper.logic.refreshui.application.*
import org.scalajs.dom.*

class SyncUpdateHTMLClassWithID[F[_]: Sync: GetElement] extends UpdateHTMLClassWithID[F] {

  import cats.syntax.flatMap.*

  override def update(id: String, htmlClass: String)(implicit document: HTMLDocument): F[Unit] =
    GetElement[F].get(id) >>= { element =>
      if (element.className != htmlClass)
        Sync[F].blocking(element.className = htmlClass)
      else
        Sync[F].unit
    }

}
