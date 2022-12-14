package net.st915.minesweeper.ui.components.instances

import cats.effect.unsafe.IORuntime
import cats.effect.{IO, Sync}
import net.st915.minesweeper.ui.components.typeclasses.CanUpdateRightClickEvent
import org.scalajs.dom.*

class SyncCanUpdateRightClickEvent[F[_]: Sync] extends CanUpdateRightClickEvent[F] {

  override def perform(element: HTMLElement, program: => F[Unit])(using IORuntime): F[Unit] =
    Sync[F].blocking {
      element.oncontextmenu = e => {
        e.preventDefault()

        program
          .asInstanceOf[IO[Unit]]
          .unsafeRunAndForget()
      }
    }

}
