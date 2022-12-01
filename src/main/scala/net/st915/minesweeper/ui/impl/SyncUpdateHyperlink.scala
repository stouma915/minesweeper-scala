package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.UpdateHyperlink
import org.scalajs.dom.*

class SyncUpdateHyperlink[F[_]: Sync] extends UpdateHyperlink[F] {

  override def update(element: HTMLLinkElement, hyperlink: String): F[Unit] =
    if (element.href != hyperlink)
      Sync[F].blocking(element.href = hyperlink)
    else
      Sync[F].unit

}
