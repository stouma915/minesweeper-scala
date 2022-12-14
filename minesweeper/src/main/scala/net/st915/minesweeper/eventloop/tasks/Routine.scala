package net.st915.minesweeper.eventloop.tasks

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.eventlistener.EventListener
import net.st915.minesweeper.eventloop.tasks.instances.*
import net.st915.minesweeper.eventloop.tasks.typeclasses.*
import net.st915.minesweeper.util.DoNothing

object Routine {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  import net.st915.minesweeper.util.instances.doNothingInstances.given

  def wired[F[_]: Sync](gameState: GameState): F[GameState] = {
    given CanFetchEventFromQueue[F] = SyncCanFetchEventFromQueue[F]

    given GameState = gameState

    for {
      _ <- Sync[F].pure(println(gameState)) // TODO: Remove this
      maybeEvent <- CanFetchEventFromQueue[F].fetch
      newState <-
        maybeEvent match
          case Some(event) =>
            EventListener.wired[F](event)
          case None =>
            DoNothing[F][GameState]()
    } yield newState
  }

}
