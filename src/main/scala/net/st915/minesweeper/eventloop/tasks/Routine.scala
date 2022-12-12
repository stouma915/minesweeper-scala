package net.st915.minesweeper.eventloop.tasks

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.eventlistener.EventListener
import net.st915.minesweeper.eventloop.tasks.instances.*
import net.st915.minesweeper.eventloop.tasks.typeclasses.*
import net.st915.minesweeper.util.instances.MonadDoNothing
import net.st915.minesweeper.util.typeclasses.DoNothing

object Routine {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](gameState: GameState): F[GameState] = {
    given CanFetchEventFromQueue[F] = SyncCanFetchEventFromQueue[F]

    given DoNothing[F] = MonadDoNothing[F]

    given GameState = gameState

    for {
      maybeEvent <- CanFetchEventFromQueue[F].fetch
      newState <-
        maybeEvent match
          case Some(event) =>
            EventListener.wired[F](event)
          case None =>
            DoNothing[F].perform[GameState]
    } yield newState
  }

}
