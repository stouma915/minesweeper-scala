package net.st915.minesweeper.ui.components.instances

import cats.effect.Sync
import net.st915.minesweeper.ui.components.typeclasses.CanUpdateHyperlink
import net.st915.minesweeper.util.Link
import org.scalajs.dom.HTMLLinkElement

class SyncCanUpdateHyperlink[F[_]: Sync] extends CanUpdateHyperlink[F] {

  override def perform(element: HTMLLinkElement, link: Link): F[Unit] =
    Sync[F].blocking {
      if (element.href != link.asStr) {
        element.href = link.asStr
      } else {
        // do nothing
      }
    }

}
