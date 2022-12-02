package net.st915.minesweeper.logic.eventloop.impl

import cats.effect.unsafe.IORuntime
import cats.effect.{IO, Sync}
import net.st915.minesweeper.Consts
import net.st915.minesweeper.logic.eventloop.application.Loop
import org.scalajs.dom.*

class SyncLoop[F[_]: Sync] extends Loop[F] {

  import cats.syntax.functor.*

  override def perform(task: F[Unit])(implicit window: Window, runtime: IORuntime): F[Unit] =
    Sync[F].pure {
      window.setInterval(
        () =>
          task
            .asInstanceOf[IO[Unit]]
            .unsafeRunAndForget(),
        Consts.EventLoopInterval
      )
    }

}
