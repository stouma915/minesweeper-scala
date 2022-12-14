package net.st915.minesweeper.ui.components.instances

import cats.effect.Sync
import net.st915.minesweeper.ui.components.typeclasses.CanAppendElement
import org.scalajs.dom.*

class SyncCanAppendElement[F[_]: Sync] extends CanAppendElement[F] {

  override def perform(parent: HTMLElement, child: HTMLElement): F[Unit] =
    Sync[F].blocking {
      parent.appendChild(child)
    }

}
