package net.st915.minesweeper.logic.eventloop

import cats.effect.unsafe.IORuntime
import cats.effect.{IO, Sync}
import net.st915.minesweeper.{Consts, GameState}
import net.st915.minesweeper.logic.eventloop.application.*
import net.st915.minesweeper.logic.eventloop.impl.*
import net.st915.minesweeper.logic.refreshui.RefreshUI
import org.scalajs.dom.*

object Loop {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](
    implicit document: HTMLDocument,
    window: Window,
    runtime: IORuntime
  ): F[Unit] = {
    implicit val _eventDistinction: EventDistinction[F] = SyncEventDistinction[F]
    implicit val _getEventFromQueue: GetEventFromQueue[F] = SyncGetEventFromQueue[F]

    Loop()
  }

  def routine[F[_]: Sync: GetEventFromQueue: EventDistinction](implicit
  document: HTMLDocument): F[Unit] =
    for {
      maybeEvent <- GetEventFromQueue[F].get
      _ <-
        maybeEvent match
          case Some(event) =>
            for {
              newState <- EventDistinction[F].perform(event, EventLoop.gameState)
              _ <- RefreshUI.wired[F](newState)
              _ <- Sync[F].delay(EventLoop.gameState = newState)
            } yield ()
          case None => Sync[F].unit
    } yield ()

  def apply[F[_]: Sync: GetEventFromQueue: EventDistinction]()(
    implicit document: HTMLDocument,
    window: Window,
    runtime: IORuntime
  ): F[Unit] =
    Sync[F].pure {
      window.setInterval(
        () => routine[F].asInstanceOf[IO[Unit]].unsafeRunAndForget(),
        Consts.EventLoopInterval
      )
    }

}
