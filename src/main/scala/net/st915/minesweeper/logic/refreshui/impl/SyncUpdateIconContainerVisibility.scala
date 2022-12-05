package net.st915.minesweeper.logic.refreshui.impl

import cats.effect.Sync
import net.st915.minesweeper.Coordinate
import net.st915.minesweeper.logic.refreshui.application.UpdateIconContainerVisibility
import net.st915.minesweeper.ui.application.UpdateElementVisibilityWithID
import net.st915.minesweeper.util.application.IDFactory
import org.scalajs.dom.*

class SyncUpdateIconContainerVisibility[F[_]: Sync: UpdateElementVisibilityWithID: IDFactory]
    extends UpdateIconContainerVisibility[F] {

  import cats.syntax.flatMap.*

  override def update(iconClass: String, coord: Coordinate, visible: Boolean)(implicit
  document: HTMLDocument): F[Unit] =
    IDFactory[F].iconContainer(iconClass, coord) >>= { id =>
      UpdateElementVisibilityWithID[F].update(id, visible)
    }

}
