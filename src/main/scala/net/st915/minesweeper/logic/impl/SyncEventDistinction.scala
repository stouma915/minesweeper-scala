package net.st915.minesweeper.logic.impl

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.*
import net.st915.minesweeper.logic.application.*

class SyncEventDistinction[F[_]: Sync: HandleButtonClickEvent] extends EventDistinction[F] {

  override def perform(event: Event, gameState: GameState): F[GameState] = {
    implicit val _gameState: GameState = gameState

    event match
      case e: ButtonClickEvent => HandleButtonClickEvent[F].handle(e)
      case _                   => Sync[F].pure(gameState)
  }

}
