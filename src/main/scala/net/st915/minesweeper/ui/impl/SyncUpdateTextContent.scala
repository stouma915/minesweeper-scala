package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.UpdateTextContent
import org.scalajs.dom.*

class SyncUpdateTextContent[F[_]: Sync] extends UpdateTextContent[F] {

  import cats.syntax.flatMap.*

  override def update[A <: HTMLElement](element: A, text: String)(implicit
  document: HTMLDocument): F[Unit] =
    if (element.textContent != text)
      Sync[F].blocking(element.textContent = text)
    else
      Sync[F].unit

}
