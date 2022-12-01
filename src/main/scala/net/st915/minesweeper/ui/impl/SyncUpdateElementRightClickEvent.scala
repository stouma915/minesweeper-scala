package net.st915.minesweeper.ui.impl

import cats.effect.unsafe.IORuntime
import cats.effect.{IO, Sync}
import net.st915.minesweeper.ui.application.UpdateElementRightClickEvent
import org.scalajs.dom.*

class SyncUpdateElementRightClickEvent[F[_]: Sync] extends UpdateElementRightClickEvent[F] {

  override def update[A <: HTMLElement](element: A, onRightClick: IO[_])(implicit
  runtime: IORuntime): F[Unit] =
    Sync[F].blocking {
      element.oncontextmenu = e => {
        e.preventDefault()
        onRightClick.unsafeRunAndForget()
      }
    }

}
