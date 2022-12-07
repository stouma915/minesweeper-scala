package net.st915.minesweeper.ui.components.instances

import cats.effect.Sync
import net.st915.minesweeper.ui.components.typeclasses.CanUpdateTextContent
import net.st915.minesweeper.ui.consts.UIText
import org.scalajs.dom.*

class SyncCanUpdateTextContent[F[_]: Sync] extends CanUpdateTextContent[F] {

  override def perform(element: HTMLElement, newContent: UIText): F[Unit] =
    Sync[F].blocking {
      if (element.textContent != newContent.asStr) {
        element.textContent = newContent.asStr
      } else {
        // do nothing
      }
    }

}
