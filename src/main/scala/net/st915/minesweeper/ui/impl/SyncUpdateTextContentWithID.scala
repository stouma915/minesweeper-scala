package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.*
import org.scalajs.dom.*

class SyncUpdateTextContentWithID[F[_]: Sync: GetElement: UpdateTextContent]
    extends UpdateTextContentWithID[F] {

  import cats.syntax.flatMap.*

  override def update(id: String, text: String)(implicit document: HTMLDocument): F[Unit] =
    GetElement[F].get(id) >>= { element => UpdateTextContent[F].update(element, text) }

}
