package net.st915.minesweeper.logic.impl

import cats.effect.Sync
import net.st915.minesweeper.GameState
import net.st915.minesweeper.event.*
import net.st915.minesweeper.logic.application.EventDistinction

class SyncEventDistinction[F[_]: Sync] extends EventDistinction[F] {

  override def perform(event: Event, gameState: GameState): F[GameState] =
    event match
      case ButtonClickEvent(buttonId) =>
        Sync[F].pure {
          println(s"Button Clicked: $buttonId")
          gameState
        }
      case _ => Sync[F].pure(gameState)

}
