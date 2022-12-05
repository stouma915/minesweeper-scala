package net.st915.minesweeper.logic.eventloop.task

import cats.effect.unsafe.IORuntime
import cats.effect.{IO, Sync}
import net.st915.minesweeper.{Consts, Difficulty}
import net.st915.minesweeper.logic.eventloop.EventLoop
import net.st915.minesweeper.logic.eventloop.application.*
import net.st915.minesweeper.logic.eventloop.impl.*
import net.st915.minesweeper.logic.refreshui.RefreshUI
import org.scalajs.dom.{HTMLDocument, Window}

object Loop {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    window: Window,
    runtime: IORuntime
  ): F[Unit] = {
    implicit val _eventDistinction: EventDistinction[F] = SyncEventDistinction[F]
    implicit val _getEventFromQueue: GetEventFromQueue[F] = SyncGetEventFromQueue[F]

    Loop(difficulty)
  }

  def routine[F[_]: Sync: GetEventFromQueue: EventDistinction](difficulty: Difficulty)(implicit
  document: HTMLDocument): F[Unit] =
    Sync[F].pure(println(s"Debug: ${EventLoop.gameState}")) >> // TODO: Remove this
      GetEventFromQueue[F].get >>= {
      _ match
        case Some(event) =>
          for {
            newState <- EventDistinction[F].perform(event, EventLoop.gameState)
            _ <-
              if (EventLoop.gameState != newState)
                RefreshUI.wired[F](difficulty, newState) >> Sync[F].delay(EventLoop.gameState =
                  newState)
              else
                Sync[F].unit
          } yield ()
        case None => Sync[F].unit
    }

  def apply[F[_]: Sync: GetEventFromQueue: EventDistinction](difficulty: Difficulty)(
    implicit document: HTMLDocument,
    window: Window,
    runtime: IORuntime
  ): F[Unit] =
    Sync[F].pure {
      window.setInterval(
        () => routine[F](difficulty).asInstanceOf[IO[Unit]].unsafeRunAndForget(),
        Consts.EventLoopInterval
      )
    }

}
