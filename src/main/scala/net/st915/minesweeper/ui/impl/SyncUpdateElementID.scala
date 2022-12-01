package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.UpdateElementID
import org.scalajs.dom.*

class SyncUpdateElementID[F[_]: Sync] extends UpdateElementID[F] {

  override def update[A <: HTMLElement](element: A, elementID: String): F[Unit] =
    if (element.id != elementID)
      Sync[F].blocking(element.id = elementID)
    else
      Sync[F].unit

}
