package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.UpdateElementTextColor
import org.scalajs.dom.*

class SyncUpdateElementTextColor[F[_]: Sync] extends UpdateElementTextColor[F] {

  override def update[A <: HTMLElement](element: A, color: String): F[Unit] =
    if (element.style.color != color)
      Sync[F].blocking(element.style.color = color)
    else
      Sync[F].unit

}
