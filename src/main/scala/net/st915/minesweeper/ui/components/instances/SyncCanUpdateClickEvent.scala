package net.st915.minesweeper.ui.components.instances

import cats.effect.unsafe.IORuntime
import cats.effect.{IO, Sync}
import net.st915.minesweeper.ui.components.typeclasses.CanUpdateClickEvent
import org.scalajs.dom.*

class SyncCanUpdateClickEvent[F[_]: Sync] extends CanUpdateClickEvent[F] {

  override def perform(element: HTMLElement, program: => F[Unit])(using IORuntime): F[Unit] =
    Sync[F].blocking {
      element.onclick = _ =>
        program
          .asInstanceOf[IO[Unit]]
          .unsafeRunAndForget()
    }

}
