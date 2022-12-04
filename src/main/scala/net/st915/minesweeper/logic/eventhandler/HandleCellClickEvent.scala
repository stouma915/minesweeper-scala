package net.st915.minesweeper.logic.eventhandler

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellClickEvent
import net.st915.minesweeper.logic.eventhandler.application.*
import net.st915.minesweeper.logic.eventhandler.impl.*

object HandleCellClickEvent {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](event: CellClickEvent)(implicit gameState: GameState): F[GameState] = {
    implicit val _doNothing: DoNothing[F] = ApplicativeDoNothing[F]

    HandleCellClickEvent(event)
  }

  def apply[
    F[_]: Sync: DoNothing
  ](event: CellClickEvent)(implicit gameState: GameState): F[GameState] =
    for {
      _ <- Sync[F].pure(println(s"clicked: ${event.coord}"))
      newState <- DoNothing[F].perform
    } yield newState
}
