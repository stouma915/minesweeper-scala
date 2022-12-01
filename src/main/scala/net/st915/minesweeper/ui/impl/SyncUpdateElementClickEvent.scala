package net.st915.minesweeper.ui.impl

import cats.effect.{IO, Sync}
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.ui.application.UpdateElementClickEvent
import org.scalajs.dom.*

class SyncUpdateElementClickEvent[F[_]: Sync]
    extends UpdateElementClickEvent[F] {

  override def update[A <: HTMLElement](
      element: A,
      onClick: IO[_]
  )(implicit runtime: IORuntime): F[Unit] =
    Sync[F].blocking {
      element.onclick = e => {
        e.preventDefault()
        onClick.unsafeRunAndForget()
      }
    }

}
