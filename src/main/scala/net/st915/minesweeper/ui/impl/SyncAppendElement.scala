package net.st915.minesweeper.ui.impl

import cats.effect.Sync
import net.st915.minesweeper.ui.application.AppendElement
import org.scalajs.dom.*

class SyncAppendElement[F[_]: Sync] extends AppendElement[F] {

  override def append[A <: HTMLElement](parent: A, child: A): F[Unit] =
    Sync[F].blocking(parent.appendChild(child))

}
