package net.st915.minesweeper.ui.components.instances

import cats.effect.Sync
import net.st915.minesweeper.ui.components.typeclasses.CanUpdateElementClass
import net.st915.minesweeper.ui.consts.CSSClass
import org.scalajs.dom.HTMLElement

class SyncCanUpdateElementClass[F[_]: Sync] extends CanUpdateElementClass[F] {

  override def perform(element: HTMLElement, newClass: CSSClass): F[Unit] =
    Sync[F].blocking {
      if (element.className != newClass.asStr) {
        element.className = newClass.asStr
      } else {
        // do nothing
      }
    }

}
