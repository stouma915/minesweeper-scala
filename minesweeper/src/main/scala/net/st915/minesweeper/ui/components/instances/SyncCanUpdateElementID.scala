package net.st915.minesweeper.ui.components.instances

import cats.effect.Sync
import net.st915.minesweeper.ui.components.typeclasses.CanUpdateElementID
import net.st915.minesweeper.util.ID
import org.scalajs.dom.HTMLElement

class SyncCanUpdateElementID[F[_]: Sync] extends CanUpdateElementID[F] {

  override def perform(element: HTMLElement, newID: ID): F[Unit] =
    Sync[F].blocking {
      if (element.id != newID.asStr) {
        element.id = newID.asStr
      } else {
        // do nothing
      }
    }

}
