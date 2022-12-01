package net.st915.minesweeper.logic

import cats.effect.Sync
import cats.effect.unsafe.IORuntime
import net.st915.minesweeper.GameState
import net.st915.minesweeper.logic.application.*
import net.st915.minesweeper.logic.impl.*
import org.scalajs.dom.*

object EventLoop {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  private var gameState = GameState.empty

  def wired[F[_]: Sync](implicit window: Window, runtime: IORuntime): F[Unit] = {
    // format: off
    implicit val _eventDistinction: EventDistinction[F] = SyncEventDistinction[F]
    implicit val _getEventFromQueue: GetEventFromQueue[F] = SyncGetEventFromQueue[F]
    implicit val _loop: Loop[F] = SyncLoop[F]
    // format: on

    EventLoop()
  }

  def apply[F[_]: Sync: EventDistinction: GetEventFromQueue: Loop]()(
    implicit window: Window,
    runtime: IORuntime
  ): F[Unit] =
    Loop[F].perform {
      for {
        maybeEvent <- GetEventFromQueue[F].get
        newState <- EventDistinction[F].perform(maybeEvent, gameState)
        _ <- Sync[F].delay(this.gameState = newState)
      } yield ()
    }

}
