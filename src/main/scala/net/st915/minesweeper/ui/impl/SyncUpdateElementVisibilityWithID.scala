package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.*
import org.scalajs.dom.*

class SyncUpdateElementVisibilityWithID[F[_]: Sync: GetElement: UpdateElementVisibility]
    extends UpdateElementVisibilityWithID[F] {

  import cats.syntax.flatMap.*

  override def update(id: String, visible: Boolean)(implicit document: HTMLDocument): F[Unit] =
    GetElement[F].get(id) >>= { element => UpdateElementVisibility[F].update(element, visible) }

}
