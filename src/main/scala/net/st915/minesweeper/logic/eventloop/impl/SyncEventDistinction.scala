package net.st915.minesweeper.logic.eventloop.impl

import cats.effect.Sync
import net.st915.minesweeper.{Difficulty, GameState}
import net.st915.minesweeper.event.*
import net.st915.minesweeper.logic.eventhandler.*
import net.st915.minesweeper.logic.eventloop.application.EventDistinction

class SyncEventDistinction[F[_]: Sync] extends EventDistinction[F] {

  override def perform(event: Event, gameState: GameState, difficulty: Difficulty): F[GameState] = {
    implicit val _gameState: GameState = gameState
    implicit val _difficulty: Difficulty = difficulty

    event match
      case e: ButtonClickEvent    => HandleButtonClickEvent.wired[F](e)
      case e: CellClickEvent      => HandleCellClickEvent.wired[F](e)
      case e: CellRightClickEvent => HandleCellRightClickEvent.wired[F](e)
      case _                      => Sync[F].pure(gameState)
  }

}
