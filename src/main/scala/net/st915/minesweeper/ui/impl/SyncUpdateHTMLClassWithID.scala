package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.*
import org.scalajs.dom.*

class SyncUpdateHTMLClassWithID[F[_]: Sync: GetElement: UpdateHTMLClass]
    extends UpdateHTMLClassWithID[F] {

  import cats.syntax.flatMap.*

  override def update(id: String, htmlClass: String)(implicit document: HTMLDocument): F[Unit] =
    GetElement[F].get(id) >>= { element => UpdateHTMLClass[F].update(element, htmlClass) }

}
