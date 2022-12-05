package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.UpdateElementVisibility
import org.scalajs.dom.*

class SyncUpdateElementVisibility[F[_]: Sync] extends UpdateElementVisibility[F] {

  override def update[A <: HTMLElement](element: A, visible: Boolean): F[Unit] =
    if (visible) {
      if (element.style.display != "block")
        Sync[F].blocking(element.style.display = "block")
      else
        Sync[F].unit
    } else {
      if (element.style.display != "none")
        Sync[F].blocking(element.style.display = "none")
      else
        Sync[F].unit
    }

}
