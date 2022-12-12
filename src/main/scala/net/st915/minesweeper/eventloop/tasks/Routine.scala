package net.st915.minesweeper.eventloop.tasks

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.eventloop.tasks.instances.*
import net.st915.minesweeper.eventloop.tasks.typeclasses.*

object Routine {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](gameState: GameState): F[GameState] = {
    given CanFetchEventFromQueue[F] = SyncCanFetchEventFromQueue[F]

    for {
      maybeEvent <- CanFetchEventFromQueue[F].fetch
      _ <- Sync[F].pure(maybeEvent.map(println))
      newState <- Sync[F].pure(gameState) // TODO
    } yield newState
  }

}
