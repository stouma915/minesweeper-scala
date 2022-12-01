package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.UpdateHTMLClass
import org.scalajs.dom.*

class SyncUpdateHTMLClass[F[_]: Sync] extends UpdateHTMLClass[F] {

  override def update[A <: HTMLElement](
      element: A,
      htmlClass: String
  ): F[Unit] =
    if (element.className != htmlClass)
      Sync[F].blocking(element.className = htmlClass)
    else
      Sync[F].unit

}
