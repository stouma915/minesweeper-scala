package net.st915.minesweeper.logic.eventhandler

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.CellRightClickEvent
import net.st915.minesweeper.logic.eventhandler.application.*
import net.st915.minesweeper.logic.eventhandler.impl.*

object HandleCellRightClickEvent {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*

  def wired[F[_]: Sync](event: CellRightClickEvent)(implicit gameState: GameState): F[GameState] = {
    implicit val _doNothing: DoNothing[F] = ApplicativeDoNothing[F]

    HandleCellRightClickEvent(event)
  }

  def apply[
    F[_]: Sync: DoNothing
  ](event: CellRightClickEvent)(implicit gameState: GameState): F[GameState] =
    for {
      _ <- Sync[F].pure(println(s"right clicked: ${event.coord}"))
      newState <- DoNothing[F].perform
    } yield newState

}
